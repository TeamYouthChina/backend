package com.youthchina.mapper;

import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BriefReviewTest {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Test
    public void get(){
        BriefReview briefReview = briefReviewMapper.get(1);
        Assert.assertEquals(Integer.valueOf(1),briefReview.getUser().getId());
    }

    @Test
    public void add(){
        BriefReview briefReview = new BriefReview();
        ComRichText comRichText = new ComRichText();
        comRichText.setTextId(1);
        User u = new User();
        u.setId(1);
        briefReview.setUser(u);
        briefReview.setBody(comRichText);
        briefReview.setRelaType(1);
        briefReview.setRelaId(1);
        briefReviewMapper.add(briefReview);
        Assert.assertNotNull(briefReview.getId());
    }

    @Test
    public void checkIfExist(){
        Assert.assertNotNull(briefReviewMapper.checkIfBriefReviewExist(1));
    }

    @Test
    public void delete(){
        briefReviewMapper.delete(1);
        Assert.assertNull(briefReviewMapper.checkIfBriefReviewExist(1));
    }

    @Test
    public void update(){
        BriefReview briefReview = briefReviewMapper.get(1);
        briefReview.setRelaId(38);
        briefReviewMapper.update(briefReview);
        Assert.assertEquals(Integer.valueOf(38), briefReviewMapper.get(1).getRelaId());
    }
    @Test
    public void getMyBriefReview(){
        List<BriefReview> briefReviewList = briefReviewMapper.getMyBriefReview(1);
        Assert.assertEquals(4,briefReviewList.size());
    }
}
