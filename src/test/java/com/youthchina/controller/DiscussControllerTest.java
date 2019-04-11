package com.youthchina.controller;

import com.youthchina.domain.jinhao.Discuss;
import com.youthchina.service.jinhao.EvaluateService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhongyangwu on 4/9/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class DiscussControllerTest extends BaseControllerTest {
    @Resource
    EvaluateService evaluateService;
    @Test
    public void testGetDiscuses() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/replies/1")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void testDeleteDiscusses() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/replies/1")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void testUpvoteDiscusses() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/replies/1/upvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":null,\"status\":{\"code\":4040,\"reason\":\"You have already upvoted! You cannot upvote again!\"}}", false))
                .andDo(print());
    }

    @Test
    public void testDownvoteDiscusses() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/replies/1/downvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
    }

    @Test
    public void testDeleteDownvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/replies/1/downvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());

        this.mvc.perform(
                delete(this.urlPrefix + "/replies/1/vote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
    }

    @Test
    public void testDeleteUpvote() throws Exception {
       /* this.mvc.perform(
                put(this.urlPrefix + "/replies/1/upvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());*/
        this.mvc.perform(
                delete(this.urlPrefix + "/replies/1/vote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
    }
}
