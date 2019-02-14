package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.dto.JobSearchDTO;
import com.youthchina.dto.community.QuestionDTO;
import com.youthchina.util.AuthGenerator;
import org.apache.commons.collections.map.HashedMap;
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
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by hongshengzhang on 2/10/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml"})
@DatabaseSetup({"classpath:answers.xml"})
@WebAppConfiguration
public class QuestionControllerTest {
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
    public void addQuestionTest() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle("Question No.100");
        questionDTO.setBody("Body of the question No.100");
        questionDTO.setAbbreviation("Abbreviation of the question No.100");
        questionDTO.setRela_type(2);
        questionDTO.setRela_id(2);
        questionDTO.setCreateAt(new Timestamp(System.currentTimeMillis()));
        questionDTO.setEditAt(new Timestamp(System.currentTimeMillis()));
        questionDTO.setAnonymous(0);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionDTO);

        this.mvc.perform(

                post(this.urlPrefix + "/questions").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }


    @Test
    public void getQuestionTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/1").param("Id", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"title\":\"第一个问题\",\"body\":\"第一个问题的正文\",\"createAt\":\"2018-12-04T13:32:40.000+0000\",\"editAt\":\"2018-12-04T13:32:40.000+0000\",\"answers\":null,\"invitation\":null,\"labelIds\":null,\"rela_type\":1,\"rela_id\":null,\"abbreviation\":\"第一个问题的描述\",\"anonymous\":null},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void getQuestionsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions").param("companyName", "百度").param("jobName", "front")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"ques_id\":4,\"ques_title\":\"第四个问题\",\"ques_abbre\":\"第四个问题的描述\",\"ques_body\":\"第四个问题的正文\",\"ques_pub_time\":\"2018-12-06T14:32:40.000+0000\",\"ques_edit_time\":\"2018-12-06T14:32:40.000+0000\",\"is_delete\":0,\"is_delete_time\":\"2018-12-06T14:32:40.000+0000\",\"user_anony\":null,\"ques_user\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"questionAttentions\":[],\"questionAnswers\":[],\"labels\":[],\"ques_invitation\":null,\"labelIds\":null,\"rela_type\":null,\"rela_id\":null,\"id\":4},{\"ques_id\":10,\"ques_title\":\"第十个问题\",\"ques_abbre\":\"第十个问题的描述\",\"ques_body\":\"第十个问题的正文\",\"ques_pub_time\":\"2018-12-06T13:32:40.000+0000\",\"ques_edit_time\":\"2018-12-06T13:32:40.000+0000\",\"is_delete\":0,\"is_delete_time\":\"2018-12-06T13:32:40.000+0000\",\"user_anony\":null,\"ques_user\":{\"id\":2,\"username\":\"zhid d\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"questionAttentions\":[],\"questionAnswers\":[],\"labels\":[],\"ques_invitation\":null,\"labelIds\":null,\"rela_type\":null,\"rela_id\":null,\"id\":10}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false));

    }

    @Test
    public void deleteQuestionTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/questions/1").param("Id", "1")
                        .with(authGenerator.authentication())

        );

        this.mvc.perform(
                get(this.urlPrefix + "/questions/1").param("Id", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":404,\"reason\":\"没有找到这个问题\"}}", false));

    }

    @Test
    public void updateQuestionTest() throws Exception {
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setTitle("Question No.100");
        questionDTO.setBody("Body of the question No.100");
        questionDTO.setAbbreviation("Abbreviation of the question No.100");
        questionDTO.setRela_type(2);
        questionDTO.setRela_id(2);
        questionDTO.setCreateAt(new Timestamp(System.currentTimeMillis()));
        questionDTO.setEditAt(new Timestamp(System.currentTimeMillis()));
        questionDTO.setAnonymous(0);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionDTO);

        this.mvc.perform(

                put(this.urlPrefix + "/questions/2").param("Id", "2").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        );

        this.mvc.perform(
                get(this.urlPrefix + "/questions/2").param("Id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
//                .andExpect(content().json("{\"content\":{\"id\":2,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"title\":\"Question No.100\",\"body\":\"Body of the question No.100\",\"createAt\":\"2018-12-05T13:32:40.000+0000\",\"editAt\":\"2019-02-14T16:50:22.000+0000\",\"answers\":null,\"invitation\":null,\"labelIds\":null,\"rela_type\":3,\"rela_id\":null,\"abbreviation\":\"Abbreviation of the question No.100\",\"anonymous\":null},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void getAnswerTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2/answers").param("Id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":[{\"id\":5,\"creator\":{\"id\":4,\"username\":\"zhid d\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"body\":\"这是第五个回答\",\"isAnonymous\":false,\"creatAt\":\"2018-12-04T13:32:40.000+0000\"}],\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }


    @Test
    public void sendInviteTest() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/questions/2/invite/2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
//                .andExpect(content().json("{\"content\":[{\"ques_id\":4,\"ques_title\":\"第四个问题\",\"ques_abbre\":\"第四个问题的描述\",\"ques_body\":\"第四个问题的正文\",\"ques_pub_time\":\"2018-12-06T14:32:40.000+0000\",\"ques_edit_time\":\"2018-12-06T14:32:40.000+0000\",\"is_delete\":0,\"is_delete_time\":\"2018-12-06T14:32:40.000+0000\",\"user_anony\":null,\"ques_user\":{\"id\":1,\"username\":\"yihao guo\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"questionAttentions\":[],\"questionAnswers\":[],\"labels\":[],\"ques_invitation\":null,\"labelIds\":null,\"rela_type\":null,\"rela_id\":null,\"id\":4},{\"ques_id\":10,\"ques_title\":\"第十个问题\",\"ques_abbre\":\"第十个问题的描述\",\"ques_body\":\"第十个问题的正文\",\"ques_pub_time\":\"2018-12-06T13:32:40.000+0000\",\"ques_edit_time\":\"2018-12-06T13:32:40.000+0000\",\"is_delete\":0,\"is_delete_time\":\"2018-12-06T13:32:40.000+0000\",\"user_anony\":null,\"ques_user\":{\"id\":2,\"username\":\"zhid d\",\"email\":null,\"phonenumber\":\"18463722634\",\"registerDate\":null,\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":null,\"age\":21},\"questionAttentions\":[],\"questionAnswers\":[],\"labels\":[],\"ques_invitation\":null,\"labelIds\":null,\"rela_type\":null,\"rela_id\":null,\"id\":10}],\"status\":{\"code\":2000,\"reason\":\"\"}}\n", false));

    }

}
