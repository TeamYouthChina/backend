package com.youthchina.Qinghong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.dto.*;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



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
    public void testGet() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"yihao guo\",\"avatarUrl\":null,\"educations\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null}],\"experiences\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"location\":\"中国江苏\",\"note\":null}],\"projects\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"extracurriculars\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2018-10-12T00:00:00.000-0400\"},\"location\":null,\"note\":null}],\"certificates\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"contacts\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]}},\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))

        ;
    }

    @Test
    public void testAdd() throws Exception{
        ApplicantDTO student=new ApplicantDTO();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        student.setName("qinghong wang");
        student.setAvatarUrl("www.baidu.com");
        student.setIsInJob("是");
        student.setCurrentCompanyName("YouthChina");
        //skill设置
        List<String> s=new ArrayList<>();
        s.add("11");
        student.setSkills(s);

        List<EducationDTO> educationDTOS=new ArrayList<>();
        EducationDTO educationDTO=new EducationDTO();
        educationDTO.setUniversity("gwu");
        educationDTO.setMajor("cs");
        Degree degree=new Degree();
        educationDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationDTO.setDuration(durationDTO);
        educationDTO.setLocation(locationDTO);
        educationDTOS.add(educationDTO);
        student.setEducations(educationDTOS);
        //联系信息
        List<String> emails=new ArrayList<>();
        List<String> phonenumbers=new ArrayList<>();
        emails.add("wangqinghong@gwu.edu");
        phonenumbers.add("5712188082");
        student.setEmails(emails);
        student.setPhonenumbers(phonenumbers);
        //工作信息
        //缺少地点
        List<WorkDTO> workDTOS=new ArrayList<>();
        WorkDTO workDTO=new WorkDTO();
        workDTO.setEmployer("google");
        workDTO.setPosition("backend");
        workDTO.setDuration(durationDTO);
        workDTO.setLocation(locationDTO);
        workDTOS.add(workDTO);
        student.setExperiences(workDTOS);
        //项目信息
        List<ProjectDTO> projectDTOS=new ArrayList<>();
        ProjectDTO projectDTO=new ProjectDTO();
        projectDTO.setName("create website");
        projectDTO.setRole("design web");
        projectDTO.setDuration(durationDTO);
        projectDTOS.add(projectDTO);
        student.setProjects(projectDTOS);
        //课外活动经历
        //缺少地点
        List<ExtracurricularDTO> extracurricularDTOS=new ArrayList<>();
        ExtracurricularDTO extracurricularDTO=new ExtracurricularDTO();
        extracurricularDTO.setName("volunteer");
        extracurricularDTO.setRole("help students");
        extracurricularDTO.setOrganization("儿童基金");
        extracurricularDTO.setDuration(durationDTO);
        extracurricularDTOS.add(extracurricularDTO);
        student.setExtracurriculars(extracurricularDTOS);
        //证书内容
        List<CertificateDTO> certificateDTOS=new ArrayList<>();
        CertificateDTO certificateDTO=new CertificateDTO();
        certificateDTO.setName("计算机证书");
        certificateDTO.setAuthority("教育部");
        certificateDTO.setDuration(durationDTO);
        certificateDTOS.add(certificateDTO);
        student.setCertificates(certificateDTOS);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(student);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;
    }

    @Test
    public void testGetContacts() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/contacts")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":{\"emails\":[\"test@test.com\"],\"phonenumbers\":[\"18463722634\"]},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetEducations() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/educations")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2020-05-14T00:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2020-05-14T00:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetProjects() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/projects")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
        ;
    }

    @Test
    public void testGetExperiences() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/experiences")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"location\":\"中国江苏\",\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetCertificates() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/certificates")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetExtracurriculars() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/extracurriculars")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetJobCollects() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/jobCollects",1).param("id", "2").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testGetCompCollects() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/companyCollects",1).param("id", "2").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testDeleteJobCollect() throws Exception{
        this.mvc.perform(delete(this.urlPrefix + "/jobs/collections/1").with(authGenerator.authentication()))
                .andDo(print());
    }
    @Test
    public void testDeleteCompCollect() throws Exception{
        this.mvc.perform
                (delete(this.urlPrefix + "/companies/collections/3")
                        .with(authGenerator.authentication()))
                .andDo(print());
    }

    /**
    * @Description: 通过user_id对于所有该用户下所有职位申请信息测试的完成
    * @Param: []
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2019/2/18
    */

    @Test
    public void testGetJobApplies() throws Exception{
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/applications",1).with(authGenerator.authentication()))
                .andDo(print());
    }
    /**
    * @Description: 对于职位申请的测试，在职位申请中还缺少对于简历是否成功发送的判断
    * @Param: []
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2019/2/18
    */

    @Test
    public void testAddJobApply() throws Exception{
        this.mvc.perform(post(this.urlPrefix + "/jobs/1/apply").with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testAddJobCollect() throws Exception{
        this.mvc.perform
                (put(this.urlPrefix + "/jobs/2/attention")
                .with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testAddCompCollect() throws Exception{
        this.mvc.perform
                (put(this.urlPrefix + "/companies/3/attention")
                        .with(authGenerator.authentication()))
                .andDo(print());
    }

    @Test
    public void testInsertEducation() throws Exception{
        EducationDTO educationDTO=new EducationDTO();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        educationDTO.setUniversity("gwu");
        educationDTO.setMajor("cs");
        Degree degree=new Degree();
        educationDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationDTO.setDuration(durationDTO);
        educationDTO.setLocation(locationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/educations").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testInsertWorks() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        List<WorkDTO> workDTOS=new ArrayList<>();
        WorkDTO workDTO=new WorkDTO();
        workDTO.setEmployer("google");
        workDTO.setPosition("backend");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        workDTO.setDuration(durationDTO);
        workDTO.setLocation(locationDTO);
        workDTOS.add(workDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/experiences").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testInsertProjects() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ProjectDTO projectDTO=new ProjectDTO();
        projectDTO.setName("create website");
        projectDTO.setRole("design web");
        projectDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/projects").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testInsertExtracurriculars() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ExtracurricularDTO extracurricularDTO=new ExtracurricularDTO();
        extracurricularDTO.setName("volunteer");
        extracurricularDTO.setRole("help students");
        extracurricularDTO.setOrganization("儿童基金");
        extracurricularDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(extracurricularDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/extracurriculars").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testInsertCertificates() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        CertificateDTO certificateDTO=new CertificateDTO();
        certificateDTO.setName("计算机证书");
        certificateDTO.setAuthority("教育部");
        certificateDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/certificates").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }










}
