package com.youthchina.service.tianjian;

import java.util.Hashtable;

public class Idtest implements Runnable {

     SnowFlakeIdGenerate snowFlakeIdGenerate;
     Hashtable<Long, Integer> testtable;
     public Idtest(SnowFlakeIdGenerate snowFlakeIdGenerate, Hashtable<Long,Integer> testtable){
         this.snowFlakeIdGenerate = snowFlakeIdGenerate;
         this.testtable = testtable;
     }
    @Override
    public void run() {
        long i = snowFlakeIdGenerate.nextId();
        testtable.put(i,testtable.getOrDefault(i, 0) + 1);
    }
}
