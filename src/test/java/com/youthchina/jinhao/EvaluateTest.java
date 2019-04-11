package com.youthchina.jinhao;


import com.youthchina.dao.jinhao.EvaluateMapper;
import com.youthchina.domain.jinhao.Evaluate;
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
public class EvaluateTest {
    @Resource
    EvaluateMapper evaluateMapper;

    @Test
    public void isEverEvaluate(){
        Evaluate evaluate = evaluateMapper.isEverEvaluate(1,1,2);
        Assert.assertEquals(Integer.valueOf(1),evaluate.getType());
        Integer id = evaluate.getId();
        evaluateMapper.reDownVote(id);
        evaluate = evaluateMapper.isEverEvaluate(1,1,2);
        Assert.assertEquals(Integer.valueOf(2),evaluate.getType());
        evaluateMapper.reUpvote(id);
        evaluate = evaluateMapper.isEverEvaluate(1,1,2);
        Assert.assertEquals(Integer.valueOf(1),evaluate.getType());
        evaluateMapper.cancel(id);
        evaluate = evaluateMapper.isEverEvaluate(1,1,2);
        Assert.assertEquals(Integer.valueOf(3),evaluate.getType());
    }

    @Test
    public void upvote(){
        evaluateMapper.upvote(2,3,5);
        Evaluate evaluate = evaluateMapper.isEverEvaluate(2,3,5);
        Assert.assertEquals(Integer.valueOf(1),evaluate.getType());
    }

    @Test
    public void downvote(){
        evaluateMapper.downvote(2,3,5);
        Evaluate evaluate = evaluateMapper.isEverEvaluate(2,3,5);
        Assert.assertEquals(Integer.valueOf(2),evaluate.getType());
    }

    @Test
    public  void count(){
        Assert.assertEquals(Integer.valueOf(2), evaluateMapper.countUpvote(1,1));
    }

    @Test
    public void getMy(){
        List<Integer> upvotes = evaluateMapper.getMyUpvote(1,2);
        List<Integer> downvotes = evaluateMapper.getMyDownVote(1,2);
        Assert.assertEquals(2,upvotes.size());
        Assert.assertEquals(1,downvotes.size());
    }
}
