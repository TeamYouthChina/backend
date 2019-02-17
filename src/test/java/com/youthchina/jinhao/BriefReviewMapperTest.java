package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:review.xml"})
public class BriefReviewMapperTest {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Test
    public void add(){
        BriefReview briefReview = new BriefReview();
        briefReview.setRela_id(1);
        briefReview.setRela_type(2);
        briefReview.setReview_content("aaaa");
        briefReview.setReview_time(new Timestamp(System.currentTimeMillis()));
        briefReviewMapper.add(briefReview);
        BriefReview briefReview1 = briefReviewMapper.simplyGetReview(briefReview.getReview_id());
        Assert.assertNotNull(briefReview1);
        Assert.assertEquals(briefReview.getReview_id(),briefReview1.getReview_id());
        briefReviewMapper.createReviewMap(briefReview.getReview_id(), 1,2,1);
        List<BriefReview> briefReviewList = briefReviewMapper.getUsersReview(1);
        Assert.assertEquals(2, briefReviewList.size());
    }

    @Test
    public void get(){
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        Assert.assertEquals(Integer.valueOf(1), briefReview.getReview_id());
        BriefReview briefReview1 = briefReviewMapper.get(1);
        Assert.assertEquals(3, briefReview1.getComments().size());
        List<Integer> id = new LinkedList<>();
        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);
        List<BriefReview> briefReviewList = briefReviewMapper.getList(id);
        Assert.assertEquals(3, briefReviewList.size());
    }

    @Test
    public void update(){
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        briefReview.setReview_content("asd");
        briefReviewMapper.update(briefReview);
        BriefReview briefReview1 = briefReviewMapper.simplyGetReview(1);
        Assert.assertEquals("asd", briefReview1.getReview_content());
    }

    @Test
    public void delete(){
        briefReviewMapper.delete(1);
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        Assert.assertNull(briefReview);
    }

    @Test
    public void countReviewAgreement(){
        int count = briefReviewMapper.countReviewAgreement(1);
        Assert.assertEquals(2,count);
    }
    @Test
    public void addReviewEvaluation(){

    }
}
