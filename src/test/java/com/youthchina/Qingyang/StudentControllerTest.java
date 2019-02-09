package com.youthchina.Qingyang;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:applicant.xml"})
@WebAppConfiguration
public class StudentControllerTest {
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
    public void testGet() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"yihao guo\",\"avatarUrl\":null,\"educations\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null}],\"experiences\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"location\":\"中国江苏\",\"note\":null}],\"projects\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"extracurriculars\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2018-10-12T00:00:00.000-0400\"},\"location\":null,\"note\":null}],\"certificates\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"contacts\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]}},\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))
                //.andExpect(content().json("{\"content\":{\"id\":1,\"name\":\"yihao guo\",\"avatarUrl\":null,\"educations\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T04:00:00.000+0000\",\"end\":\"2020-05-14T04:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T04:00:00.000+0000\",\"end\":\"2020-05-14T04:00:00.000+0000\"},\"location\":{\"region_num\":null},\"note\":null}],\"experiences\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T04:00:00.000+0000\",\"end\":\"2018-10-11T04:00:00.000+0000\"},\"location\":\"中国江苏\",\"note\":null}],\"projects\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T04:00:00.000+0000\",\"end\":\"2018-10-11T04:00:00.000+0000\"},\"note\":null}],\"extracurriculars\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T04:00:00.000+0000\",\"end\":\"2018-10-12T04:00:00.000+0000\"},\"location\":null,\"note\":null}],\"certificates\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T04:00:00.000+0000\",\"end\":\"2018-10-11T04:00:00.000+0000\"},\"note\":null}],\"contacts\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]}},\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))

        ;
    }

    @Test
    public void testGetContacts() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/contacts")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"emails\":[null],\"phonenumbers\":[\"18463722634\"]},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testGetEducations() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/educations")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null},{\"university\":\"CSSA\",\"major\":\"1\",\"degree\":\"1\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2020-05-14T00:00:00.000-0400\"},\"location\":{\"region_num\":null},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))
        ;
    }

    @Test
    public void testGetProjects() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/projects")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"name\":\"web develop\",\"role\":\"backend\",\"duration\":{\"begin\":\"2018-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n",false))
        ;
    }

    @Test
    public void testGetExperiences() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/experiences")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"employer\":\"Facebook\",\"position\":\"SDE\",\"duration\":{\"begin\":\"2017-09-11T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"location\":\"中国江苏\",\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))
        ;
    }

    @Test
    public void testGetCertificates() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/certificates")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"name\":\"Java skill\",\"authority\":\"CSSA\",\"duration\":{\"begin\":\"2016-10-01T00:00:00.000-0400\",\"end\":\"2018-10-11T00:00:00.000-0400\"},\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))
        ;
    }

    @Test
    public void testGetExtracurriculars() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/applicants/1/extracurriculars")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"name\":\"volunteer\",\"role\":\"worker\",\"organization\":\"gwu\",\"duration\":{\"begin\":\"2018-10-11T00:00:00.000-0400\",\"end\":\"2018-10-12T00:00:00.000-0400\"},\"location\":null,\"note\":null}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false))
        ;
    }

    @Test
    public void testPost() throws Exception{
        this.mvc.perform(post(this.urlPrefix + "/login").param("id", "1").param("password", "123456"))
                .andDo(print());
//        this.mvc.perform(post(this.urlPrefix + "/applicants"))
//                .andDo(print());
    }


}
