
package com.youthchina.service;

import com.youthchina.domain.tianjian.ComFriendGroup;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.FriendsServiceImpl;
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

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
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
        //1给2发过申请，2作为user 将1 作为好友添加
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUserId(2);
        comFriendRelation.setFriendId(1);

        friendsService.addFriend(comFriendRelation);
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