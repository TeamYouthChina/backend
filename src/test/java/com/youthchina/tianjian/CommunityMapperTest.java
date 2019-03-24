package com.youthchina.tianjian;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.tianjian.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.zhongyang.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/12/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:essay.xml"})
public class CommunityMapperTest {
    @Autowired
    private CommunityMapper userMapper;

    @Autowired
    UserService userService;
    @Test
    public void testGetEssay() {
        ComEssay comEssay = userMapper.getEssay(2);
        Assert.assertNotNull(comEssay.getEssayId());
    }

    @Test
    public void testAddEssay() {
        ComEssay comEssay = new ComEssay();
        comEssay.setEssayTitle("title1");
        comEssay.setEssayAbbre("this essay describe ...");
        comEssay.setUserAnony(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssayPubTime(time);
        comEssay.setEssayEditTime(time);
        comEssay.setIsDelete(0);
        comEssay.setUserAnony(0);
        comEssay.setUserId(1);
        comEssay.setRelaType(0);
         userMapper.addEssay(comEssay);
        Assert.assertNotNull(comEssay.getEssayId());
    }


    @Test
    public void testDeleteEssay() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        userMapper.deleteEssay(1,time);
    }

    @Test
    public void testUpdateEssay() {
        ComEssay comEssay = new ComEssay();
        comEssay.setEssayId(1);
        comEssay.setEssayTitle("title1");
        comEssay.setEssayAbbre("this essay describe ...");
        comEssay.setUserAnony(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssayPubTime(time);
        comEssay.setEssayEditTime(time);
        comEssay.setIsDelete(0);
        comEssay.setUserAnony(0);
        userMapper.updateEssay(comEssay);
    }


    @Test
    public void testGetEssayLatest() {
        List<ComEssay> comEssays = userMapper.getEssayLatest();
        Assert.assertNotNull(comEssays);
        System.out.println(comEssays.size());
    }



    @Test
    public void testSaveFriendsRelation() {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUser_id(1001);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comFriendRelation.setAdd_time(time);
        comFriendRelation.setIs_delete(0);
        userMapper.saveFriendsRelation(comFriendRelation);
        Assert.assertNotNull(comFriendRelation.getRela_id());
    }

    @Test
    public void testSaveFriendsRelationMap() {
        ComFriendRelationMap comFriendRelationMap = new ComFriendRelationMap();
        comFriendRelationMap.setRela_id(3);
        comFriendRelationMap.setUser_id(1001);
        userMapper.saveFriendsRelationMap(comFriendRelationMap);
    }

    @Test
    public void testDeleteFriend() {
        ComFriendRelation comFriendRelation = new ComFriendRelation();
        comFriendRelation.setUser_id(1001);
        comFriendRelation.setIs_delete(1);
        comFriendRelation.setRela_id(2);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comFriendRelation.setIs_delete_time(time);
        userMapper.deleteFriend(comFriendRelation,1001);
    }

    @Test
    public void testGetFriend() {
        List<ComFriendRelation> comFriendRelation = userMapper.getFriend(1001);
         Assert.assertNotNull(comFriendRelation);
         System.out.println(comFriendRelation);
    }

    @Test
    public void testSaveFriendGroup() {
        ComFriendGroup comFriendGroup = new ComFriendGroup();
        comFriendGroup.setGroup_name("the first group");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comFriendGroup.setAdd_time(time);
        comFriendGroup.setIs_delete(0);
        comFriendGroup.setGroup_num(1);
        userMapper.saveFriendGroup(comFriendGroup);
    }

    @Test
    public void testSaveFriendGroupMap() {
        ComFriendGroupMap comFriendGroupMap = new ComFriendGroupMap();
        comFriendGroupMap.setGroup_id(2);
        comFriendGroupMap.setRela_id(6);
        userMapper.saveFriendGroupMap(comFriendGroupMap);
    }

    @Test
    public void testUpdateFriendGroup() {
        ComFriendGroup comFriendGroup = new ComFriendGroup();
        comFriendGroup.setGroup_name("the second group");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comFriendGroup.setAdd_time(time);
        comFriendGroup.setIs_delete(0);
        comFriendGroup.setGroup_num(2);
        userMapper.updateFriendGroup(comFriendGroup,1002);
    }


    @Test
    public void testGetFriendGroup() {
        List<ComFriendRelation> comFriendRelation = new ArrayList<ComFriendRelation>();
        ComFriendRelation relation1 = new ComFriendRelation();
        relation1.setRela_id(6);
        comFriendRelation.add(relation1);
        List<ComFriendGroup> comFriendGroups =  userMapper.getFriendGroup(comFriendRelation);
        Assert.assertNotNull(comFriendGroups);
        System.out.println(comFriendGroups.size());
    }

}
