package com.youthchina.service;

import com.youthchina.dao.qingyang.ResumePDFMapper;
import com.youthchina.domain.qingyang.ResumePDF;
import com.youthchina.service.application.ResumePDFServiceImpl;
import com.youthchina.service.community.FriendsServiceImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.FriendsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class ResumePDFTest {

    MockMvc mvc;
    @Autowired
    private WebApplicationContext context;
    @Value("${web.url.prefix}")
    private String urlPrefix;
    @Autowired
    private ResumePDFServiceImpl resumePDFService;
    @Autowired
    private ResumePDFMapper resumePDFMapper;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void serviceTest() throws NotFoundException {
        //Add ( Get )
        ResumePDF resumePDF = new ResumePDF();
        resumePDF.setResumeName("TestName");
        resumePDF.setDocuLocalId("TestDocID");
        resumePDF.setGenerateMethod(ResumePDF.GENERATED);
        resumePDF.setStuId(1);
        resumePDF = resumePDFService.add(resumePDF);
        Assert.assertEquals("TestName",resumePDF.getResumeName());
        Assert.assertEquals(Integer.valueOf(ResumePDF.GENERATED),resumePDF.getGenerateMethod());
        Assert.assertEquals("TestDocID",resumePDF.getDocuLocalId());
        Assert.assertEquals(Integer.valueOf(1),resumePDF.getStuId());

        //Update Name
        resumePDF.setResumeName("UpdateName");
        resumePDF = resumePDFService.update(resumePDF);
        Assert.assertEquals("UpdateName",resumePDF.getResumeName());

        //Delete
        resumePDFService.delete(resumePDF.getResumeId());
        try {
            resumePDF = resumePDFService.get(resumePDF.getResumeId());
            Assert.fail();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

}
