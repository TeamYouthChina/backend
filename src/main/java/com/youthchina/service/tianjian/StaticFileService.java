package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.tianjian.ComMediaDocument;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyangwu on 2/12/19.
 */
@Service
public class StaticFileService {
    private StaticFileSystemMapper fileSystemMapper;

    private FileNameGenerate fileNameGenerate;

    private Map<String, FileStorageService> fileStorageServices;

    private SnowFlakeIdGenerate idGenerate;

    @Autowired
    public StaticFileService(StaticFileSystemMapper fileSystemMapper, FileNameGenerate fileNameGenerate, FileStorageService[] fileStorageServices, SnowFlakeIdGenerate idGenerate) {
        this.fileSystemMapper = fileSystemMapper;
        this.fileNameGenerate = fileNameGenerate;
        this.fileStorageServices = new HashMap<>();
        for (FileStorageService service : fileStorageServices) {
            this.fileStorageServices.put(service.getClass().getSimpleName(), service);
        }
        Asserts.check(!this.fileStorageServices.isEmpty(), "Must have a FileStorageService");
        this.idGenerate = idGenerate;
    }

    public long saveFile(File file, Integer user_id) throws IOException {
        //Do not perform user inspect
        ComMediaDocument comMediaDocument = new ComMediaDocument();
        Long id = idGenerate.nextId();
        String uuid = fileNameGenerate.generateFileName();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comMediaDocument.setCreate_time(time);
        comMediaDocument.setIs_delete(0);
        comMediaDocument.setIs_delete_time(null);
        comMediaDocument.setUpload_user_id(user_id);
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        comMediaDocument.setDocu_local_name(fileName.substring(0, index));
        comMediaDocument.setDocu_local_format(fileName.substring(index + 1));
        comMediaDocument.setDocu_local_id(id.toString());
        Double fizeSize = Double.valueOf(file.length() / 1024.0 / 1024.0);
        comMediaDocument.setDocu_local_size(String.valueOf(fizeSize));

        //save info to database
        fileSystemMapper.saveFileInfo(comMediaDocument);
        try {
            //save fileInfo
            for (FileStorageService fileStorageService : fileStorageServices.values()) {
                fileStorageService.uploadFile(file,id);
            }
        } catch (Exception e) {

        }
        return id;
    }

    public URL getFileUrl(String fileId, String location) {
        switch (location) {
            case "China": {
                return getFileStorageService(AliCloudFileStorageService.class.getSimpleName()).downloadFile(fileId);
            }
            case "US": {
                return getFileStorageService("AWSFileStorageService").downloadFile(fileId);
            }
        }
        return null;
    }

    private FileStorageService getFileStorageService(String name) {
        if (this.fileStorageServices.containsKey(name)) {
            return this.fileStorageServices.get(name);
        } else {
            return this.fileStorageServices.get(this.fileStorageServices.keySet().iterator().next());
        }
    }
}
