package com.youthchina.Qinghong;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.dto.applicant.*;
import com.youthchina.dto.util.DurationDTO;
import com.youthchina.dto.util.LocationDTO;
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
@DatabaseSetup({"classpath:testnew.xml","classpath:New_Company_test.xml","classpath:New_Dictionary_test.xml"})
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
    /**
    * @Description: 获取申请者的所有信息
    * @Param: []
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2019/2/27
    */

    @Test
    public void testGet() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
      //  .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"yihao guo\",\"avatarUrl\":null,\"educations\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null}],\"experiences\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"location\":\"中国江苏\",\"note\":null}],\"projects\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"extracurriculars\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2018-10-12T00:00:00.000-0400\"},\"location\":null,\"note\":null}],\"certificates\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"contacts\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]}},\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))

        ;
    }

    /**
    * @Description: 全部添加申请者的所有信息
    * @Param: []
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2019/2/27
    */

    @Test
    public void testAdd() throws Exception{
        ApplicantRequestDTO student=new ApplicantRequestDTO();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        student.setName("qinghong wang");
        student.setAvatarUrl("www.baidu.com");
        student.setIsInJob(true);
        student.setCurrentCompanyId(1);
        //skill设置
        List<String> s=new ArrayList<>();
        s.add("11");
        student.setSkills(s);

        List<EducationRequestDTO> educationRequestDTOS =new ArrayList<>();
        EducationRequestDTO educationRequestDTO =new EducationRequestDTO();
        educationRequestDTO.setUniversity_id(1);
        educationRequestDTO.setMajor("cs");
        Degree degree=new Degree();
        educationRequestDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationRequestDTO.setDuration(durationDTO);
        educationRequestDTO.setLocation(locationDTO);
        educationRequestDTOS.add(educationRequestDTO);
        student.setEducations(educationRequestDTOS);
        //联系信息
        List<String> emails=new ArrayList<>();
        List<String> phonenumbers=new ArrayList<>();
        emails.add("wangqinghong@gwu.edu");
        phonenumbers.add("5712188082");
        ContactDTO contactDTO=new ContactDTO();
        contactDTO.setEmails(emails);
        contactDTO.setPhonenumbers(phonenumbers);

        student.setContacts(contactDTO);
        //工作信息
        //缺少地点
        List<WorkRequestDTO> workRequestDTOS =new ArrayList<>();
        WorkRequestDTO workRequestDTO =new WorkRequestDTO();
        workRequestDTO.setEmployer("google");
        workRequestDTO.setPosition("backend");
        workRequestDTO.setDuration(durationDTO);
        workRequestDTO.setLocation(locationDTO);
        workRequestDTOS.add(workRequestDTO);
        student.setExperiences(workRequestDTOS);
        //项目信息
        List<ProjectRequestDTO> projectRequestDTOS =new ArrayList<>();
        ProjectRequestDTO projectRequestDTO =new ProjectRequestDTO();
        projectRequestDTO.setName("create website");
        projectRequestDTO.setRole("design web");
        projectRequestDTO.setDuration(durationDTO);
        projectRequestDTOS.add(projectRequestDTO);
        student.setProjects(projectRequestDTOS);
        //课外活动经历
        //缺少地点
        List<ExtracurricularRequestDTO> extracurricularRequestDTOS =new ArrayList<>();
        ExtracurricularRequestDTO extracurricularRequestDTO =new ExtracurricularRequestDTO();
        extracurricularRequestDTO.setName("volunteer");
        extracurricularRequestDTO.setRole("help students");
        extracurricularRequestDTO.setOrganization("儿童基金");
        extracurricularRequestDTO.setDuration(durationDTO);
        extracurricularRequestDTOS.add(extracurricularRequestDTO);
        student.setExtracurriculars(extracurricularRequestDTOS);
        //证书内容
        List<CertificateRequestDTO> certificateRequestDTOS =new ArrayList<>();
        CertificateRequestDTO certificateRequestDTO =new CertificateRequestDTO();
        certificateRequestDTO.setName("计算机证书");
        certificateRequestDTO.setAuthority("教育部");
        certificateRequestDTO.setDuration(durationDTO);
        certificateRequestDTOS.add(certificateRequestDTO);
        student.setCertifications(certificateRequestDTOS);
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
    public void testGetSkills()throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/skills")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetAllSkills()throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/skills")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }


//    @Test
//    public void testGetJobCollects() throws Exception{
//        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/jobCollects",1).param("id", "2").with(authGenerator.authentication()))
//                .andDo(print());
//    }
//
//    @Test
//    public void testGetCompCollects() throws Exception{
//        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/companyCollects",1).param("id", "2").with(authGenerator.authentication()))
//                .andDo(print());
//    }

    @Test
    public void testDeleteJobCollect() throws Exception{
        this.mvc.perform(delete(this.urlPrefix + "/jobs/attentions/1").with(authGenerator.authentication()))
                .andDo(print());
    }
    @Test
    public void testDeleteCompCollect() throws Exception{
        this.mvc.perform
                (delete(this.urlPrefix + "/companies/attentions/1")
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
        this.mvc.perform(post(this.urlPrefix + "/jobs/3/apply").with(authGenerator.authentication()))
                .andDo(print());
    }

    /**
    * @Description: 通过职位id添加职位收藏
    * @Param: []
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2019/2/27
    */

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
                (put(this.urlPrefix + "/companies/2/attention")
                        .with(authGenerator.authentication()))
                .andDo(print());
    }


    /** 
    * @Description: 通过测试，还需核对接口 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testInsertEducation() throws Exception{
        EducationRequestDTO educationDTO=new EducationRequestDTO();
        educationDTO.setUniversity_id(10001);
        educationDTO.setMajor("cs");
        Degree degree=new Degree();
        educationDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationDTO.setDuration(durationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/education").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    

    /** 
    * @Description: 通过测试，还需核对接口 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testInsertWorks() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("CHN");
        locationDTO.setLocation_code("110000");
        List<WorkRequestDTO> workDTOS=new ArrayList<>();
        WorkRequestDTO workDTO=new WorkRequestDTO();
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
                        (this.urlPrefix + "/applicants/1/experience").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    /** 
    * @Description: 通过测试，还需核对接口 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testInsertProjects() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ProjectRequestDTO projectDTO=new ProjectRequestDTO();
        projectDTO.setName("create website");
        projectDTO.setRole("design web");
        projectDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/project").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    /** 
    * @Description: 通过测试，还需核对接口 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testInsertExtracurriculars() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ExtracurricularRequestDTO extracurricularDTO=new ExtracurricularRequestDTO();
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
                        (this.urlPrefix + "/applicants/1/extracurricular").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    
    

    /** 
    * @Description: 通过测试，还需核对接口
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testInsertCertificates() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        CertificateRequestDTO certificateDTO=new CertificateRequestDTO();
        certificateDTO.setName("计算机证书");
        certificateDTO.setAuthority("教育部");
        certificateDTO.setCountry("CHN");
        certificateDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/certificate").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testInsertAdvantageSkill() throws Exception{
        SkillsRequestDTO skillsRequestDTO=new SkillsRequestDTO();
        skillsRequestDTO.setLabel_code("22");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(skillsRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/skill").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;
    }

    @Test
    public void testSaveEducations() throws Exception{
        List<EducationRequestDTO> educationRequestDTOS =new ArrayList<>();
        EducationRequestDTO educationRequestDTO =new EducationRequestDTO();
        EducationRequestDTO educationRequestDTO1 =new EducationRequestDTO();
        educationRequestDTO1.setUniversity_id(1);
        educationRequestDTO1.setMajor("Engineer");
        educationRequestDTO1.setDegree("1");


        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        educationRequestDTO.setUniversity_id(1);
        educationRequestDTO.setMajor("Engineer");
        educationRequestDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;

        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationRequestDTO.setDuration(durationDTO);
        educationRequestDTO.setLocation(locationDTO);
        educationRequestDTO1.setDuration(durationDTO);
        educationRequestDTO1.setLocation(locationDTO);
        educationRequestDTOS.add(educationRequestDTO);
        educationRequestDTOS.add(educationRequestDTO1);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationRequestDTOS);
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
    public void testSaveWorks() throws Exception{
        List<WorkRequestDTO> workRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        WorkRequestDTO workRequestDTO =new WorkRequestDTO();
        workRequestDTO.setEmployer("google");
        workRequestDTO.setPosition("backend");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        workRequestDTO.setDuration(durationDTO);
        workRequestDTO.setLocation(locationDTO);
        workRequestDTOS.add(workRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/works").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testSaveProjects() throws Exception{
        List<ProjectRequestDTO> projectRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ProjectRequestDTO projectRequestDTO =new ProjectRequestDTO();
        projectRequestDTO.setName("create website");
        projectRequestDTO.setRole("design web");
        projectRequestDTO.setDuration(durationDTO);
        projectRequestDTOS.add(projectRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectRequestDTOS);
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
    public void testSaveExtracurriculars() throws Exception{
        List<ExtracurricularRequestDTO> extracurricularRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ExtracurricularRequestDTO extracurricularRequestDTO =new ExtracurricularRequestDTO();
        extracurricularRequestDTO.setName("volunteer");
        extracurricularRequestDTO.setRole("help students");
        extracurricularRequestDTO.setOrganization("儿童基金");
        extracurricularRequestDTO.setDuration(durationDTO);
        extracurricularRequestDTOS.add(extracurricularRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(extracurricularRequestDTOS);
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
    public void testSaveCertificates() throws Exception{
        List<CertificateRequestDTO> certificateRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        CertificateRequestDTO certificateRequestDTO =new CertificateRequestDTO();
        certificateRequestDTO.setName("计算机证书");
        certificateRequestDTO.setAuthority("教育部");
        certificateRequestDTO.setDuration(durationDTO);
        certificateRequestDTOS.add(certificateRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateRequestDTOS);
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
    @Test
    public void testSaveSkills() throws Exception{
        List<String> strings=new ArrayList<>();
        strings.add("33");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(strings);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/1/skills").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testUpdateEducations() throws Exception{
        List<EducationRequestDTO> educationRequestDTOS =new ArrayList<>();
        EducationRequestDTO educationRequestDTO =new EducationRequestDTO();
        EducationRequestDTO educationRequestDTO1 =new EducationRequestDTO();
        educationRequestDTO1.setUniversity_id(1);
        educationRequestDTO1.setMajor("Engineer");
        educationRequestDTO1.setDegree("1");


        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        educationRequestDTO.setUniversity_id(1);
        educationRequestDTO.setMajor("Engineer");
        educationRequestDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationRequestDTO.setDuration(durationDTO);
        educationRequestDTO.setLocation(locationDTO);
        educationRequestDTO1.setDuration(durationDTO);
        educationRequestDTO1.setLocation(locationDTO);
        educationRequestDTOS.add(educationRequestDTO);
        educationRequestDTOS.add(educationRequestDTO1);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/educations").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testUpdateWorks() throws Exception{
        List<WorkRequestDTO> workRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        WorkRequestDTO workRequestDTO =new WorkRequestDTO();
        workRequestDTO.setEmployer("google");
        workRequestDTO.setPosition("backend");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        workRequestDTO.setDuration(durationDTO);
        workRequestDTO.setLocation(locationDTO);
        workRequestDTOS.add(workRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/works").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testUpdateProjects() throws Exception{
        List<ProjectRequestDTO> projectRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ProjectRequestDTO projectRequestDTO =new ProjectRequestDTO();
        projectRequestDTO.setName("create website");
        projectRequestDTO.setRole("design web");
        projectRequestDTO.setDuration(durationDTO);
        projectRequestDTOS.add(projectRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/projects").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testUpdateExtracurriculars() throws Exception{
        List<ExtracurricularRequestDTO> extracurricularRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ExtracurricularRequestDTO extracurricularRequestDTO =new ExtracurricularRequestDTO();
        extracurricularRequestDTO.setName("volunteer");
        extracurricularRequestDTO.setRole("help students");
        extracurricularRequestDTO.setOrganization("儿童基金");
        extracurricularRequestDTO.setDuration(durationDTO);
        extracurricularRequestDTOS.add(extracurricularRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(extracurricularRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/extracurriculars").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testUpdateCertificates() throws Exception{
        List<CertificateRequestDTO> certificateRequestDTOS =new ArrayList<>();
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("USA");
        locationDTO.setLocation_code("920001");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        CertificateRequestDTO certificateRequestDTO =new CertificateRequestDTO();
        certificateRequestDTO.setName("计算机证书");
        certificateRequestDTO.setAuthority("教育部");
        certificateRequestDTO.setDuration(durationDTO);
        certificateRequestDTOS.add(certificateRequestDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateRequestDTOS);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/certificates").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    @Test
    public void testUpdateSkills() throws Exception{
        List<String> strings=new ArrayList<>();
        strings.add("11");

        strings.add("22");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(strings);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/skills").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }

    @Test
    public void testUserAttentions() throws Exception{
        this.mvc.perform(
                get
                        (this.urlPrefix + "/users/1/attentions").param("type","Company")

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;
    }
//
//    @Test
//    public void testSendingEmail() throws Exception{
//        File file = new ClassPathResource("hjh_hw2.pdf").getFile();
//        //文件之外的参数
//        MockMultipartFile firstFile = new MockMultipartFile("file", "hjh_hw2_PART1.pdf",
//                MediaType.TEXT_PLAIN_VALUE, new FileInputStream(file));
//
//
//
//        this.mvc.perform(MockMvcRequestBuilders
//                .multipart(this.urlPrefix + "/jobs/1/apply/sendingemail")
//                .file(firstFile)
//                .with(authGenerator.authentication())
//        ).andDo(print());
//    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testDeleteEducation() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/educations/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testDeleteWork() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/experiences/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testDeleteProject() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/projects/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testDeleteActivity() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/extracurriculars/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testDeleteCertificate() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/certificates/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 通过测试 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/4/3 
    */

    @Test
    public void testDeleteSkill() throws Exception{
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/1/skills/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    
    /** 
    * @Description: 已通过测试，差数据层更改 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testUpdateEducation() throws Exception{
        EducationRequestDTO educationDTO=new EducationRequestDTO();
        educationDTO.setId(1);
        educationDTO.setUniversity_id(10001);
        educationDTO.setMajor("law");
        Degree degree=new Degree();
        educationDTO.setDegree("1");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        educationDTO.setDuration(durationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/applicants/1/education/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 已通过测试，差数据校对 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testUpdateWork() throws Exception{
        LocationDTO locationDTO=new LocationDTO();
        locationDTO.setNation_code("CHN");
        locationDTO.setLocation_code("110000");
        WorkRequestDTO workRequestDTO =new WorkRequestDTO();
        workRequestDTO.setId(1);
        workRequestDTO.setEmployer("amazon");
        workRequestDTO.setPosition("SDE");
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        workRequestDTO.setDuration(durationDTO);
        workRequestDTO.setLocation(locationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/work/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 已通过测试，差数据校对 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testUpdateProject() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ProjectRequestDTO projectRequestDTO =new ProjectRequestDTO();
        projectRequestDTO.setId(1);
        projectRequestDTO.setName("做网站");
        projectRequestDTO.setRole("做网站");
        projectRequestDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/project/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    /** 
    * @Description: 已通过数据，差测试数据 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */

    @Test
    public void testUpdateExtracurricular() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        ExtracurricularRequestDTO extracurricularRequestDTO =new ExtracurricularRequestDTO();
        extracurricularRequestDTO.setId(1);
        extracurricularRequestDTO.setName("volunteer");
        extracurricularRequestDTO.setRole("help students");
        extracurricularRequestDTO.setOrganization("儿童基金组织");
        extracurricularRequestDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(extracurricularRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/extracurricular/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }
    
    /** 
    * @Description: 已通过数据，差测试数据 
    * @Param: [] 
    * @return: void 
    * @Author: Qinghong Wang 
    * @Date: 2019/3/30 
    */
    @Test
    public void testUpdateCertificate() throws Exception{
        long begin=1111111;
        long end=2222222;
        DurationDTO durationDTO=new DurationDTO(begin,end);
        CertificateRequestDTO certificateRequestDTO =new CertificateRequestDTO();
        certificateRequestDTO.setId(1);
        certificateRequestDTO.setName("计算机证书");
        certificateRequestDTO.setAuthority("教育部");
        certificateRequestDTO.setDuration(durationDTO);
        certificateRequestDTO.setCountry("CHN");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/1/certificate/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;

    }












}
