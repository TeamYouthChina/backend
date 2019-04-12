package com.youthchina.Qingyang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.ResumeJsonMapper;
import com.youthchina.domain.jinhao.Answer;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.applicant.ResumeJsonRequestDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.AnswerServiceImpl;
import com.youthchina.service.jinhao.QuestionServiceImpl;
import com.youthchina.service.qingyang.ResumeJsonServiceImpl;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.zhongyang.JwtService;
import com.youthchina.util.AuthGenerator;
import com.youthchina.util.zhongyang.JwtAuthenticationProvider;
import org.junit.Assert;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
/**
 * @author: Qingyang Zhao
 * @create: 2019-04-11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ResumeJsonTest {
    @Autowired
    WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

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
    ResumeJsonMapper resumeJsonMapper;
    @Autowired
    ResumeJsonServiceImpl resumeJsonService;

    private AuthGenerator authGenerator = new AuthGenerator();

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testResumeJsonMapper(){
        ResumeJson resumeJson = new ResumeJson();
        resumeJson.setJsonContent("TestContent");
        resumeJson.setUserId(1);
        resumeJsonMapper.insertResumeJson(resumeJson);
        resumeJson = resumeJsonMapper.selectResumeJson(resumeJson.getResumeId());
        Assert.assertEquals("TestContent", resumeJson.getJsonContent());
        resumeJson.setJsonContent("TestUpdate");
        resumeJsonMapper.updateResumeJson(resumeJson);
        resumeJson = resumeJsonMapper.selectResumeJson(resumeJson.getResumeId());
        Assert.assertEquals("TestUpdate", resumeJson.getJsonContent());
        resumeJsonMapper.deleteResumeJson(resumeJson.getResumeId());
        resumeJson = resumeJsonMapper.selectResumeJson(resumeJson.getResumeId());
        Assert.assertEquals(null, resumeJson);

        resumeJson = new ResumeJson();
        resumeJson.setJsonContent("TestContent");
        resumeJson.setUserId(1);
        resumeJsonMapper.insertResumeJson(resumeJson);
        Integer id1 = resumeJson.getResumeId();
        resumeJsonMapper.insertResumeJson(resumeJson);
        Integer id2 = resumeJson.getResumeId();
        List<Integer> idList = new ArrayList<>();
        idList.add(id1);
        idList.add(id2);
        List<ResumeJson> resumeJsonList = resumeJsonMapper.selectResumeJsonByIdList(idList);
        Assert.assertEquals(2, resumeJsonList.size());

    }


    @Test
    public void testResumeJsonPost() throws Exception {
        Integer id = 1;
        ResumeJsonRequestDTO resumeJsonRequestDTO = new ResumeJsonRequestDTO();
        resumeJsonRequestDTO.setResume("TestPost");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String insertJson = ow.writeValueAsString(resumeJsonRequestDTO);
        System.out.println(insertJson);

        this.mvc.perform(
                post(this.urlPrefix + "/resumes/online")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(insertJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jsons\":[\"TestPost\"]},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;
    }

    @Test
    public void testResumeJsonGetDelete() throws Exception{
        Integer id = 1;
        ResumeJson resumeJson = new ResumeJson();
        resumeJson.setJsonContent("TestContent");
        resumeJson.setUserId(id);
        resumeJsonMapper.insertResumeJson(resumeJson);
        this.mvc.perform(
                get(this.urlPrefix + "/resumes/online/" + resumeJson.getId())
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jsons\":[\"TestContent\"]},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

        this.mvc.perform(
                delete(this.urlPrefix + "/resumes/online/" + resumeJson.getId())
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
        ;

        this.mvc.perform(
                get(this.urlPrefix + "/resumes/online/" + resumeJson.getId())
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4040,\"reason\":\"ResumeJson Not Found\"}}", false))
        ;
    }


}
