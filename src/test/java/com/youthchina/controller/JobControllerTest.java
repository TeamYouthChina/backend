package com.youthchina.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * Created by zhongyangwu on 2/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:job.xml"})
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
    public void testLogin() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/jobs/1").param("jobId", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobId\":1,\"jobName\":\"teacher\",\"jobProfCode\":\"123\",\"jobStartTime\":\"2018-10-11\",\"jobEndTime\":\"2018-10-11\",\"jobType\":12,\"jobDescription\":\"hkukfyufyufiy\",\"jobDuty\":\"uoogyicdcyvhh\",\"jobHighlight\":\"lkkfjchvjugifuv\",\"jobSalaryFloor\":3000,\"jobSalaryCap\":6000,\"jobLink\":\"aogubkliphoug\",\"cvReceiMail\":\"787879879@gmail.com\",\"cvNameRule\":\"sdfghjkrtyu\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":null,\"region_chn\":null,\"region_eng\":null,\"region_level\":null,\"region_parent_num\":null,\"start_time\":null,\"is_delete\":null,\"is_delete_time\":null,\"jobId\":1}],\"jobReqList\":[{\"degreeNum\":null,\"degreeChn\":null,\"degreeEng\":null,\"startDate\":null,\"jobId\":1}],\"industries\":[{\"indNum\":null,\"indCode\":null,\"indChn\":null,\"indEng\":null,\"indLevel\":null,\"indParentCode\":null,\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":null,\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":3,\"companyName\":null,\"companyCode\":null,\"companyIntroduc\":null,\"companyNature\":null,\"companyScale\":null,\"location\":null,\"country\":null,\"companyMail\":null,\"companyWebsite\":null,\"companyStartDate\":null,\"companyLogo\":null,\"companyVerify\":null,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":null,\"indCode\":null,\"indChn\":null,\"indEng\":null,\"indLevel\":null,\"indParentCode\":null,\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":3,\"jobId\":null}],\"verificationList\":[]},\"hr\":{\"hrId\":2,\"companyId\":3,\"hrOnJob\":null,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1}}", false))
                ;
    }

}
