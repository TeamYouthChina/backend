package com.youthchina.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.SearchResult;
import com.youthchina.domain.zhongyang.SearchResultItem;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Hongsheng.SearchService;
import com.youthchina.service.tianjian.EssayService;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.stereotype.Service;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Created by zhongyangwu on 4/7/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Company_test.xml",
        "classpath:New_Dictionary_test.xml",
        "classpath:New_SYS_test.xml",
        "classpath:New_Job_test.xml",
        "classpath:New_Community_test.xml"
})
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
    public void testSearch() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/search")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"content\":{\"id\":1,\"title\":\"翟天临们，放过博士学位吧\",\"company\":null,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"author\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":null,\"age\":25},\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<这只是针对《谈电视剧[白鹿原]中“白孝文”的表演创作》一文问题的查处结果单就这个问题所作的查处结果对陈邑和翟天临来说很合适。一起观望后续其他问题的查处结果唯一需要担心的是，北电别有用心，先抛出这个结果来试探全国广大网民的对翟天临事件的较真程度如果网民们较真，北电只需要回应这只是这个问题的查处结果，其他问题正在查处中如果网名们淡忘了，热度消失了，公众麻木了，不感兴趣了这起事件淡出了公众的视线那么后续相关问题可能慢慢地就不了了之了而后续相关问题即翟天临如何能通过北电学术委员会和答辩委员会的评审以及这其中是否存在拉帮结派利益勾结，才是翟天临事件的重中之重，也是北电最为害怕公之于众的所以最后望广大学术一线人员苟富贵，莫相忘哈哈哈>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"这只是针对《谈电视剧[白鹿原]中“白孝文”的表演创作》一文问题的查处结果单就这个问题所作的查处结果对陈邑和翟天临来说很合适。一起观望后续其他问题的查处结果唯一需要担心的是，北电别有用心，先抛出这个结果来试探全国广大网民的对翟天临事件的较真程度如果网民们较真，北电只需要回应这只是这个问题的查处结果，其他问题正在查处中如果网名们淡忘了，热度消失了，公众麻木了，不感兴趣了这起事件淡出了公众的视线那么后续相关问题可能慢慢地就不了了之了而后续相关问题即翟天临如何能通过北电学术委员会和答辩委员会的评审以及这其中是否存在拉帮结派利益勾结，才是翟天临事件的重中之重，也是北电最为害怕公之于众的所以最后望广大学术一线人员苟富贵，莫相忘哈哈哈\",\"compiletype\":1},\"is_anonymous\":true},\"type\":\"article\"}],\"page_count\":0,\"item_count\":3,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

}