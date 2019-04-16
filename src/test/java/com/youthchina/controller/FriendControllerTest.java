package com.youthchina.controller;

import com.youthchina.util.AuthGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static com.youthchina.util.CustomMockMvcMatchers.partialContent;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class FriendControllerTest {

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
    public void testaddapplyfriend() throws Exception {
        this.mvc.perform(
                post(this.urlPrefix + "/friends/8/apply").param("id", "8")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(partialContent("{\"content\":{\"reference_id\":6,\"create_at\":\"2019-04-13 15:41:08.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false},\"status\":{\"code\":201,\"reason\":\"success\"}}","$.content.reference_id","$.content.create_at"));
    }

    @Test
    public void testdeletefriend() throws Exception {
        this.mvc.perform(
                delete(this.urlPrefix + "/friends/2").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testgetFriendApplication() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends/applications/2").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"reference_id\":2,\"create_at\":\"2019-01-01 00:00:00.0\"},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void testaddApprovalApplication() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/friends/applications/2/approval").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testaddDenyApplication() throws Exception {
        this.mvc.perform(
                put(this.urlPrefix + "/friends/applications/2/deny").param("id", "2")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"code\":200,\"reason\":\"success\"},\"status\":{\"code\":2000,\"reason\":\"\"}}", false));
    }

    @Test
    public void testgetAllFriend() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"id\":2,\"username\":\"DEF\",\"email\":\"123456@456.com\",\"phonenumber\":\"9876543210123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"DDD\",\"last_name\":\"DDDEEEFFF\",\"gender\":\"Female\",\"nation\":\"USA\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":28},{\"id\":3,\"username\":\"GHI\",\"email\":\"123456@789.com\",\"phonenumber\":\"1112223334445\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"GGG\",\"last_name\":\"GGGHHHIII\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":26},{\"id\":4,\"username\":\"ABC\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"register_date\":\"2019-01-01 00:00:00.0\",\"first_name\":\"AAA\",\"last_name\":\"AAABBBCCC\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatar_url\":\"---\",\"role\":[\"ADMIN\"],\"age\":25}],\"page_count\":0,\"item_count\":3,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }

    @Test
    public void testgetUserAllApplication() throws Exception {
        this.mvc.perform(
                get(this.urlPrefix + "/friends/applications")
                        .with(authGenerator.authentication())

        )
                .andDo(print())
                .andExpect(content().json("{\"content\":{\"content\":{\"offset\":0,\"limit\":2147483646,\"data\":[{\"reference_id\":1,\"create_at\":\"2019-01-01 00:00:00.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false},{\"reference_id\":2,\"create_at\":\"2019-01-01 00:00:00.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false},{\"reference_id\":3,\"create_at\":\"2019-01-01 00:00:00.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false},{\"reference_id\":4,\"create_at\":\"2019-01-01 00:00:00.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false},{\"reference_id\":5,\"create_at\":\"2019-01-01 00:00:00.0\",\"applicant\":{\"id\":1,\"username\":\"Admin\",\"email\":\"123456@123.com\",\"phonenumber\":\"1234657890123\",\"registerDate\":\"2019-01-01 00:00:00.0\",\"firstName\":\"Admin\",\"lastName\":\"Admin\",\"gender\":\"Male\",\"nation\":\"CHN\",\"avatarUrl\":\"---\",\"hired\":false,\"role\":null,\"age\":25},\"is_read\":false}],\"page_count\":0,\"item_count\":5,\"page_index\":0,\"is_first\":true,\"is_last\":false},\"status\":{\"code\":2000,\"reason\":\"\"}},\"status\":{\"code\":200,\"reason\":\"success\"}}", false));
    }
}
