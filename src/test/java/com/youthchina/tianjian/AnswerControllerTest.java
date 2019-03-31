package com.youthchina.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml"})
@DatabaseSetup({"classpath:sys.xml"})
@WebAppConfiguration
public class AnswerControllerTest {

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
    public void testGetAnswer() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1").param("id", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
           .andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<个人感觉阿里的贡献远大于腾讯。从阿里的发展来看，一直在引领社会的进步，电商平台的贡献不用多说，物流，移动支付，都是阿里带给我们的，后面的阿里云，即将发力的物联网，都与我们息息相关。同类的某东完全不一样，我们用某东最多的是什么？自营！对，就是自营，理论上他只是一个超级大的仓库，那么阿里呢？起家的c2c业务对农村和城市就业以及物流的贡献……不用多说了，多少人的饭碗。再来说腾讯，当然也有价值。腾讯的核心是什么？社交链！那么我们需要社交嘛？当然需要，这就是他最大的价值。但是，社交本身是不盈利的，理论上它只是一个巨大的流量池，他一定会把流量引流到他需要盈利的地方，这就是所有腾讯系企业需要腾讯的原因。那腾讯系企业有价值吗？也有。但是和腾讯没有关系，财务投资人谁都会当。本质上不过是个流量变现而已>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"个人感觉阿里的贡献远大于腾讯。从阿里的发展来看，一直在引领社会的进步，电商平台的贡献不用多说，物流，移动支付，都是阿里带给我们的，后面的阿里云，即将发力的物联网，都与我们息息相关。同类的某东完全不一样，我们用某东最多的是什么？自营！对，就是自营，理论上他只是一个超级大的仓库，那么阿里呢？起家的c2c业务对农村和城市就业以及物流的贡献……不用多说了，多少人的饭碗。再来说腾讯，当然也有价值。腾讯的核心是什么？社交链！那么我们需要社交嘛？当然需要，这就是他最大的价值。但是，社交本身是不盈利的，理论上它只是一个巨大的流量池，他一定会把流量引流到他需要盈利的地方，这就是所有腾讯系企业需要腾讯的原因。那腾讯系企业有价值吗？也有。但是和腾讯没有关系，财务投资人谁都会当。本质上不过是个流量变现而已\",\"compiletype\":1},\"is_anonymous\":false,\"creator\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"modified_at\":\"2018-02-03 00:00:00.0\",\"create_at\":\"2018-02-03 00:00:00.0\",\"question\":{\"id\":1,\"creator\":{\"id\":1,\"username\":null,\"email\":null,\"phonenumber\":null,\"register_date\":null,\"firstName\":null,\"lastName\":null,\"gender\":null,\"nation\":null,\"avatar_url\":null,\"role\":null,\"age\":null},\"title\":\"腾讯好么？\",\"is_anonymous\":true,\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"rela_type\":1,\"rela_id\":37,\"body\":{\"braftEditorRaw\":{\"resourceIdList\":[],\"braftEditorRaw\":{\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"<asdasd>\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}},\"previewText\":\"<在此填入你的文字>\"},\"previewText\":\"asdasd\",\"compiletype\":1}},\"id\":1},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

    }

    @Test
    public void testUpdateAnswer() throws Exception {
        SimpleAnswerRequestDTO simpleAnswerDTO = new SimpleAnswerRequestDTO();
        simpleAnswerDTO.setIs_anonymous(true);
        String json = "";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);

        simpleAnswerDTO.setBody(richTextDTO);
        simpleAnswerDTO.setIs_anonymous(false);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String searchJson = ow.writeValueAsString(simpleAnswerDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(searchJson)
        )
                .andDo(print());
    }

    @Test
    public void testDeleteAnswer() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
    }

    @Test
    public void testAddAnswerComment() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setBody("qqqqq");
        commentRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(commentRequestDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(addJson)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testAddUpvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1/upvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testGetAllComments() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"comments\":[{\"id\":16,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"firstName\":\"DDD\",\"lastName\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":null,\"age\":28},\"body\":\"好好好\",\"create_at\":\"2019-02-12T00:00:00.000+0000\",\"is_anonymous\":true}]},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

}
