package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.community.briefreview.BriefReviewRequestDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
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
public class BriefReviewControllerTest {
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
    public void getBriefReviewTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"id\":1,\"body\":{\"braftEditorRaw\":\"{\\\"braftEditorRaw\\\":{\\\"blocks\\\":[{\\\"key\\\":\\\"dtj4a\\\",\\\"text\\\":\\\"<有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。>\\\",\\\"type\\\":\\\"unstyled\\\",\\\"depth\\\":0,\\\"inlineStyleRanges\\\":[],\\\"entityRanges\\\":[],\\\"data\\\":{}}],\\\"entityMap\\\":{}},\\\"previewText\\\":\\\"<在此填入你的文字>\\\",\\\"resourceIdList\\\":[]}\",\"previewText\":\"有感于腾讯公司的发家史，总觉得腾讯背后有某种强大的力量，能靠微创新（也可称山寨）能发展到现如今的体量也算是世界奇观。靠模仿icq完成了资本的原始积累并实现了滚雪球，可以这么说腾讯的今天是一切都建立在oicq（qq）之上的，从qq堂，qq飞车，qq劲舞，腾讯的发家史就是一个复制粘贴史。。。并且发展到如今规模，企业文化还是坚强的延续下来，复制粘贴的企业文化从高层到底层，已深入骨髓，从领子烂到里子。。对创新型企业来说，腾讯如一颗毒瘤存在，注定不会得到大家尊重。\",\"compiletype\":1},\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"body\":\"短评评论1\",\"create_at\":\"2018-02-03T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":\"2018-02-03T00:00:00.000+0000\",\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null}],\"author\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"upvoteCount\":2,\"downvoteCount\":2,\"attentionCount\":4,\"evaluateStatus\":3,\"modified_at\":\"2019-01-01T00:00:00.000+0000\",\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
        // change register_date, real_name
    }

    @Test
    public void deleteBriefReviewTest() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/editorials/1")
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));

    }

    @Test
    public void updateBriefReviewTest() throws Exception {
        BriefReviewRequestDTO briefReviewRequestDTO = new BriefReviewRequestDTO();
        String json = "ttererer";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        briefReviewRequestDTO.setBody(richTextDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String addJson = ow.writeValueAsString(briefReviewRequestDTO);
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print());
           //     .andExpect(content().json("{\"content\":{\"id\":1,\"body\":{\"braftEditorRaw\":\"ttererer\",\"previewText\":\"pre\",\"compiletype\":1},\"comments\":[{\"id\":1,\"creator\":{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},\"body\":\"短评评论1\",\"create_at\":\"2018-02-03T00:00:00.000+0000\",\"is_anonymous\":false,\"modified_at\":\"2018-02-03T00:00:00.000+0000\",\"upvoteCount\":null,\"downvoteCount\":null,\"evaluateStatus\":null}],\"author\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"Admin\",\"last_name\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ROOT\"],\"age\":25},\"upvoteCount\":null,\"downvoteCount\":null,\"attentionCount\":null,\"evaluateStatus\":null,\"modified_at\":\"2019-04-11T16:50:21.000+0000\",\"attention\":false},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));


    }

    @Test
    public void addBriefReviewTest() throws Exception {
        BriefReviewRequestDTO briefReviewRequestDTO = new BriefReviewRequestDTO();
        String json = "erer";
        String pre = "pre";
        RichTextRequestDTO richTextDTO = new RichTextRequestDTO();
        richTextDTO.setBraftEditorRaw(json);
        richTextDTO.setPreviewText(pre);
        richTextDTO.setCompiletype(1);
        briefReviewRequestDTO.setBody(richTextDTO);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String addJson = ow.writeValueAsString(briefReviewRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())

        )
                .andDo(print());
    }

    @Test
    public void addBriefReviewCommentsTest() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        String json = "comment";
        commentRequestDTO.setBody(json);

        commentRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String addJson = ow.writeValueAsString(commentRequestDTO);
        this.mvc.perform(
                post(this.urlPrefix + "/editorials/1/comments")
                        .content(addJson)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void addBriefReviewUpvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1/upvote")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void addBriefReviewDownvoteTest() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/editorials/1/downvote")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void getBriefReviewCommentsTest() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/editorials/1/comments")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .with(authGenerator.authentication())
        )
                .andDo(print())
                .andExpect(content().json("{\n" +
                        "  \"content\": {\n" +
                        "    \"offset\": 0,\n" +
                        "    \"limit\": 2147483646,\n" +
                        "    \"data\": [\n" +
                        "      {\n" +
                        "        \"id\": 1,\n" +
                        "        \"creator\": {\n" +
                        "          \"id\": 2,\n" +
                        "          \"username\": \"DEF\",\n" +
                        "          \"email\": \"123456@456.com\",\n" +
                        "          \"phonenumber\": \"9876543210123\",\n" +
                        "          \"register_date\": \"2019-01-01 00:00:00.0\",\n" +
                        "          \"first_name\": \"DDD\",\n" +
                        "          \"last_name\": \"DDDEEEFFF\",\n" +
                        "          \"gender\": \"Female\",\n" +
                        "          \"nation\": \"USA\",\n" +
                        "          \"avatar_url\": \"---\",\n" +
                        "          \"role\": [\n" +
                        "            \"ADMIN\"\n" +
                        "          ],\n" +
                        "          \"age\": 28\n" +
                        "        },\n" +
                        "        \"body\": \"短评评论1\",\n" +
                        "        \"create_at\": \"2018-02-03T00:00:00.000+0000\",\n" +
                        "        \"is_anonymous\": false,\n" +
                        "        \"modified_at\": \"2018-02-03T00:00:00.000+0000\",\n" +
                        "        \"upvoteCount\": 2,\n" +
                        "        \"downvoteCount\": 2,\n" +
                        "        \"evaluateStatus\": 3\n" +
                        "      }\n" +
                        "    ],\n" +
                        "    \"page_count\": 0,\n" +
                        "    \"item_count\": 1,\n" +
                        "    \"page_index\": 0,\n" +
                        "    \"is_first\": true,\n" +
                        "    \"is_last\": false\n" +
                        "  },\n" +
                        "  \"status\": {\n" +
                        "    \"code\": 2000,\n" +
                        "    \"reason\": \"\"\n" +
                        "  }\n" +
                        "}", false));

    }
}