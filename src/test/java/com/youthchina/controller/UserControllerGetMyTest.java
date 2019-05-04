package com.youthchina.controller;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.service.community.AnswerServiceImpl;
import com.youthchina.service.community.EssayServiceImpl;
import com.youthchina.service.community.InfluenceService;
import com.youthchina.service.community.QuestionServiceImpl;
import com.youthchina.service.user.JwtService;
import com.youthchina.util.AuthGenerator;
import com.youthchina.util.JwtAuthenticationProvider;
import com.youthchina.util.dictionary.SearchType;
import org.junit.Assert;
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

import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * @author: Qingyang Zhao
 * @create: 2019-04-10
 **/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class UserControllerGetMyTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mvc;
    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    JwtService jwtService;
    @Autowired
    QuestionServiceImpl questionService;
    @Autowired
    AnswerServiceImpl answerService;
    @Autowired
    EssayServiceImpl essayService;
    @Autowired
    InfluenceService influenceService;
    @Value("${web.url.prefix}")
    private String urlPrefix;
    private AuthGenerator authGenerator = new AuthGenerator();

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void getMy() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix +  "/my")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        //No target type
        ;
    }

    @Test
    public void getPageTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/my")
                        .param("type", SearchType.ARTICLE)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
        ;
    }

    @Test
    public void getMyQuestionServiceTest() throws Exception {
        Integer id = 1;
        List<Question> questionList = questionService.getMyQuestion(id);
        Assert.assertEquals(49, questionList.size());
    }

    @Test
    public void getMyAnswerServiceTest() throws Exception {
        Integer id = 1;
        List<Answer> answerList = answerService.getMyAnswers(id);
        Assert.assertEquals(0, answerList.size());

        id = 5;
        answerList = answerService.getMyAnswers(id);
        Assert.assertEquals(13, answerList.size());

    }

    @Test
    public void getMyEssayServiceTest() throws Exception {
        Integer id = 1;
        List<ComEssay> comEssayList = essayService.getAllEssayByUserId(id);
        Assert.assertEquals(6, comEssayList.size());
//        ComEssay comEssay1 = comEssayList.get(0);
//        EssayResponseDTO essayResponseDTO = new EssayResponseDTO();
//        essayResponseDTO.convertToDTO(comEssay1);
    }

//    @Test
//    public void getMyNone() throws Exception {
//        Integer id = 2;
//        this.mvc.perform(
//                get(this.urlPrefix + "/users/:" + id + "/my")
//                        .contentType(MediaType.APPLICATION_JSON_UTF8)
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print())
//                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4030,\"reason\":\"Cannot access\"}}", false))
//        ;
//    }

    @Test
    public void getMyInfluence() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/users/1/influence")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
    }
}