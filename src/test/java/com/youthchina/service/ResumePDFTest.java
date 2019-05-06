package com.youthchina.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dao.Qinghong.ResumeMapper;
import com.youthchina.dao.qingyang.ResumePDFMapper;
import com.youthchina.domain.qingyang.ResumePDF;
import com.youthchina.dto.applicant.ResumePDFDTO;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.application.ResumePDFServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.job.JobRequestDTO;
import com.youthchina.dto.util.LocationDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * @author: Qingyang Zhao
 * @create: 2019-05-05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class ResumePDFTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

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
        Assert.assertEquals("TestName", resumePDF.getResumeName());
        Assert.assertEquals(Integer.valueOf(ResumePDF.GENERATED), resumePDF.getGenerateMethod());
        Assert.assertEquals("TestDocID", resumePDF.getDocuLocalId());
        Assert.assertEquals(Integer.valueOf(1), resumePDF.getStuId());

        //Update Name
        resumePDF.setResumeName("UpdateName");
        resumePDF = resumePDFService.update(resumePDF);
        Assert.assertEquals("UpdateName", resumePDF.getResumeName());

        //Delete
        resumePDFService.delete(resumePDF.getResumeId());
        try {
            resumePDF = resumePDFService.get(resumePDF.getResumeId());
            Assert.fail();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void controllerAddTest() throws Exception {
        //ADD
        ResumePDFDTO resumePDFDTO = new ResumePDFDTO();
        resumePDFDTO.setFileId("TestFileId");
        resumePDFDTO.setName("TestName");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(resumePDFDTO);

        System.out.println(insertJson);
        this.mvc.perform(
                post(this.urlPrefix + "/resumes/pdf")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"fileId\":\"TestFileId\",\"name\":\"TestName\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }

    @Test
    public void controllerGetTest() throws Exception {
        ResumePDF resumePDF = new ResumePDF();
        resumePDF.setResumeName("TestName");
        resumePDF.setDocuLocalId("TestFileId");
        resumePDF.setGenerateMethod(ResumePDF.GENERATED);
        resumePDF.setStuId(1);
        resumePDF = resumePDFService.add(resumePDF);
        Integer id = resumePDFMapper.selectResumePDFByStuId(1).get(0).getResumeId();
        System.out.println("\nTest::::::" + id);
        this.mvc.perform(
                get(this.urlPrefix + "/resumes/pdf/:" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"fileId\":\"TestFileId\",\"name\":\"TestName\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void controllerPatchTest() throws Exception {
        ResumePDF resumePDF = new ResumePDF();
        resumePDF.setResumeName("Initial");
        resumePDF.setDocuLocalId("TestFileId");
        resumePDF.setGenerateMethod(ResumePDF.GENERATED);
        resumePDF.setStuId(1);
        resumePDF = resumePDFService.add(resumePDF);
        Integer id = resumePDFMapper.selectResumePDFByStuId(1).get(0).getResumeId();

        ResumePDFDTO resumePDFDTO = new ResumePDFDTO();
        resumePDFDTO.setName("TestName");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(resumePDFDTO);

        this.mvc.perform(
                patch(this.urlPrefix + "/resumes/pdf/:" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":" + id + ",\"fileId\":\"TestFileId\",\"name\":\"TestName\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }


    @Test
    public void controllerDeleteTest() throws Exception {
        ResumePDF resumePDF = new ResumePDF();
        resumePDF.setResumeName("Initial");
        resumePDF.setDocuLocalId("TestFileId");
        resumePDF.setGenerateMethod(ResumePDF.GENERATED);
        resumePDF.setStuId(1);
        resumePDF = resumePDFService.add(resumePDF);
        Integer id = resumePDFMapper.selectResumePDFByStuId(1).get(0).getResumeId();

        this.mvc.perform(
                delete(this.urlPrefix + "/resumes/pdf/:" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }
}
