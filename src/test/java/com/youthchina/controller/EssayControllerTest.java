package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.dto.community.EssayReplyDTO;
import com.youthchina.dto.community.QuestionDTO;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by hongshengzhang on 2/17/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:essay.xml"})
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
    public void getEssayTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"title\":\"title\",\"body\":\"WOSHI BODY\",\"company\":null,\"creat_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"user\":null,\"user_anony\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }



    @Test
    public void addEssayTest() throws Exception {
        RequestEssayDTO requestEssayDTO = new RequestEssayDTO();
        requestEssayDTO.setBody("This is a new article Body");
        requestEssayDTO.setTitle("This is a new article Title");
        //requestEssayDTO.setCompany_id(1);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void updateEssayTest() throws Exception {
        RequestEssayDTO requestEssayDTO = new RequestEssayDTO();
        requestEssayDTO.setBody("This is a new Body");
        requestEssayDTO.setTitle("This is a new Title");


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(

                put(this.urlPrefix + "/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                .with(authGenerator.authentication())

        )
                .andDo(print());

    }



    @Test
    public void deleteEssayTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
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
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }
}