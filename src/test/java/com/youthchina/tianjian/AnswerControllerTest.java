package com.youthchina.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.RichTextDTO;
import com.youthchina.dto.community.RequestSimpleAnswerDTO;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:answers.xml"})
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
                .andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":null,\"previewText\":\"这是第一个回答\",\"resourceList\":null},\"is_anonymous\":false,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"modified_at\":\"2018-12-04 13:32:40.0\",\"create_at\":\"2018-12-04 13:32:40.0\",\"company_id\":null,\"question\":{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"title\":\"第一个问题\",\"isAnonymous\":1,\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"answers\":[],\"rela_type\":1,\"rela_id\":null,\"anonymous\":1,\"richTextDTO\":{\"braftEditorRaw\":\"Abbreviation of the question 1 but42\",\"previewText\":\"Body of the question 1 but 42\",\"resourceList\":null}}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));

    }

    @Test
    public void testUpdateAnswer() throws Exception{
        RequestSimpleAnswerDTO simpleAnswerDTO = new RequestSimpleAnswerDTO();
        simpleAnswerDTO.setIs_anonymous(true);
        RichTextDTO richTextDTO = new RichTextDTO();
        richTextDTO.setPreviewText("qweertyuiop");
        simpleAnswerDTO.setBody(richTextDTO);


        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String searchJson = ow.writeValueAsString(simpleAnswerDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(searchJson)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"body\":{\"braftEditorRaw\":null,\"previewText\":\"qweertyuiop\",\"resourceList\":null},\"isAnonymous\":true},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
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
    public void testAddAnswerComment() throws Exception{
        RequestSimpleAnswerDTO simpleAnswerDTO = new RequestSimpleAnswerDTO();
        RichTextDTO richTextDTO = new RichTextDTO();
        richTextDTO.setPreviewText("qweweer");
        simpleAnswerDTO.setBody(richTextDTO);
        simpleAnswerDTO.setIs_anonymous(false);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String addJson = ow.writeValueAsString(simpleAnswerDTO);

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
    public void testAddUpvote() throws Exception{
        this.mvc.perform(
                post(this.urlPrefix + "/answers/1/upvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testGetAllComments() throws Exception{
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
                //.andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

}
