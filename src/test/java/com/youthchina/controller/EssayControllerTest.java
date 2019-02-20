package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.EssayReplyDTO;
import com.youthchina.dto.community.RequestEssayDTO;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:essay.xml"})
@DatabaseSetup({"classpath:company.xml"})
@WebAppConfiguration
public class EssayControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void getArticleTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
                //.andExpect(content().json("{\"id\":1,\"title\":\"title\",\"body\":\"WOSHI BODY\",\"company\":null,\"creat_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"user\":null,\"user_anony\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void deleteArticleTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
//                .andExpect(content().json("{\"content\":{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":1}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":null,\"is_delete_time\":null,\"jobId\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[]},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4000,\"reason\":\"not found articles\"}}", false));

    }

    @Test
    public void addArticleTest() throws Exception {
        RequestEssayDTO  essayDTO = new RequestEssayDTO();
        essayDTO.setBody("article is very good.");
        essayDTO.setTitle("Article balabala");
        essayDTO.setCompany_id(1);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(essayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void addCommentTest() throws Exception {
        EssayReplyDTO essayDTO = new EssayReplyDTO();
        essayDTO.setAnonymous(false);
        essayDTO.setBody("Nizhenshigerencai");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(essayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }



}
