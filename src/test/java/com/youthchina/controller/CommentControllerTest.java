package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.community.discuss.DiscussRequestDTO;
import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
public class CommentControllerTest {

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
    public void testGetAllReplies() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/comments/1/replies").param("id", "1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":1,\"commentId\":1,\"creator\":{\"id\":3,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},\"body\":\"讨论内容1 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":2,\"commentId\":1,\"creator\":{\"id\":4,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":25},\"body\":\"讨论内容2 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":3,\"commentId\":1,\"creator\":{\"id\":5,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"body\":\"讨论内容3 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":4,\"commentId\":1,\"creator\":{\"id\":6,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},\"body\":\"讨论内容4 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":5,\"commentId\":1,\"creator\":{\"id\":7,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"APPLICANT\"],\"age\":25},\"body\":\"讨论内容5 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":true,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":6,\"commentId\":1,\"creator\":{\"id\":8,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"APPLICANT\"],\"age\":28},\"body\":\"讨论内容6 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":true,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null},{\"id\":7,\"commentId\":1,\"creator\":{\"id\":9,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"APPLICANT\"],\"age\":26},\"body\":\"讨论内容7 \",\"create_at\":\"2019-01-01T00:00:00.000+0000\",\"is_anonymous\":true,\"modified_at\":null,\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null}],\"page_count\":0,\"item_count\":7,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void testDeleteComment() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/comments/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
    }

    @Test
    public void testAddUpvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/comments/1/upvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testAddDownvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/comments/1/downvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testAddDiscuss() throws Exception {
        DiscussRequestDTO discussRequestDTO = new DiscussRequestDTO();
        discussRequestDTO.setBody("This is a discuss test.");
        discussRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String addJson = ow.writeValueAsString(discussRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/comments/2/replies")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(addJson)
        )
                .andDo(print());

    }
}
