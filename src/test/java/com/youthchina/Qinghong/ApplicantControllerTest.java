//package com.youthchina.Qinghong;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectWriter;
//import com.github.springtestdbunit.DbUnitTestExecutionListener;
//import com.github.springtestdbunit.annotation.DatabaseSetup;
//import com.youthchina.domain.qingyang.Degree;
//import com.youthchina.domain.zhongyang.Role;
//import com.youthchina.dto.applicant.*;
//import com.youthchina.dto.util.DurationDTO;
//import com.youthchina.dto.util.LocationDTO;
//import com.youthchina.util.AuthGenerator;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//
//
///**
// * @program: youthchina
// * @description: 申请者controller 测试
// * @author: Qinghong Wang
// * @create: 2019-02-12 09:14
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
//@DatabaseSetup({"classpath:New_Stu_test.xml","classpath:New_Company_test.xml","classpath:New_Dictionary_test.xml","classpath:New_Job_test.xml","classpath:New_SYS_test.xml"})
//@WebAppConfiguration
//public class ApplicantControllerTest {
//    @Autowired
//    WebApplicationContext context;
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
//        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////    @Test
////    public void testGetJobCollects() throws Exception{
////        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/jobCollects",1).param("id", "2").with(authGenerator.authentication()))
////                .andDo(print());
////    }
////
////    @Test
////    public void testGetCompCollects() throws Exception{
////        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/companyCollects",1).param("id", "2").with(authGenerator.authentication()))
////                .andDo(print());
////    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//}
