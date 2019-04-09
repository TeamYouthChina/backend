package com.youthchina.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.util.AuthGenerator;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml"})
@DatabaseSetup({"classpath:New_SYS_test.xml"})

@WebAppConfiguration
public class VideoControllerTest {
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
    public void getVideoTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/videos/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print());
        //     .andExpect(content().json("{\"content\":{\"id\":1,\"url\":\"GOOD\",\"comments\":[{\"id\":1,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the comment 1\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-10-11T11:11:22.000+0000\",\"is_anonymous\":false},{\"id\":3,\"creator\":{\"id\":1,\"username\":\"yihao guo\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21},\"body\":{\"braftEditorRaw\":{\"entityMap\":{},\"blocks\":[{\"key\":\"dtj4a\",\"text\":\"Body of the comment 3\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}]},\"previewText\":null,\"resourceIdList\":[]},\"create_at\":\"2018-10-11T11:11:22.000+0000\",\"is_anonymous\":false}],\"uploader\":{\"id\":2,\"username\":\"zhid d\",\"email\":\"test@test.com\",\"phonenumber\":\"18463722634\",\"registerDate\":\"2018-10-11 11:11:22.0\",\"realName\":\"None\",\"gender\":\"male\",\"nation\":\"China\",\"avatarUrl\":null,\"role\":1,\"age\":21}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void getCommentsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/videos/1/comments")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":6,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"body\":\"好好好\",\"create_at\":\"2019-02-20T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":\"2019-02-20T00:00:00.000+0000\",\"upvoteCount\":2,\"downvoteCount\":0,\"evaluateStatus\":3},{\"id\":7,\"creator\":{\"id\":3,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},\"body\":\"一般般\",\"create_at\":\"2016-06-13T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":\"2016-06-13T00:00:00.000+0000\",\"upvoteCount\":2,\"downvoteCount\":0,\"evaluateStatus\":3}],\"page_count\":0,\"item_count\":2,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void deleteVideoTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/videos/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());

        this.mvc.perform(
                get(this.urlPrefix + "/videos/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print());


    }

    @Test
    public void addCommentTest() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setIs_anonymous(false);
        String json = "comment";
        commentRequestDTO.setBody(json);

        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        java.lang.String requestJson = ow.writeValueAsString(commentRequestDTO);

        this.mvc.perform(
                post(this.urlPrefix + "/videos/1/comments").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(requestJson)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void attentionTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/videos/1/attention").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void upvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/videos/1/upvote").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void downvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/videos/1/downvote").contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

//    @Test
//    public void testUserAttentions() throws Exception {
//        this.mvc.perform(
//                get
//                        (this.urlPrefix + "/users/1/attentions").param("type", "Video")
//
//                        .with(authGenerator.authentication())
//        )
//                .andDo(print());
//    }


//    @Test
//    public void testUploadVideo() throws Exception {
//        VideoRequestDTO requestVideoDTO = new VideoRequestDTO();
//        requestVideoDTO.setCompany_id(1);
//
//        ObjectMapper mapper = new ObjectMapper();
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = ow.writeValueAsString(requestVideoDTO);
//
////        ClassPathResource classPathResource = new ClassPathResource("rank.xml");
//        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "multipart/form-data", "hello upload".getBytes("UTF-8"));
//
//        this.mvc.perform(MockMvcRequestBuilders.multipart(this.urlPrefix + "/videos")
//                .file(file).with(authGenerator.authentication()))
//                .andDo(print())
//                .andExpect(status().is2xxSuccessful())
//                .andReturn().getResponse().getContentAsString();
//
//
//    }

}