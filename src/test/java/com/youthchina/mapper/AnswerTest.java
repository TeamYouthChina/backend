package com.youthchina.mapper;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.domain.jinhao.Answer;
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
public class AnswerTest {
    @Resource
    AnswerMapper answerMapper;

    @Test
    public void get(){
        Answer answer = answerMapper.get(1);
        Assert.assertEquals(Integer.valueOf(5),answer.getUser().getId());
    }

    @Test
    public void getAnswers(){
        List<Answer> answerList = answerMapper.getAnswers(1);
        Assert.assertEquals(1,answerList.size());
    }

    @Test
    public void add(){
        Answer answer = new Answer();
        ComRichText comRichText = new ComRichText();
        comRichText.setTextId(1);
        answer.setBody(comRichText);
        answer.setTargetType(1);
        answer.setTargetId(1);
        answer.setIsAnony(0);
        User u  = new User();
        u.setId(1);
        answer.setUser(u);
        answerMapper.add(answer);
        Assert.assertNotNull(answer.getId());
    }

    @Test
    public void update(){
        Answer answer = answerMapper.get(1);
        answer.setIsAnony(1);
        answerMapper.update(answer);
        Answer answer1 = answerMapper.get(1);
        Assert.assertEquals(Integer.valueOf(1), answer1.getIsAnony());
    }

    @Test
    public void delete(){
        answerMapper.delete(1);
        Assert.assertNull(answerMapper.get(1));
    }

    @Test
    public void checkIfAnswerExist(){
        int i = answerMapper.checkIfAnswerExist(1);
        Assert.assertEquals(1,i);
    }

    @Test
    public void getMyAnswer(){
        List<Answer> answers = answerMapper.getMyAnswers(5);
        Assert.assertEquals(13,answers.size());
    }
}
