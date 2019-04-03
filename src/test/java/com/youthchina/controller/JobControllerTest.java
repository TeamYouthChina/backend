package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.dto.applicant.OrganizationDTO;
import com.youthchina.dto.company.CompanyRequestDTO;
import com.youthchina.dto.job.JobRequestDTO;
import com.youthchina.dto.job.JobSearchDTO;
import com.youthchina.dto.util.DurationDTO;
import com.youthchina.dto.util.LocationDTO;
import com.youthchina.dto.util.NationDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;



/**
 * Created by zhongyangwu on 2/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Company_test.xml",
        "classpath:New_Dictionary_test.xml",
        "classpath:New_SYS_test.xml",
        "classpath:New_Job_test.xml"
})
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
    public void testGetJob() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/jobs/1").param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"产品信息管理实习生\",\"organization\":{\"id\":37,\"name\":\"深圳市腾讯计算机系统有限公司\",\"avatarUrl\":null,\"location\":null,\"website\":\"https://www.tencent.com\",\"note\":\"腾讯科技股份有限公司（港交所：700）是中国规模最大的互联网公司，1998年11月由马化腾、张志东、陈一丹、许晨晔、曾李青5位创始人共同创立，总部位于深圳南山区腾讯大厦。腾讯业务拓展... \",\"nation\":null},\"location\":\"广东省广州市\",\"type\":\"实习\",\"deadLine\":\"12/31/2019\",\"job_duty\":\"在读硕士研究生, 如果是2019年毕业生, 有转正机会；熟悉产品信息管理\",\"job_description\":\"产品信息管理实习生\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }



    @Test
    public void testSearch() throws Exception {
//        JobSearchDTO jobSearchDTO = new JobSearchDTO();
//        jobSearchDTO.setId(1);
//        jobSearchDTO.setJobName("front");
//        jobSearchDTO.setComId(1);
//        jobSearchDTO.setCompanyName("大疆");
//        jobSearchDTO.setJobType(1);
//        jobSearchDTO.setSalaryCap(10000);
//        jobSearchDTO.setSalaryFloor(1000);
//        jobSearchDTO.setActivate(true);
//        Timestamp startTime = new Timestamp(118, 01, 01, 00, 00, 00, 00);
//        Timestamp endTime = new Timestamp(123, 01, 01, 00, 00, 00, 00);
////        startTime = null;
////        endTime = null;
//        DurationDTO duration = new DurationDTO(startTime, endTime);
//
//        jobSearchDTO.setDurationDTO(new DurationDTO());
//
//        JobSearchDTO jobSearchDTO2 = new JobSearchDTO();
//        jobSearchDTO2.setId(1);
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        java.lang.String searchJson = ow.writeValueAsString(jobSearchDTO);
//
//        this.mvc.perform(
//                post(this.urlPrefix + "/jobs/search").contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(searchJson)
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print())
//
//        //.andExpect(content().json("{\"content\":{\"searchResult\":[{\"jobId\":1,\"jobName\":\"front\",\"jobProfCode\":\"A\",\"jobStartTime\":\"2019-01-01\",\"jobEndTime\":\"2020-01-01\",\"jobType\":1,\"jobDescription\":\"996\",\"jobDuty\":\"front\",\"jobHighlight\":\"50K\",\"jobSalaryFloor\":5000,\"jobSalaryCap\":6000,\"jobLink\":\"job.com\",\"cvReceiMail\":\"youth@china\",\"cvNameRule\":\"nameRule\",\"jobActive\":1,\"jobLocationList\":[{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null}],\"jobReqList\":[{\"degreeNum\":1,\"degreeChn\":\"本科\",\"degreeEng\":\"Bachelor\",\"startDate\":\"2019-01-01T11:11:22.000+0000\",\"jobId\":1},{\"degreeNum\":2,\"degreeChn\":\"硕士\",\"degreeEng\":\"Master\",\"startDate\":\"2019-01-02T11:11:22.000+0000\",\"jobId\":1}],\"industries\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":\"2018-10-11T11:11:22.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":null,\"jobId\":1}],\"profession\":{\"profNum\":1,\"profLevel\":1,\"profCode\":\"A\",\"profParentCode\":\"A\",\"profChn\":\"前端\",\"profEng\":\"frontEnd\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"addTime\":\"2019-01-01T11:11:23.000+0000\",\"isDelete\":null,\"isDeleteTime\":null,\"company\":{\"companyId\":1,\"companyName\":\"大疆\",\"companyCode\":\"2\",\"companyIntroduc\":\"无人机\",\"companyNature\":{\"natureNum\":1,\"natureChn\":\"国企\",\"natureEng\":\"public\",\"natureDetail\":\"good\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"companyScale\":{\"scaleNum\":1,\"scaleChn\":\"大\",\"scaleEng\":\"big\",\"startTime\":\"2019-01-01T11:11:22.000+0000\"},\"location\":{\"region_num\":1,\"region_chn\":\"北京\",\"region_eng\":\"Beijing\",\"region_level\":1,\"region_parent_num\":1,\"start_time\":\"2019-01-01T11:11:22.000+0000\",\"is_delete\":0,\"is_delete_time\":null,\"jobId\":null,\"nation_code\":\"CHN\",\"regionCity\":null,\"usaState\":null},\"country\":null,\"companyMail\":\"dji@com\",\"companyWebsite\":\"dji.com\",\"companyStartDate\":\"2005-11-20\",\"companyLogo\":\"1\",\"companyVerify\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null,\"addTime\":null,\"jobs\":null,\"indList\":[{\"indNum\":1,\"indCode\":\"A\",\"indChn\":\"工\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"A3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null},{\"indNum\":2,\"indCode\":\"B\",\"indChn\":\"农\",\"indEng\":\"eng\",\"indLevel\":2,\"indParentCode\":\"B3\",\"startTime\":null,\"isDelete\":null,\"isDeleteTime\":null,\"companyId\":1,\"jobId\":null}],\"verificationList\":[],\"id\":1},\"hr\":{\"hrId\":1,\"companyId\":1,\"hrOnJob\":1,\"userId\":null,\"isDelete\":null,\"isDeleteTime\":null},\"id\":1}],\"status\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testDeleteJob() throws Exception {
        Integer id = 1;
        this.mvc.perform(
                delete(this.urlPrefix + "/jobs/" + id)//.param("id", "1").param("detailLevel", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }

    @Test
    public void testAddJob() throws Exception {
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setName("front");
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(2);
        jobRequestDTO.setOrganization(organizationDTO);
        jobRequestDTO.setType("1");

        List<LocationDTO> list = new ArrayList<>();
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_code("994701");
        locationDTO.setNation_code("USA");
        list.add(locationDTO);
        jobRequestDTO.setLocationList(list);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(jobRequestDTO);
        System.out.println(insertJson);

        this.mvc.perform(
                post(this.urlPrefix + "/jobs")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                //.andExpect(content().json("{\"content\"}", false))
        ;
    }

    @Test
    public void testUpdateJob() throws Exception {
        int id = 1;
        JobRequestDTO jobRequestDTO = new JobRequestDTO();
        jobRequestDTO.setId(1);
        jobRequestDTO.setName("front");
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(2);
        jobRequestDTO.setOrganization(organizationDTO);
        jobRequestDTO.setType("1");

        List<LocationDTO> list = new ArrayList<>();
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocation_code("994701");
        locationDTO.setNation_code("USA");
        list.add(locationDTO);
        jobRequestDTO.setLocationList(list);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(jobRequestDTO);

        System.out.println(insertJson);
        this.mvc.perform(
                put(this.urlPrefix + "/jobs/" + id)
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)

        )
                .andDo(print())
                //.andExpect(content().json("{\"content\":{\"id\":" + id + ",\"name\":\"Vavle\",\"avatarUrl\":\"vavle.com/AvatarUrl\",\"location\":\"Berkeley\",\"website\":\"vavle.com\",\"note\":\"Steam\",\"nation\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }



}
