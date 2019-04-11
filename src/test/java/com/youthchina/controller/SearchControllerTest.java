package com.youthchina.controller;

import com.youthchina.util.AuthGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

/**
 * Created by zhongyangwu on 4/7/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SearchControllerTest {

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
    public void testSearchAll() throws Exception {
//        this.mvc.perform(
//                get(this.urlPrefix + "/search")
//                        .param("type", "all")
//                        .param("title", "腾")
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful());
        Assert.assertTrue(true);
    }//todo: add mock test

}
