package com.youthchina.service.tianjian;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.PutObjectRequest;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.tianjian.ComMediaDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AliCloudFileStorageService implements FileStorageService {

    // 阿里云API的内或外网域名
    private static String endPoint;

    // 阿里云API的密钥Access Key ID
    private static String accessKeyId;

    // 阿里云API的密钥Access Key Secret
    private static String accessKeySecret;

    //仓库名称Bucket Name
    private static String bucketName;

    private static String cloudIdColName;

    @Autowired
    SnowFlakeIdGenerate snowFlakeIdGenerate;

    @Autowired
    FileNameGenerate fileNameGenerate;

    @Autowired
    StaticFileSystemMapper mapper;//todo: determine how to use interface to perform insert to cloud provider column

    public void printOSSExceptionMessage(OSSException oe) {
        System.out.println("Caught an OSSException, which means your request made it to OSS, "
                + "but was rejected with an error response for some reason.");
        System.out.println("Error Message: " + oe.getErrorCode());
        System.out.println("Error Code:       " + oe.getErrorCode());
        System.out.println("Request ID:      " + oe.getRequestId());
        System.out.println("Host ID:           " + oe.getHostId());
    }

    public AliCloudFileStorageService(@Value("${staticfile.endPoint}") String endPoint, @Value("${staticfile.accessKeyId}") String accessKeyId, @Value("${staticfile.accessKeySecret}") String accessKeySecret, @Value("${staticfile.bucketName}") String bucketName, @Value("${staticfile.idColName}") String cloudIdColName) {
        AliCloudFileStorageService.endPoint = endPoint;
        AliCloudFileStorageService.accessKeyId = accessKeyId;
        AliCloudFileStorageService.accessKeySecret = accessKeySecret;
        AliCloudFileStorageService.bucketName = bucketName;
        AliCloudFileStorageService.cloudIdColName = cloudIdColName;
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

    @Override
    public void uploadFile(File file) {
        OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);

        Long localId = snowFlakeIdGenerate.nextId();
        try {
            this.uploadFile(file, ossClient, localId);
        } catch (OSSException oe) {
            printOSSExceptionMessage(oe);
        } catch (ClientException ce) {
            System.out.println("Error Message: " + ce.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        //if all successful
        this.mapper.setCloudStorageId(cloudIdColName, localId);
    }

    public void uploadFile(File file, OSSClient ossClient, Long localId) throws IOException {
        InputStream input = new FileInputStream(file);
        byte[] inputByte = new byte[input.available()];
        input.read(inputByte);
        //System.out.println("Uploading a new object to OSS from a file\n");
        ossClient.putObject(new PutObjectRequest(bucketName, String.valueOf(localId), new ByteArrayInputStream(inputByte)));

    }
}
