package com.youthchina.service.tianjian;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("FileNameGenerate")
public class FileNameGenerate {
    public String generateFileName(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }
}
