package com.youthchina.service.tianjian;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectRequest;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service("StaticFileSystemServiceImplALiCloud")
public class StaticFileSystemServiceImplALiCloud implements StaticFileSystemService {

    // 阿里云API的内或外网域名
    private static String endPoint;

    // 阿里云API的密钥Access Key ID
    private static String accessKeyId;

    // 阿里云API的密钥Access Key Secret
    private static String accessKeySecret;

    //仓库名称Bucket Name
    private static String bucketName;

    @Autowired
    SnowFlakeIdGenerate snowFlakeIdGenerate;

    @Autowired
    FileNameGenerate fileNameGenerate;

    @Autowired
    StaticFileSystemMapper mapper;

    public void printOSSExceptionMessage(OSSException oe) {
        System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        System.out.println("Error Message: " + oe.getErrorCode());
        System.out.println("Error Code:       " + oe.getErrorCode());
        System.out.println("Request ID:      " + oe.getRequestId());
        System.out.println("Host ID:           " + oe.getHostId());
    }

    public StaticFileSystemServiceImplALiCloud(@Value("${staticfile.endPoint}")String endPoint, @Value("${staticfile.accessKeyId}")String accessKeyId, @Value("${staticfile.accessKeySecret}")String accessKeySecret,@Value("${staticfile.bucketName}")String bucketName){
        this.endPoint = endPoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    @Override
    public long uploadFile(String fileName,File file,String format) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        long localId=0;
        try {
            /*
             * Upload an object
             */
            InputStream input = new FileInputStream(file);
            byte[] inputByte = new byte[input.available()];
            input.read(inputByte);
            String fileNameInDataBase = fileNameGenerate.generateFileName();
            System.out.println("Uploading a new object to OSS from a file\n");
            ossClient.putObject(new PutObjectRequest(bucketName, fileNameInDataBase, new ByteArrayInputStream(inputByte)));
            localId = snowFlakeIdGenerate.nextId();
            System.out.println("Uploading a new object to OSS from a file\n");
            ossClient.putObject(new PutObjectRequest(bucketName, String.valueOf(localId), new ByteArrayInputStream(inputByte)));


            ComMediaDocument comMediaDocument = new ComMediaDocument();
            comMediaDocument.setDocu_local_id( String.valueOf(localId));
            comMediaDocument.setDocu_local_name(fileName);
            comMediaDocument.setDocu_local_format(format);
            comMediaDocument.setDocu_server_ali_id(String.valueOf(localId));
            comMediaDocument.setDocu_server_aws_id(String.valueOf(localId));
            Timestamp time = new Timestamp(System.currentTimeMillis());
            comMediaDocument.setCreate_time(time);
            comMediaDocument.setIs_delete(0);
            comMediaDocument.setIs_delete_time(null);
            mapper.saveFileInfo(comMediaDocument);

        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }

        return localId;
    }

    @Override
    public URL downloadFile(String fileName) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        URL url = null;
        try {
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);// 生成URL
            url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
            return url;
        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (Throwable ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
       return url;
    }

    @Override
    public boolean verifyFile(String fileName) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        boolean found = false;
        try {
            found = ossClient.doesObjectExist(bucketName, fileName);
        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (Throwable ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
        return found;
    }

    @Override
    public void deleteFile(List<String> fileName) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        try {
            /*
             * Delete all objects uploaded recently under the bucket
             */
            System.out.println("\nDeleting all objects:");
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(fileName));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                System.out.println("\t" + object);
            }
        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }
}
