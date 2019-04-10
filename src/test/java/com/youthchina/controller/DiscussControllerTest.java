package com.youthchina.controller;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zhongyangwu on 4/9/19.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
@WebAppConfiguration
public class DiscussControllerTest extends BaseControllerTest {

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
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
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
                delete(this.urlPrefix + "/replies/1/downvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
    }

    @Test
    public void testDeleteUpvote() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/replies/1/upvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
        this.mvc.perform(
                delete(this.urlPrefix + "/replies/1/upvote")
                        .with(this.authGenerator.authentication())
        )
                .andExpect(content().json("{\"content\":{\"code\":204,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false))
                .andDo(print());
    }
}
