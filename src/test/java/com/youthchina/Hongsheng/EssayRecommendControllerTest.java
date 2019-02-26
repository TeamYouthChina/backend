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
public class EssayRecommendControllerTest {
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
    public void getRecommandEssayTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/articles")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"users\":[{\"id\":1,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":2,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":3,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":4,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":5,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":6,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":7,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":8,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":9,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false},{\"id\":10,\"title\":\"title\",\"company\":null,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":null,\"body\":{\"braftEditorRaw\":\"Abbreviation of the essay 1 but42\",\"previewText\":\"Body Body 1\",\"resourceList\":null},\"is_anonymous\":false}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
