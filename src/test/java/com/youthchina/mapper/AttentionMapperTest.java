package com.youthchina.mapper;

import com.youthchina.dao.jinhao.AttentionMapper;
import com.youthchina.util.dictionary.AttentionTargetType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhongyangwu on 4/15/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AttentionMapperTest {
    @Autowired
    AttentionMapper communityAttentionMapper;

    @Test
    public void testGetAttention() {
        List res = communityAttentionMapper.getAttentionList(2, AttentionTargetType.ESSAY);
        Assert.assertEquals(2, res.size());
        Assert.assertTrue(res.contains(1));
        Assert.assertTrue(res.contains(3));

    }

    @Test
    public void testCancelAttention() {
        Assert.assertTrue(communityAttentionMapper.getAttentionList(2, AttentionTargetType.ESSAY).contains(1)); //User 2 has Essay 4
        communityAttentionMapper.cancelAttention(AttentionTargetType.ESSAY, 1, 2);
        Assert.assertFalse(communityAttentionMapper.getAttentionList(2, AttentionTargetType.ESSAY).contains(1)); //User 2 has Essay 4
    }

    @Test
    public void cancelAttentionByEntity(){
        Assert.assertTrue(communityAttentionMapper.getAttentionList(2, AttentionTargetType.ESSAY).contains(1)); //User 2 has Essay 4
        communityAttentionMapper.cancelAttentionByEntity(1, AttentionTargetType.ESSAY); //delete essay 1;
        Assert.assertFalse(communityAttentionMapper.getAttentionList(2, AttentionTargetType.ESSAY).contains(1)); //User 2 has Essay 4
    }
}
