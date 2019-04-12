package com.youthchina.tianjian;

import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class FriendControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    private AuthGenerator authGenerator = new AuthGenerator();

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void testaddapplyfriend() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/friends/2/apply").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
                //.andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。到了今天，微软在互联网时代江河日下，谷歌和facebook大肆收购，花上百亿美元去买下新兴的技术，为的是什么？就是在押宝呀。技术在不断向前升级，哪一个方向才是未来的主流趋势呢？没有人知道。对于腾讯来说，也是一样的。小马哥每天都在为这件事情而焦虑。截至目前，在国内，押中两次宝的就只有腾讯和阿里。阿里押中了淘宝和支付宝，腾讯押中了QQ和微信。在移动互联网时代，腾讯可以稍稍松一口气了，但是在下一个主流技术趋势到来的时候，还有这个好运气么？>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"在软件行业，操作系统平台就是那个八，其他的应用软件就是那个二。微软已经踩到了一次狗屎运，得到了软件行业80%的利润，现在，他所需要做的，就是保持住这个地位。但技术不是静止不动的，不断有新的技术生长出来，在成千上万种技术中，有一种会长成参天大树，利润无比丰厚，取代原来的技术平台，成为新的主流趋势。到了今天，微软在互联网时代江河日下，谷歌和facebook大肆收购，花上百亿美元去买下新兴的技术，为的是什么？就是在押宝呀。技术在不断向前升级，哪一个方向才是未来的主流趋势呢？没有人知道。对于腾讯来说，也是一样的。小马哥每天都在为这件事情而焦虑。截至目前，在国内，押中两次宝的就只有腾讯和阿里。阿里押中了淘宝和支付宝，腾讯押中了QQ和微信。在移动互联网时代，腾讯可以稍稍松一口气了，但是在下一个主流技术趋势到来的时候，还有这个好运气么？\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"modified_at\":\"2018-02-03 00:00:00.0\",\"create_at\":\"2018-02-03 00:00:00.0\",\"question\":{\"id\":1,\"creator\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"title\":\"腾讯好么？\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"rela_type\":1,\"rela_id\":37,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<asdasd>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"asdasd\",\"compiletype\":1}},\"id\":1,\"upvoteCount\":0,\"downvoteCount\":0,\"attentionCount\":0,\"evaluateStatus\":3,\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void testdeletefriend() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/friends/2").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testgetFriendApplication() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends/applications/2").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"reference_id\":2,\"create_at\":\"2019-01-01 00:00:00.0\"},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void testaddApprovalApplication() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/friends/applications/2/approval").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testaddDenyApplication() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/friends/applications/2/deny").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testgetAllFriend() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},{\"id\":3,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},{\"id\":4,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":25}],\"page_count\":0,\"item_count\":3,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void testgetUserAllApplication() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends/applications")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
        // .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }
}
