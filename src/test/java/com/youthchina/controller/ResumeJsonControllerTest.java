//package com.youthchina.controller;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.youthchina.annotation.RequestBodyDTO;
//import com.youthchina.annotation.ResponseBodyDTO;
//import com.youthchina.controller.DomainCRUDController;
//import com.youthchina.dao.qingyang.ResumeJsonMapper;
//import com.youthchina.domain.qingyang.ResumeJson;
//import com.youthchina.domain.qingyang.ResumePDF;
//import com.youthchina.domain.zhongyang.User;
//import com.youthchina.dto.Response;
//import com.youthchina.dto.applicant.ResumeJsonRequestDTO;
//import com.youthchina.dto.applicant.ResumeJsonResponseDTO;
//import com.youthchina.dto.applicant.ResumePDFDTO;
//import com.youthchina.exception.zhongyang.exception.BaseException;
//import com.youthchina.exception.zhongyang.exception.ForbiddenException;
//import com.youthchina.exception.zhongyang.exception.NotFoundException;
//import com.youthchina.service.DomainCRUDService;
//import com.youthchina.service.application.ResumeJsonServiceImpl;
//import com.youthchina.service.application.ResumePDFServiceImpl;
//import com.youthchina.service.user.StudentServiceImpl;
//import com.youthchina.util.AuthGenerator;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.net.URI;
//import java.net.URISyntaxException;
///**
// * @author: Qingyang Zhao
// * @create: 2019-02-16
// **/
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
//@DatabaseSetup({"classpath:resumejson.xml"})
//@WebAppConfiguration
//public class ResumeJsonControllerTest {
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @Autowired
//    private ResumeJsonMapper resumeJsonMapper;
//
//    @Autowired
//    private StudentServiceImpl studentServiceImpl;
//
//    @Value("${web.url.prefix}")
//    private String urlPrefix;
//
//    private AuthGenerator authGenerator = new AuthGenerator();
//
//    MockMvc mvc;
//
//    @Before
//    public void setup() {
//        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
//    }
//
//    @Test
//    public void insertMapperTest() throws Exception { // PASS
//        ResumeJson resumeJson = new ResumeJson();
//
//        resumeJsonMapper.insertResumeJson(resumeJson);
//    }
//
//    @Test
//    public void insertServiceTest() throws Exception { // PASS
//        ResumeJson resumeJson = new ResumeJson();
//        resumeJson.setJson_count(2);
//        resumeJson.setJson_1("111");
//        resumeJson.setJson_2("222");
//        resumeJson.setStu_id(1);
//        resumeJson = studentServiceImpl.insertResumeJson(resumeJson);
//        Assert.assertEquals(Integer.valueOf(2), resumeJson.getJson_count());
//        Assert.assertEquals("111", resumeJson.getJson_1());
//    }
//
//    @Test
//    public void insertTest() throws Exception {
//        Integer id = 1;
//        ResumeRequestDTO requestDTO = new ResumeRequestDTO();
//        String resume = "111";
//        requestDTO.setResume(resume);
//
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        java.lang.String insertJson = ow.writeValueAsString(requestDTO);
//
//        this.mvc.perform(
//                post(this.urlPrefix + "/resumes")//.param("id", "1").param("detailLevel", "1")
//                        .with(authGenerator.authentication())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .content(insertJson)
//        )
//                .andDo(print())
//        //     .andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
//        ;
//    }
//
//    @Test
//    public void getTest() throws Exception {
//        Integer id = 1;
//
//        this.mvc.perform(
//                get(this.urlPrefix + "/resumes/" + id)//.param("id", "1").param("detailLevel", "1")
//                        .with(authGenerator.authentication())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        )
//                .andDo(print())
//        //     .andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
//        ;
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        Integer id = 1;
//        this.mvc.perform(
//                delete(this.urlPrefix + "/resumes/" + id)//.param("id", "1").param("detailLevel", "1")
//                        .with(authGenerator.authentication())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        )
//                .andDo(print())
//        ;
//    }
//
//    @Test
//    public void getByStuIdMapperTest() throws Exception {
//        Integer stu_id = 1;
//        List<ResumeJson> resumeJsonList = resumeJsonMapper.selectResumeJsonByStuId(stu_id);
//
//        Assert.assertEquals(2, resumeJsonList.size());
//        Assert.assertEquals(Integer.valueOf(1), resumeJsonList.get(0).getResume_id());
//
//    }
//
//    @Test
//    public void getByStuIdServiceTest() throws Exception {
//        Integer stu_id = 1;
//        List<ResumeJson> resumeJsonList = studentServiceImpl.selectResumeJsonByStuId(stu_id);
//
//        Assert.assertEquals(2, resumeJsonList.size());
//        Assert.assertEquals(Integer.valueOf(1), resumeJsonList.get(0).getResume_id());
//    }
//
//    @Test
//    public void getByStuIdControllerTest() throws Exception {
//        Integer id = 1;
//
//
//        this.mvc.perform(
//                get(this.urlPrefix + "/applicants/" + id + "/resumes")//.param("id", "1").param("detailLevel", "1")
//                        .with(authGenerator.authentication())
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//        )
//                .andDo(print())
//        //     .andExpect(content().json("{\"content\":{\"id\":" +id+ ",\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
//        ;
//    }
//
//
//}