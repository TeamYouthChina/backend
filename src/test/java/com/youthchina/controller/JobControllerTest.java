package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.DurationDTO;
import com.youthchina.dto.JobSearchDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by zhongyangwu on 2/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml"})
@WebAppConfiguration
public class JobControllerTest {

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
    public void testgetJob() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/jobs/1").param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                //.andExpect(content().json("{\"content\":{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profLevel\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"addTime\":\"2019-01-01T11:11:23.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"addTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[],\"id\":1},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }

    @Test
    public void testSearch() throws Exception {
        JobSearchDTO jobSearchDTO = new JobSearchDTO();
        jobSearchDTO.setJobId(1);
        jobSearchDTO.setJobName("front");
        jobSearchDTO.setComId(1);
        jobSearchDTO.setComName("大疆");
        jobSearchDTO.setType(1);
        jobSearchDTO.setSalaryCap(10000);
        jobSearchDTO.setSalaryFloor(1000);
        jobSearchDTO.setActive(1);
        Timestamp startTime = new Timestamp(118,01,01,00,00,00,00);
        Timestamp endTime = new Timestamp(123,01,01,00,00,00,00);
        DurationDTO duration = new DurationDTO(startTime, endTime);

        jobSearchDTO.setDurationDTO(duration);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String searchJson = ow.writeValueAsString(jobSearchDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/jobs/search").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(searchJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())

                //.andExpect(content().json("{\"content\":{\"searchResult\":[{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profLevel\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"addTime\":\"2019-01-01T11:11:23.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"addTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[],\"id\":1},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1}],\"status\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

}
