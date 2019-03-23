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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by hongshengzhang on 2/26/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@DatabaseSetup({"classpath:recommendation.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@WebAppConfiguration
@Transactional
public class CompanyRecommendControllerTest {
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
    public void getRecommendCompanyTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/companies")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"companies\":[{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},{\"id\":2,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},{\"id\":3,\"name\":\"腾讯\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"},{\"id\":4,\"name\":\"腾牛讯\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"},{\"id\":5,\"name\":\"腾讯深圳总公司\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"},{\"id\":6,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},{\"id\":7,\"name\":\"百度\",\"avatarUrl\":\"1\",\"location\":\"北京市\",\"website\":\"baidu.com\",\"note\":\"baidu\",\"nation\":\"中国\"},{\"id\":8,\"name\":\"腾讯\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"},{\"id\":9,\"name\":\"腾牛讯\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"},{\"id\":10,\"name\":\"腾讯深圳总公司\",\"avatarUrl\":\"1\",\"location\":\"市辖区\",\"website\":\"QQ.com\",\"note\":\"QQ\",\"nation\":\"中国\"}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
