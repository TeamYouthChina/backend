package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.HrMapper;
import com.youthchina.dao.qingyang.ResumeJsonMapper;
import com.youthchina.domain.Qinghong.ResumeJson;
import com.youthchina.domain.qingyang.Hr;
import io.swagger.models.auth.In;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:resumejson.xml"})
public class ResumeJsonTest {

    @Autowired
    private ResumeJsonMapper resumeJsonMapper;

    @Test
    public void testGetJson() {
        ResumeJson resumeJson=resumeJsonMapper.selectResumeJson(1);
        Assert.assertNotNull(resumeJson);
        System.out.print(resumeJson.getJson_1());
    }
    @Test
    public void insertJson(){
        ResumeJson resumeJson=new ResumeJson();
        resumeJson.setJson_1("1111");
        resumeJson.setJson_count(1);
        Integer integer=resumeJsonMapper.insertResumeJson(resumeJson);
        System.out.print(integer);

    }
    @Test
    public void deleteJson(){
        Integer integer=resumeJsonMapper.deleteResumeJson(1);
        System.out.print(integer);

    }


}
