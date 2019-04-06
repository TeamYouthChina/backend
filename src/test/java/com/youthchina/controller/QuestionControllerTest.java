package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.question.QuestionRequestDTO;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml"})
@DatabaseSetup({"classpath:New_Company_test.xml"})
@DatabaseSetup({"classpath:sys.xml"})
@WebAppConfiguration
public class QuestionControllerTest {
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
    public void addQuestion() throws Exception{
        //language=JSON
        String json = "{\n" +
                "  \"braftEditorRaw\": {\n" +
                "    \"blocks\": [\n" +
                "      {\n" +
                "        \"key\": \"dtj4a\",\n" +
                "        \"text\": \"<asdasd>\",\n" +
                "        \"type\": \"unstyled\",\n" +
                "        \"depth\": 0,\n" +
                "        \"inlineStyleRanges\": [],\n" +
                "        \"entityRanges\": [],\n" +
                "        \"data\": {}\n" +
                "      }\n" +
                "    ],\n" +
                "    \"entityMap\": {}\n" +
                "  },\n" +
                "  \"previewText\": \"<在此填入你的文字>\",\n" +
                "  \"resourceIdList\": []\n" +
                "}";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        QuestionRequestDTO questionRequestDTO = new QuestionRequestDTO();
        questionRequestDTO.setBody(richTextDTO);
        questionRequestDTO.setIs_anonymous(false);
        questionRequestDTO.setRela_id(1);
        questionRequestDTO.setRela_type(3);
        questionRequestDTO.setTitle("nihao");
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/question").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }
    @Test
    public void getQuestionTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/2")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
        //     .andExpect(content().json("{\"content\":{\"id\":1,\"url\":\"GOOD\",\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the comment 1\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-10-11T11:11:22.000+0000\",\"is_anonymous\":false},{\"id\":3,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the comment 3\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-10-11T11:11:22.000+0000\",\"is_anonymous\":false}],\"uploader\":{\"id\":2,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void deleteEssayTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/questions/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/questions/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
    }
    @Test
    public void getQuestionAnswers() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/questions/1/answers")
                        .with(authGenerator.authentication())

        )
                .andDo(print());
    }

    @Test
    public void attentionQuestion() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/questions/1/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }

    @Test
    public void invit() throws Exception{
        this.mvc.perform(
                put(this.urlPrefix + "/questions/1/invite/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print());
    }
}
