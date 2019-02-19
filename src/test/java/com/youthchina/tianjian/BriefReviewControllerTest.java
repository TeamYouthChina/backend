package com.youthchina.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.BriefReviewDTO;
import com.youthchina.dto.community.RequestBriefReviewDTO;
import com.youthchina.dto.community.RequestCommentDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:briefreview.xml","classpath:users.xml"})
@WebAppConfiguration
public class BriefReviewControllerTest {
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
    public void getBriefReviewTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
                }

    @Test
    public void deleteBriefReviewTest() throws Exception {
        this.mvc.perform(
                 delete(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void addBriefReviewTest() throws Exception {
        RequestBriefReviewDTO requestBriefReviewDTO = new RequestBriefReviewDTO();
        requestBriefReviewDTO.setBody("dsafsaf");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(requestBriefReviewDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void addBriefReviewCommentsTest() throws Exception {
       RequestCommentDTO requestCommentDTO = new RequestCommentDTO();
       requestCommentDTO.setBody("qqqrrr");
       requestCommentDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(requestCommentDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials/1/comments")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void addBriefReviewUpvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1/upvote")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print());

    }
}
