package com.youthchina;

import com.youthchina.service.tianjian.Idtest;
import com.youthchina.service.tianjian.LocalFileManage;
import com.youthchina.service.tianjian.SnowFlakeIdGenerate;
import com.youthchina.service.tianjian.StaticFileSystemServiceImplALiCloud;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YouthchinaApplicationTests {
    @Autowired
    SnowFlakeIdGenerate snowFlakeIdGenerate;
    @Autowired
    StaticFileSystemServiceImplALiCloud staticFileSystemServiceImplALiCloud;
    @Autowired
    LocalFileManage localFileManage;

    @Test
    public void testupLoadFile() {
       File file = new File("");
       long i = staticFileSystemServiceImplALiCloud.uploadFile("youthchinatest",file ,"mp4");
       System.out.println(i);
    }

    @Test
    public void verifyFile() {
        System.out.println( staticFileSystemServiceImplALiCloud.verifyFile("nihaouip"));
    }

    @Test
    public void testIdGenerate() {

        Hashtable<Long,Integer> testtable = new Hashtable<Long, Integer>();
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        for(int i = 0;i<10000;i++){
            executorService.execute(new Idtest(snowFlakeIdGenerate,testtable));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()){}

        for(Iterator it=testtable.keySet().iterator();it.hasNext();){
            Long kkk = (Long)it.next();
            if(testtable.get(kkk)>1){
                System.out.println("fail");
                break;
            }
        }
        System.out.println(testtable.size());
        System.out.println("end");

    }

    @Test
    public void testGenerateId() {
        Long i = snowFlakeIdGenerate.nextId();
        System.out.println(i);
    }

    @Test
    public void testuploadFiletoLocal() {


    }



}
