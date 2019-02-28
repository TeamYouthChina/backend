package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.RichTextDTO;
import com.youthchina.dto.community.RequestEssayReplyDTO;
import com.youthchina.dto.community.RequestEssayDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
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

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


/**
 * Created by hongshengzhang on 2/17/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:essay.xml"})
@DatabaseSetup({"classpath:users.xml"})
@DatabaseSetup({"classpath:company.xml"})
@WebAppConfiguration
public class EssayControllerTest {

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
    public void getEssayTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"title\":\"title\",\"company\":{\"id\":1,\"name\":\"大疆\",\"avatarUrl\":\"1\",\"location\":\"北京\",\"website\":\"dji.com\",\"note\":\"无人机\",\"nation\":\"中国\"},\"create_at\":\"2018-12-04T13:32:40.000+0000\",\"modified_at\":\"2018-12-04T13:32:40.000+0000\",\"author\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Abbreviation of the essay 1 but42\",\"resourceIdList\":[]},\"is_anonymous\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }



    @Test
    public void addEssayTest() throws Exception {
        String json = "{\n" +
                "  \"braftEditorRaw\": {\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\": \"dtj4a\",\n" +
                "        \"text\": \"dsfgdfgdfg\",\n" +
                "        \"type\": \"unstyled\",\n" +
                "        \"depth\": 0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\": {}\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\": {\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\": \"This is a new article Abbre\",\n" +
                "  \"resourceIdList\": []\n" +
                "}";

        RichTextDTO richTextDTO = null;
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTO.class);
        } catch (IOException e) {
            Assert.fail();
        }
        RequestEssayDTO requestEssayDTO = new RequestEssayDTO();
        requestEssayDTO.setTitle("This is a new article Title");
        //RichTextDTO richTextDTO = new RichTextDTO();
        //richTextDTO.setBraftEditorRaw("{\n" +
        //       "  \"this\": \"that\"\n" +
        //        "}");
        //richTextDTO.setPreviewText("This is a new article Abbre");
        requestEssayDTO.setBody(richTextDTO);
        requestEssayDTO.setCompany_id(1);
        requestEssayDTO.setIs_anonymous(false);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void updateEssayTest() throws Exception {
        RequestEssayDTO requestEssayDTO = new RequestEssayDTO();
        requestEssayDTO.setTitle("This is a new Title 1");
        RichTextDTO richTextDTO = new RichTextDTO();
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\":{\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\":\"dtj4a\",\n" +
                "        \"text\":\"This is a new article 2/26 body\",\n" +
                "        \"type\":\"unstyled\",\n" +
                "        \"depth\":0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\":{\n" +
                "        }\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\":{\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\":\"This is a new article 2/26 Abbre\",\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTO.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }

        requestEssayDTO.setBody(richTextDTO);
        requestEssayDTO.setCompany_id(2);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO);

        this.mvc.perform(

                put(this.urlPrefix + "/articles/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                .with(authGenerator.authentication())

        )
                .andDo(print());
    }



    @Test
    public void deleteEssayTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/articles/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/articles/1")
                .with(authGenerator.authentication())

        )
                .andDo(print());


    }

    @Test
    public void addCommentTest() throws Exception {
        RequestEssayReplyDTO essayDTO = new RequestEssayReplyDTO();
        essayDTO.setAnonymous(false);
        RichTextDTO richTextDTO = new RichTextDTO();
        String json = "{\n" +
                "  \"braftEditorRaw\": {\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\": \"dtj4a\",\n" +
                "        \"text\": \"Nizhenshigerencai\",\n" +
                "        \"type\": \"unstyled\",\n" +
                "        \"depth\": 0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\": {}\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\": {\n" +
                "    }\n" +
                "  },\n" +
                "  \"previewText\": null,\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        try {
            richTextDTO = new ObjectMapper().readValue(json, RichTextDTO.class);
            System.out.println(richTextDTO);
        } catch (IOException e) {
            Assert.fail();
        }
        essayDTO.setBody(richTextDTO);
        essayDTO.setAnonymous(false);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(essayDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/articles/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson)
                .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void updateAttention() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/articles/1/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void deleteAttention() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/articles/attentions/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void getEssayComments() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/articles/1/comments")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"content\":{\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"register_date\":\"2018-10-11 11:11:22.0\",\"real_name\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatar_url\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"reply_content42\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":\"Body Body 1\",\"resourceIdList\":[]},\"create_at\":\"2018-12-04 13:32:40.0\",\"is_anonymous\":false,\"modified_at\":\"2018-12-04 13:32:40.0\"}]},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}