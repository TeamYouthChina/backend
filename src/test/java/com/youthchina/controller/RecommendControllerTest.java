package com.youthchina.controller;

import com.youthchina.controller.util.RecommendController;
import com.youthchina.controller.util.SearchController;
import com.youthchina.service.util.SearchServiceImplememt;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecommendControllerTest {
    @InjectMocks
    private RecommendController recommendController;

    @Mock
    private SearchController searchController;

    @Mock
    private SearchServiceImplememt searchServiceImplememt;

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
    public void TestRecommend() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
               .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendCompany() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/companies")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendUser() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/users")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendArticles() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/articles")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendQuestions() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/questions")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendJobs() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/jobs")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void TestRecommendEditorials() throws Exception {
        //Mockito.mock();
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/editorials")
                        .param("offset", "0")
                        .param("limit","12")
                        .param("page","1")
                        .with(authGenerator.authentication())
        ).andDo(print())
                .andExpect(status().is2xxSuccessful());

    }
}
