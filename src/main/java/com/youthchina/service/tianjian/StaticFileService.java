package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.tianjian.ComMediaDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 2/12/19.
 */
@Service
public class StaticFileService {
    private StaticFileSystemMapper fileSystemMapper;

    private FileNameGenerate fileNameGenerate;

    private FileStorageService[] fileStorageServices;

    private SnowFlakeIdGenerate idGenerate;

    @Autowired
    public StaticFileService(StaticFileSystemMapper fileSystemMapper, FileNameGenerate fileNameGenerate, FileStorageService[] fileStorageServices, SnowFlakeIdGenerate idGenerate) {
        this.fileSystemMapper = fileSystemMapper;
        this.fileNameGenerate = fileNameGenerate;
        this.fileStorageServices = fileStorageServices;
        this.idGenerate = idGenerate;
    }

    public long saveFile(File file, Integer user_id) {
        //Do not perform user inspect
        ComMediaDocument comMediaDocument = new ComMediaDocument();
        Long id = idGenerate.nextId();
        String uuid = fileNameGenerate.generateFileName();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comMediaDocument.setCreate_time(time);
        comMediaDocument.setIs_delete(0);
        comMediaDocument.setIs_delete_time(null);
        comMediaDocument.setUser_id(user_id);
        String fileName = file.getName();
        int index = fileName.lastIndexOf(".");
        comMediaDocument.setDocu_local_name(fileName.substring(0, index));
        comMediaDocument.setDocu_local_format(fileName.substring(index + 1));
        comMediaDocument.setDocu_local_id(id.toString());
        //save info to database
        fileSystemMapper.saveFileInfo(comMediaDocument);
        try {
            //save fileInfo
            for (FileStorageService fileStorageService : fileStorageServices) {
                fileStorageService.uploadFile(file);
            }
        } catch (Exception e) {

        }
        return id;
    }
}
