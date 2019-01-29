package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.InfluenceMapper;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.jinhao.communityQA.PersonInfluence;
import com.youthchina.domain.tianjian.ComFriendRelation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml", "classpath:answers.xml", "classpath:comments.xml", "classpath:discuss.xml", "classpath:videos.xml"})
public class InfluenceTest {
    @Autowired
    InfluenceMapper influenceMapper;

    @Test
    public void getInfluenceById(){
        PersonInfluence personInfluence = influenceMapper.getInfluenceByUserId(1);
        Assert.assertNotNull(personInfluence);
        Student student = personInfluence.getStudent();
        Assert.assertNotNull(student);
        System.out.println(student.getId());
        List<ComFriendRelation> comFriendRelationList = personInfluence.getComFriendRelations();
        Assert.assertEquals(1, comFriendRelationList.size());
    }
}
