package com.youthchina.service.tianjian;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
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

    public void printOSSExceptionMessage(OSSException oe) {
        System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        System.out.println("Error Message: " + oe.getErrorCode());
        System.out.println("Error Code:       " + oe.getErrorCode());
        System.out.println("Request ID:      " + oe.getRequestId());
        System.out.println("Host ID:           " + oe.getHostId());
    }

    public StaticFileSystemServiceImplALiCloud(@Value("${endPoint}")String endPoint, @Value("${accessKeyId}")String accessKeyId, @Value("${accessKeySecret}")String accessKeySecret,@Value("${bucketName}")String bucketName){
        this.endPoint = endPoint;
        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
        this.bucketName = bucketName;
    }

    @Override
    public long uploadFile(String fileName) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        try {
            /*
             * Upload an object
             */
          /*  InputStream input = new FileInputStream(file);
            byte[] inputByte = new byte[input.available()];
            input.read(inputByte);*/
            String content = "Hello OSS";
            ossClient.putObject(bucketName, "nihao", new ByteArrayInputStream(content.getBytes()));
            boolean isExist = ossClient.doesObjectExist(bucketName, fileName);
            if(isExist==true){

            }else{
                //    System.out.println("Uploading a new object to OSS from a file\n");
                //    ossClient.putObject(new PutObjectRequest(bucketName, filename, new ByteArrayInputStream(inputByte)));
            }

        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        }/* catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } */finally {
            ossClient.shutdown();
        }
        long myId = snowFlakeIdGenerate.nextId();
        return myId;
    }

    @Override
    public URL downloadFile(String fileName) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
        URL url = null;
        try {
            Date expiration = new Date(new Date().getTime() + 3600 * 1000);// 生成URL
            url = ossClient.generatePresignedUrl(bucketName, accessKeyId, expiration);
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
        List<OSSObjectSummary> fileList = null;
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
