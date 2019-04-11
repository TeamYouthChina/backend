package com.youthchina.Qingyang;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by zhongyangwu on 4/4/19.
 */



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class JobControllerTest {
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
    public void testSearchJob() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/jobs/search?page=1&limit=10")
                        .content("{\n" +
                                "  \"industry\": [\n" +
                                "    \"test\",\n" +
                                "    \"test\"\n" +
                                "  ]\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print())
        ;
    }

}