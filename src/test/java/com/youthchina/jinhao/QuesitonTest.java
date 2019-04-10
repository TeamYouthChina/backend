package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.QuestionMapper;
import com.youthchina.domain.jinhao.Question;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
public class QuesitonTest {
    @Resource
    QuestionMapper questionMapper;

    @Test
    public void add(){
        Question question = new Question();
        question.setTitle("11111");
        question.setAbbre("111111");
        question.setIsAnony(0);
        question.setRelaId(1);
        question.setRelaType(1);
        ComRichText comRichText = new ComRichText();
        comRichText.setTextId(1);
        User u = new User();
        u.setId(1);
        question.setUser(u);
        question.setBody(comRichText);
        questionMapper.add(question);
        Assert.assertNotNull(question.getId());
    }
    @Test
    public void get(){
        Question question = questionMapper.get(1);
        Assert.assertEquals(Integer.valueOf(1),question.getUser().getId());
    }

    @Test
    public void delete(){
        questionMapper.delete(1);
        Assert.assertNull(questionMapper.get(1));
        Assert.assertNull(questionMapper.checkIfQuestionExist(1));
    }

    @Test
    public void update(){
        Question question = questionMapper.get(1);
        question.setTitle("avcd");
        questionMapper.edit(question);
        Assert.assertEquals("avcd",questionMapper.get(1).getTitle());
    }
}
