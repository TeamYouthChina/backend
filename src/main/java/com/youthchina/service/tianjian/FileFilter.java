package com.youthchina.service.tianjian;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

public class FileFilter {
    @Autowired
    StaticFileSystemService staticFileSystemService;
    public boolean isFileLegalUpload(File file){
        boolean isLegal=false;
          if(file.length()>0){
              isLegal = true;
          }
          return isLegal;
    }


}
