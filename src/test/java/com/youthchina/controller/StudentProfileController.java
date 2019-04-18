package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.zhongyang.Role;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @program: youthchina
 * @description: 学生文档controller测试
 * @author: Qinghong Wang
 * @create: 2019-04-04 15:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class StudentProfileController {
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
                        .with(authGenerator.authentication(Role.APPLICANT, 1))
        )
                .andDo(print())
        //  .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"yihao guo\",\"avatarUrl\":null,\"educations\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null}],\"experiences\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"location\":\"中国江苏\",\"note\":null}],\"projects\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"extracurriculars\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2018-10-12T00:00:00.000-0400\"},\"location\":null,\"note\":null}],\"certificates\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"contacts\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]}},\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))

        ;
    }

    /**
     * @Description: 分页已完成测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/6
     */

    @Test
    public void testGetEducations() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/educations?limit=1&offset=0")
                        .with(authGenerator.authentication(Role.APPLICANT, 1))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2020-05-14T00:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2020-05-14T00:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    /**
     * @Description: 通过测试，location已删除
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/3/30
     */
    @Test
    public void testInsertEducation() throws Exception {
        EducationRequestDTO educationDTO = new EducationRequestDTO();
        educationDTO.setUniversity_id(10001);
        educationDTO.setMajor("10101");
        educationDTO.setDegree("2");
        String begin = "2018-10-09";
        String end = "2018-12-09";
        DurationDTO durationDTO = new DurationDTO(begin, end);
        educationDTO.setDuration(durationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/10/educations").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    /**
     * @Description: 已通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/3/30
     */

    @Test
    public void testUpdateEducation() throws Exception {
        EducationRequestDTO educationDTO = new EducationRequestDTO();
        educationDTO.setUniversity_id(10001);
        educationDTO.setMajor("10101");
        educationDTO.setDegree("1");
        String begin = "2014-10-07";
        String end = "2018-10-07";
        DurationDTO durationDTO = new DurationDTO(begin, end);
        educationDTO.setDuration(durationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(educationDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/applicants/10/educations/50").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteEducation() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/educations/10")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

//    @Test
//    public void testGetContacts() throws Exception{
//        this.mvc.perform(
//                get(this.urlPrefix + "/applicants/10/contacts")
//                        .with(authGenerator.authentication(Role.APPLICANT, 10))
//        )
//                .andDo(print())
////                .andExpect(content().json("{\"content\":{\"emails\":[\"test@test.com\"],\"phonenumbers\":[\"18463722634\"]},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
//        ;
//    }

    /**
     * @Description: 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/6
     */
    @Test
    public void testGetProjects() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/projects?limit=1&offset=0")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}",false))
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
    public void testInsertProjects() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        ProjectRequestDTO projectDTO = new ProjectRequestDTO();
        projectDTO.setName("create website");
        projectDTO.setRole("design web");
        projectDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/10/projects").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testUpdateProject() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        ProjectRequestDTO projectRequestDTO = new ProjectRequestDTO();
        projectRequestDTO.setId(6);
        projectRequestDTO.setName("做网站");
        projectRequestDTO.setRole("做网站");
        projectRequestDTO.setDuration(durationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(projectRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/10/projects/5").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteProject() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/projects/1")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    /**
     * @Description: 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/4
     */

    @Test
    public void testGetExperiences() throws Exception {

        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/experiences")
                        .with(authGenerator.authentication(Role.APPLICANT, 7))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"location\":\"中国江苏\",\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
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
    public void testInsertWorks() throws Exception {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setNation_code("440000");
        locationDTO.setLocation_code("");
        WorkRequestDTO workDTO = new WorkRequestDTO();
        workDTO.setEmployer("google");
        workDTO.setPosition("backend");
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        workDTO.setDuration(durationDTO);
        workDTO.setLocation(locationDTO);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/10/experiences").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/experiences")
                        .with(authGenerator.authentication(Role.APPLICANT, 7))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"location\":\"中国江苏\",\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

    }

    /**
     * @Description: 已通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/3/30
     */

    @Test
    public void testUpdateWork() throws Exception {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setNation_code("CHN");
        locationDTO.setLocation_code("210000");
        WorkRequestDTO workRequestDTO = new WorkRequestDTO();
        workRequestDTO.setId(6);
        workRequestDTO.setEmployer("amazon");
        workRequestDTO.setPosition("SDE");
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        workRequestDTO.setDuration(durationDTO);
        workRequestDTO.setLocation(locationDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(workRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/10/experiences/6").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteWork() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/experiences/1")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    /**
     * @Description: 通过测试，多了一个country的属性
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/6
     */

    @Test
    public void testGetCertificates() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/certificates")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000+0000\",\"end\":\"2018-10-11T00:00:00.000+0000\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
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
    public void testInsertCertificates() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        CertificateRequestDTO certificateDTO = new CertificateRequestDTO();
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
                        (this.urlPrefix + "/applicants/10/certificates").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testUpdateCertificate() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        CertificateRequestDTO certificateRequestDTO = new CertificateRequestDTO();
        certificateRequestDTO.setId(1);
        certificateRequestDTO.setName("计算机证书");
        certificateRequestDTO.setAuthority("教育部");
        certificateRequestDTO.setDuration(durationDTO);
        certificateRequestDTO.setCountry("111");

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(certificateRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                put
                        (this.urlPrefix + "/applicants/10/certificates/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteCertificate() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/certificates/1")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    @Test
    public void testGetExtracurriculars() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/extracurriculars")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
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
    public void testInsertExtracurriculars() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        ExtracurricularRequestDTO extracurricularDTO = new ExtracurricularRequestDTO();
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
                        (this.urlPrefix + "/applicants/10/extracurriculars").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    /**
     * @Description: 已通过数据
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/3/30
     */

    @Test
    public void testUpdateExtracurricular() throws Exception {
        long begin = 1111111;
        long end = 2222222;
        DurationDTO durationDTO = new DurationDTO(begin, end);
        ExtracurricularRequestDTO extracurricularRequestDTO = new ExtracurricularRequestDTO();
        extracurricularRequestDTO.setId(2);
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
                        (this.urlPrefix + "/applicants/10/extracurriculars/3").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteActivity() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/extracurriculars/1")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    @Test
    public void testGetSkills() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/10/skills")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetAllSkills() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/skills")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
//                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000+0000\",\"end\":\"2018-10-12T00:00:00.000+0000\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testInsertAdvantageSkill() throws Exception {
        SkillsRequestDTO skillsRequestDTO = new SkillsRequestDTO();
        skillsRequestDTO.setLabel_code("I6510");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(skillsRequestDTO);
        System.out.print(requestJson);
        this.mvc.perform(
                post
                        (this.urlPrefix + "/applicants/10/skills").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)

                        .with(authGenerator.authentication(Role.APPLICANT, 10))
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
    public void testDeleteSkill() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/applicants/10/skills/1")
                        .with(authGenerator.authentication(Role.APPLICANT, 10))
        )
                .andDo(print())
        ;

    }

    /**
     * @Description: 职位返回的地址为空！
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/4
     */

    @Test
    public void testUserAttentions() throws Exception {
        this.mvc.perform(
                get
                        (this.urlPrefix + "/users/1/attentions?type=job&limit=2&offset=0")

                        .with(authGenerator.authentication(Role.ADMIN, 1))
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
        ;

    }

    /**
     * @Description: 通过职位id添加职位收藏, 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/2/27
     */

    @Test
    public void testAddJobCollect() throws Exception {
        this.mvc.perform
                (put(this.urlPrefix + "/jobs/4/attention")
                        .with(authGenerator.authentication(Role.APPLICANT, 1)))
                .andDo(print());
    }

    /**
     * @Description: 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/4
     */

    @Test
    public void testAddCompCollect() throws Exception {
        this.mvc.perform
                (put(this.urlPrefix + "/companies/4/attention")
                        .with(authGenerator.authentication(Role.APPLICANT, 10)))
                .andDo(print());
    }

    /**
     * @Description: 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/3
     */

    @Test
    public void testDeleteJobCollect() throws Exception {
        this.mvc.perform(delete(this.urlPrefix + "/jobs/attentions/2").with(authGenerator.authentication(Role.APPLICANT, 10)))
                .andDo(print());
    }

    /**
     * @Description: 通过测试
     * @Param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/4/3
     */
    @Test
    public void testDeleteCompCollect() throws Exception {
        this.mvc.perform
                (delete(this.urlPrefix + "/companies/attentions/2")
                        .with(authGenerator.authentication(Role.APPLICANT, 10)))
                .andDo(print());
    }

    /**
     * @Description: 通过user_id对于所有该用户下所有职位申请信息测试的完成
     * param: []
     * @return: void
     * @Author: Qinghong Wang
     * @Date: 2019/2/18
     */

    @Test
    public void testGetJobApplies() throws Exception {
        this.mvc.perform(get(this.urlPrefix + "/applicants/{id}/applications", 10).with(authGenerator.authentication(Role.APPLICANT, 10)))
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
    public void testAddJobApply() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/jobs/4/apply").with(authGenerator.authentication(Role.APPLICANT, 10)))
                .andDo(print());
    }


}
