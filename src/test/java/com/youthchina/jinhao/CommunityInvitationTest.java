package com.youthchina.jinhao;

import com.youthchina.dao.jinhao.CommunityInvitationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommunityInvitationTest {
    @Resource
    CommunityInvitationMapper communityInvitationMapper;
//
//    @Test
//    public void addAndCheck(){
//        communityInvitationMapper.add(1,1,5,3);
//        Assert.assertNotNull(communityInvitationMapper.checkIfInvitationExist(1,1,5,3));
//    }
//
    @Test
    public void get(){
//        CommunityInvitation communityInvitation = communityInvitationMapper.get(1);

    }
}
