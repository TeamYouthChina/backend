package com.youthchina.Qinghong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.Qinghong.JobApply;
import com.youthchina.domain.Qinghong.JobCollect;
import com.youthchina.dto.UserDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



/**
 * @program: youthchina
 * @description: 申请者controller 测试
 * @author: Qinghong Wang
 * @create: 2019-02-12 09:14
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:applicant.xml"})
@WebAppConfiguration
public class ApplicantControllerTest {
    @Autowired
    WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testGetExtracurriculars() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/extracurriculars")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetJobCollects() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/jobCollects",1).param("id", "1").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testGetCompCollects() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/companyCollects",1).param("id", "1").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testDeleteJobCollect() throws Exception{
        this.mvc.perform(delete(this.urlPrefix + "/applicants/1/jobCollect/1").with(authGenerator.authentication()))
                .andDo(print());
    }
    @Test
    public void testDeleteCompCollect() throws Exception{
        this.mvc.perform(delete(this.urlPrefix + "/applicants/1/compCollect/1").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testGetJobApplies() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/jobApplies",1).param("id", "1").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testAddJobApply() throws Exception{
//        JobApply jobApply=new JobApply();
//        jobApply.setJob_cv_send(1);
//        jobApply.setJob_id(1);
//        jobApply.setJob_apply_status("已申请");
//        jobApply.setStu_id(1);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        java.lang.String requestJson = ow.writeValueAsString(jobApply);
        this.mvc.perform(post(this.urlPrefix + "/applicants/1/jobApply/1").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testAddJobCollect() throws Exception{
        this.mvc.perform
                (post(this.urlPrefix + "/applicants/1/jobCollect/2")
                .with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testAddCompCollect() throws Exception{
        this.mvc.perform
                (post(this.urlPrefix + "/applicants/1/compCollect/1")
                        .with(authGenerator.authentication()))
                .andDo(print());
    }




}
