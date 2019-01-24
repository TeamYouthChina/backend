package com.youthchina.jinhao;

import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.CommunityQAService;
import com.youthchina.service.jinhao.communityQA.CommunityQAServiceImplement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {

    @InjectMocks
    private CommunityQAService communityQAService = new CommunityQAServiceImplement();

    @Mock
    private CommunityQAMapper communityQAMapper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testQuestion() throws NotFoundException {
        Question question = new Question();
        question.setQues_id(2);
        when(communityQAMapper.getQuestion(any(Integer.class))).thenReturn(question);
        Question question1 = communityQAService.getQuestion(1);
        Assert.assertEquals(question, question1);
    }
}
