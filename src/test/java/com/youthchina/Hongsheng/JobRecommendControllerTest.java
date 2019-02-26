package com.youthchina.Hongsheng;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hongshengzhang on 2/24/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@WebAppConfiguration
public class JobRecommendControllerTest {
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
    public void getRecommandInternJobsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/jobs/intern")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void getRecommandGeneralJobTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/jobs/general")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void getRecommandCampusJobsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/jobs/campus")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"jobList\":[{\"id\":1,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":2,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":3,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":4,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":null,\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":null,\"nation\":null,\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":5,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":6,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":7,\"name\":\"front-end\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":8,\"name\":\"前端工程师\",\"organization\":{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"baidu.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null},{\"id\":9,\"name\":\"front\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"front\",\"description\":\"996\",\"status\":null},{\"id\":10,\"name\":\"back\",\"organization\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"中国江苏\",\"website\":\"dji.com\",\"note\":null,\"nation\":\"CHN\"},\"location\":\"中国江苏\",\"nation\":\"CHN\",\"type\":\"实习\",\"deadLine\":\"01/01/2020\",\"duty\":\"back\",\"description\":\"996\",\"status\":null}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}

