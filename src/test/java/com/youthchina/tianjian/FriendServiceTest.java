package com.youthchina.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.dto.community.answer.SimpleAnswerRequestDTO;
import com.youthchina.dto.community.comment.CommentRequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.tianjian.FriendsServiceImpl;
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

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
@WebAppConfiguration
public class FriendServiceTest {

    @Autowired
    private WebApplicationContext context;

    @Value("${web.url.prefix}")
    private String urlPrefix;

    @Autowired
    private FriendsServiceImpl friendsService;

    MockMvc mvc;

    @Before
    public void setup() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context).apply(SecurityMockMvcConfigurers.springSecurity()).build();
    }

    @Test
    public void testAddfriend() throws Exception {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUserId(1);
        comFriendRelation.setFriendId(2);
        friendsService.saveFriend(comFriendRelation);
    }

    @Test
    public void testdeleteFriend() throws NotFoundException {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUserId(1);
        comFriendRelation.setFriendId(2);
        friendsService.deleteFriend(comFriendRelation);
    }

    @Test
    public void testgetFriend(){
        List<ComFriendRelation> comFriendRelationList = friendsService.getFriend(2);
        System.out.println(comFriendRelationList.size());
    }

    @Test
    public void testaddFriendGroup(){
        ComFriendGroup comFriendGroup = new ComFriendGroup();
        comFriendGroup.setGroupName("qqwet");
        comFriendGroup.setGroupNum(1);
        friendsService.saveFriendGroup(comFriendGroup,1);
    }

    @Test
    public void testgetFriendGroup(){
        List<ComFriendGroup> comFriendGroups = friendsService.getFriendGroup(4);
        System.out.println(comFriendGroups.size());
    }
}
