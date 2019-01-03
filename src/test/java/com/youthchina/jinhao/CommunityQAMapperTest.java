package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.Question;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml"})
public class CommunityQAMapperTest extends BaseTest{
    @Autowired
    CommunityQAMapper communityQAMapper;

    @Test
    public void getQuestion(){
        Question question = communityQAMapper.getQuestion(1);
        Assert.assertEquals("第一个问题", question.getQues_title());
    }

    @Test
    public void addQuestion(){
        Question question = new Question();
        question.setQues_title("被插入的问题");
        question.setQues_abbre("这问题是新插入的");
        question.setQues_body("这是一个新插入的问题，巴拉巴拉巴拉");
        question.setQues_pub_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        question.setQues_edit_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        question.setQues_delete(0);
        question.setQues_delete_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        communityQAMapper.addQuestion(question);
        Assert.assertNotNull(question.getQues_id());
        Question createQuestion = communityQAMapper.getQuestion(question.getQues_id());
        Assert.assertNotNull(createQuestion);
    }

    @Test
    public void createMapBetweenQuestionAndUser(){
        communityQAMapper.createMapBetweenQuestionAndUser(2, 1);
        List<Question> questionList = communityQAMapper.getMyQuestions(1);
        Assert.assertEquals(2, questionList.size());
        for(Question question : questionList){
            if(question.getQues_id() != 1 || question.getQues_id() != 2){
                Assert.fail();
            }
        }

    }
    @Test
    public void updateQuestion(){
        Question question = communityQAMapper.getQuestion(1);
        question.setQues_title("修改问题标题");
        question.setQues_abbre("修改问题简介");
        question.setQues_body("修改问题主题");
        question.setQues_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.editQuestion(question);

    }

}
