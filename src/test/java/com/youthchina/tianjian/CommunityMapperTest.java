package com.youthchina.tianjian;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.domain.tianjian.*;
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
    @Test
    public void testGetEssay() {
        ComEssay comEssay = userMapper.getEssay(1);
        Assert.assertNotNull(comEssay.getEssay_id());
    }

    @Test
    public void testAddEssay() {
        ComEssay comEssay = new ComEssay();
        comEssay.setEssay_title("title1");
        comEssay.setEssay_abbre("this essay describe ...");
        comEssay.setEssay_body("body");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssay_pub_time(time);
        comEssay.setEssay_edit_time(time);
        comEssay.setIs_delete(0);
        comEssay.setUser_anony(0);
         userMapper.addEssay(comEssay);
        Assert.assertNotNull(comEssay.getEssay_id());
    }


    @Test
    public void testDeleteEssay() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        userMapper.deleteEssay(1,time);
    }

    @Test
    public void testUpdateEssay() {
        ComEssay comEssay = new ComEssay();
        comEssay.setEssay_id(1);
        comEssay.setEssay_title("title1");
        comEssay.setEssay_abbre("this essay describe ...");
        comEssay.setEssay_body("body");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssay_pub_time(time);
        comEssay.setEssay_edit_time(time);
        comEssay.setIs_delete(0);
        comEssay.setUser_anony(0);
        userMapper.updateEssay(comEssay);
    }


    @Test
    public void testUpdateEssayAuthor() {
        ComAuthorEssayMap comAuthorEssayMap = new ComAuthorEssayMap();
        comAuthorEssayMap.setEssay_id(1);
        comAuthorEssayMap.setUser_id(1003);
        userMapper.updateEssayAuthor(comAuthorEssayMap);
    }

    @Test
    public void testAddEssayLabel() {
        List<ComEssayLabelMap> cel = new ArrayList<ComEssayLabelMap>();
        ComEssayLabelMap comEssayLabelMap = new ComEssayLabelMap();
        comEssayLabelMap.setEssay_id(1);
        comEssayLabelMap.setLab_num(1);
        ComEssayLabelMap comEssayLabelMap1 = new ComEssayLabelMap();
        comEssayLabelMap1.setEssay_id(1);
        comEssayLabelMap1.setLab_num(2);
        cel.add(comEssayLabelMap);
        cel.add(comEssayLabelMap1);
        userMapper.addEssayLabel(cel);
    }

    @Test
    public void testDeleteEssayLabel() {
        userMapper.deleteEssayLabel(1);
    }

    @Test
    public void testAddEssayAuthor() {
        ComAuthorEssayMap caem = new ComAuthorEssayMap();
        caem.setUser_id(1001);
        caem.setEssay_id(2);
        caem.setRela_id(3);
        caem.setRela_type(0);
        userMapper.addEssayAuthor(caem);

    }

    @Test
    public void testAddFavoriteEssay() {
        ComEssayAttention comessayattention = new ComEssayAttention();
        comessayattention.setUser_id(1002);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comessayattention.setAtten_time(time);
        comessayattention.setAtten_cancel(0);
        userMapper.addFavoriteEssay(comessayattention);
        Assert.assertNotNull(comessayattention.getAtten_id());

    }

    @Test
    public void testAddFavoriteEssayMap() {
        ComEssayAttentionMap ceam = new ComEssayAttentionMap();
        ceam.setAtten_id(2);
        ceam.setEssay_id(1001);
        userMapper.addFavoriteEssayMap(ceam);
    }

    @Test
    public void testDeleteFavoriteEssayMap() {
        userMapper.deleteFavoriteEssay(1,1001);
    }


    @Test
    public void testGetFavoriteEssayWhetherAtten() {
        ComEssayAttention comEssayAttention = userMapper.getFavoriteEssayWhetherAtten(1,1001);
        Assert.assertNotNull(comEssayAttention.getAtten_id());
        System.out.println(comEssayAttention);
    }

    @Test
    public void testAddReply() {
        ComEssayReply comessayanswer = new ComEssayReply();
        comessayanswer.setUser_id(1003);
        comessayanswer.setReply_content("reply");
        comessayanswer.setUser_anony(1);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comessayanswer.setReply_pub_time(time);
        comessayanswer.setReply_edit_time(time);
        comessayanswer.setIs_delete(0);
        userMapper.addReply(comessayanswer);
        Assert.assertNotNull(comessayanswer.getReply_id());
    }

    @Test
    public void testAddEssayReplyMap() {
        ComEssayReplyMap cerm = new ComEssayReplyMap();
        cerm.setReply_id(3);
        cerm.setEssay_id(1001);
        cerm.setReply_level(2);
        userMapper.addEssayReplyMap(cerm);
    }

    @Test
    public void testUpdateReply() {
        ComEssayReply comessayreply = new ComEssayReply();
        comessayreply.setReply_id(1);
        comessayreply.setUser_id(1001);
        comessayreply.setReply_content("update");
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comessayreply.setReply_edit_time(time);
        comessayreply.setIs_delete(0);
        comessayreply.setUser_anony(1);
        userMapper.updateReply(comessayreply,1);
    }

    @Test
    public void testDeleteReply() {
        userMapper.deleteReply(1,1002,1);
    }

    @Test
    public void testGetReply() {
        List<ComEssayReply> comEssayReplies = userMapper.getReply(1);
        Assert.assertNotNull(comEssayReplies);
        System.out.println(comEssayReplies.size());
    }

    @Test
    public void testAddReplyEvaluate() {
        ComReplyEvaluate comreplyevaluate = new ComReplyEvaluate();
        comreplyevaluate.setUser_id(1002);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comreplyevaluate.setEvaluate_time(time);
        comreplyevaluate.setEvaluate_type(0);
        userMapper.addReplyEvaluate(comreplyevaluate);
        Assert.assertNotNull(comreplyevaluate.getEvaluate_id());
    }

    @Test
    public void testAddReplyEvaluateMap() {
        ComReplyEvaluateMap crem = new ComReplyEvaluateMap();
        crem.setEvaluate_id(2);
        crem.setReply_id(1);
        userMapper.addReplyEvaluateMap(crem);
    }

    @Test
    public void testUpdateReplyEvaluate() {
        ComReplyEvaluate comreplyevaluate = new ComReplyEvaluate();
        comreplyevaluate.setUser_id(1002);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comreplyevaluate.setEvaluate_time(time);
        comreplyevaluate.setEvaluate_type(0);
        userMapper.updateReplyEvaluate(comreplyevaluate,1);
    }


    @Test
    public void testGetReplyEvaluate() {
        List<ComReplyEvaluate> comReplyEvaluates = userMapper.getReplyEvaluate(1);
         Assert.assertNotNull(comReplyEvaluates);
        System.out.println(comReplyEvaluates.size());
    }

    @Test
    public void testGetEssayLatest() {
        List<ComEssay> comEssays = userMapper.getEssayLatest();
        Assert.assertNotNull(comEssays);
        System.out.println(comEssays.size());
    }

    @Test
    public void testGetEssayReply() {
        List<ComEssayReply> comEssayReplies = userMapper.getEssayReply(1);
        Assert.assertNotNull(comEssayReplies);
        System.out.println(comEssayReplies.size());
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
