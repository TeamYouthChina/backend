package com.youthchina.service.tianjian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileFilter {
    @Autowired
    FileStorageService[] staticFileSystemService;

    public boolean isFileLegalUpload(File file) {
        boolean isLegal = false;
        if (file.length() > 0) {
            isLegal = true;
        }
        return isLegal;
    }


}
