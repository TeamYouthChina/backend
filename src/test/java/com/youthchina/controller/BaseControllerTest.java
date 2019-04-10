package com.youthchina.controller;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by zhongyangwu on 4/9/19.
 */
public class BaseControllerTest {

    @Autowired
    protected WebApplicationContext context;

    @Autowired
    protected CommunityMapper communityMapper;

    @Value("${web.url.prefix}")
    protected String urlPrefix;

    protected AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }
}
