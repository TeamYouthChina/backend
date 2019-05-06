package com.youthchina.controller;

import com.youthchina.service.user.JwtService;
import com.youthchina.util.AuthGenerator;
import com.youthchina.util.JwtAuthenticationProvider;
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

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by zhongyangwu on 1/2/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class UserControllerTest extends BaseControllerTest {
    @Autowired
    WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    MockMvc mvc;

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    JwtService jwtService;
    private AuthGenerator authGenerator = new AuthGenerator();

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    public void testLogin() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/login").contentType(MediaType.APPLICATION_JSON_UTF8).content("{\n" +
                "  \"id\": 2,\n" +
                "  \"password\": \"123456\"\n" +
                "}"))
                .andDo(print())
                .andExpect(content().json(readJson("responses/post-login.json"), false))
                .andExpect(header().exists("X-AUTHENTICATION"));
    }

    @Test
    public void testRegister() throws Exception {
        this.mvc.perform(post(this.urlPrefix + "/applicants/register")
                .content("{\n" +
                        "  \"email\": \"string@string.com\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"gender\": \"MALE\",\n" +
                        "  \"dateOfBirth\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(partialContent("{\"content\":{\"id\":19,\"email\":\"string@string.com\",\"register_date\":1557120136000,\"date_of_birth\":0,\"first_name\":\"string\",\"last_name\":\"string\",\"gender\":\"male\",\"nation\":\"CHN\",\"avatar_url\":null,\"role\":[\"APPLICANT\"],\"phone_number\":\"000000000\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", "$.content.id", "$.content.register_date"))
        ;

        this.mvc.perform(post(this.urlPrefix + "/applicants/register")
                .content("{\n" +
                        "  \"email\": \"string@string.com\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"gender\": \"MALE\",\n" +
                        "  \"dateOfBirth\": 0\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is(400))
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4000,\"reason\":\"cannot register because there are already user registered with same email or username\"}}", false));
    }


}
