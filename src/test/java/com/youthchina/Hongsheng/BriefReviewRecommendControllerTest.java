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
 * Created by hongshengzhang on 2/26/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:recommendation.xml"})
@DatabaseSetup({"classpath:briefreview.xml"})
@WebAppConfiguration
public class BriefReviewRecommendControllerTest {
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
    public void getRecommandEditorialsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/discovery/editorials")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"editorials\":[{\"id\":1,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"QQQEERTT\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"comments\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":null,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"qwe\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false}]},\"author\":null},{\"id\":2,\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"QQQEERTTYYYY\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"comments\":{\"comments\":[{\"id\":2,\"creator\":{\"id\":3,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":null,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"qweetrt\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"is_anonymous\":false}]},\"author\":null}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
