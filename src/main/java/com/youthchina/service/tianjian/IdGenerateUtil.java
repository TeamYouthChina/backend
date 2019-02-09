package com.youthchina.service.tianjian;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("IdGenerateUtil")
public class IdGenerateUtil {
    public int generateNumberId(int num, int rangeLow, int rangeHigh,int length ) {
        return 0;
    }

    public String generateStringId(int wordCase){
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        if(wordCase==0){
            return uuid;
        }else if(wordCase==1){
            return uuid.toLowerCase();
        }else {
            return uuid.toUpperCase();
        }

    }
}
