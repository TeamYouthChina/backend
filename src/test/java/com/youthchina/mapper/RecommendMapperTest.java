package com.youthchina.mapper;

import com.youthchina.dao.jinhao.NewRecommendMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.recommendation.RecommendService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RecommendMapperTest {
    @Resource
    NewRecommendMapper newRecommendMapper;

    @Resource
    RecommendService recommendService;
    @Test
    public void tag(){
        newRecommendMapper.addTag(92,23,43);
        Assert.assertNotNull(newRecommendMapper.isTagExist(92,23,43));
        newRecommendMapper.deleteTag(92,23,43);
        Assert.assertNull(newRecommendMapper.isTagExist(92,23,43));
    }

    @Test
    public void getUserLabel(){
        newRecommendMapper.addTag(1,100,2);
        newRecommendMapper.addTag(2,100,2);
        newRecommendMapper.addTag(46,100,2);
        List<Integer> labels = newRecommendMapper.getUserLabel(2);
        Assert.assertEquals(3,labels.size());
        for(Integer i : labels){
            if(i != 1 && i != 2 && i != 46){
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomQuestion(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,1,2);
        newRecommendMapper.addTag(66,1,5);
        newRecommendMapper.addTag(77,1,3);
        newRecommendMapper.addTag(88,1,9);
        List<Integer> quesIds = newRecommendMapper.getRecommendQuestion(labels);
        Assert.assertEquals(3,quesIds.size());
        for(Integer i : quesIds){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }
    }
    @Test
    public void getRecomEassy(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,2,2);
        newRecommendMapper.addTag(66,2,5);
        newRecommendMapper.addTag(77,2,3);
        newRecommendMapper.addTag(88,2,9);
        List<Integer> ids = newRecommendMapper.getRecommendEassy(labels);
        Assert.assertEquals(3,ids.size());
        for(Integer i : ids){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }
    }

    @Test
    public void getRecomBr(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,3,2);
        newRecommendMapper.addTag(66,3,5);
        newRecommendMapper.addTag(77,3,3);
        newRecommendMapper.addTag(88,3,9);
        List<Integer> ids = newRecommendMapper.getRecommendBriefReview(labels);
        Assert.assertEquals(3,ids.size());
        for(Integer i : ids){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }
    }
    @Test
    public void getRecomJob(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,300,2);
        newRecommendMapper.addTag(66,300,5);
        newRecommendMapper.addTag(77,300,3);
        newRecommendMapper.addTag(88,300,9);
        List<Integer> ids = newRecommendMapper.getRecommendJob(labels);
        Assert.assertEquals(3,ids.size());
        for(Integer i : ids){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }
    }
    @Test
    public void getRecomCompany(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,200,2);
        newRecommendMapper.addTag(66,200,5);
        newRecommendMapper.addTag(77,200,3);
        newRecommendMapper.addTag(88,200,9);
        List<Integer> ids = newRecommendMapper.getRecommendCompany(labels);
        Assert.assertEquals(3,ids.size());
        for(Integer i : ids){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }
    }
    @Test
    public void getRecomUser(){
        List<Integer> labels = new ArrayList<>();
        labels.add(66);
        labels.add(77);
        newRecommendMapper.addTag(66,100,2);
        newRecommendMapper.addTag(66,100,5);
        newRecommendMapper.addTag(77,100,3);
        newRecommendMapper.addTag(88,100,9);
        List<Integer> ids = newRecommendMapper.getRecommendUser(labels);
        Assert.assertEquals(3,ids.size());
        for(Integer i : ids){
            if(i != 2 && i != 5 && i != 3){
                Assert.fail();
            }
        }

    }
    @Test
    public void getTag() throws NotFoundException {
        recommendService.addTag(1,100,1);
        recommendService.addTag(2,100,1);
        recommendService.addTag(3,100,1);
        recommendService.addTag(3,100,2);
        recommendService.addTag(3,100,3);
        recommendService.addTag(3,100,4);
        List<User> labels = recommendService.getRecommendUser(1);
        Assert.assertEquals(3,labels.size());
        for(User user : labels){
            System.out.println(user.getId());
        }
    }
}
