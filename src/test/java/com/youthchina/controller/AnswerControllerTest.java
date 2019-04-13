package com.youthchina.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class AnswerControllerTest extends BaseControllerTest {

    @Test
    public void testGetAnswer() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json(readJson("responses/get-answer.json"), false));

    }

    @Test
    public void testUpdateAnswer() throws Exception {

        this.mvc.perform(
                put(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(readJson("requests/post-answer.json"))
        )
                .andDo(print())
                .andExpect(partialContent(readJson("responses/put-answer.json"), "$.content.modified_at", "$.content.create_at"));
    }

    @Test
    public void testDeleteAnswer() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/answers/1")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddAnswerComment() throws Exception {
        CommentRequestDTO commentRequestDTO = new CommentRequestDTO();
        commentRequestDTO.setBody("qqqqq");
        commentRequestDTO.setIs_anonymous(true);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String addJson = ow.writeValueAsString(commentRequestDTO);

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
    public void testAddDownvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/answers/1/downvote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testDeletevote() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/answers/1/vote")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print());
        // .andExpect(content().json("{\"content\":{\"code\":201,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testGetAllComments() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/answers/1/comments")
                        .with(authGenerator.authentication())
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[],\"page_count\":0,\"item_count\":0,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

}
