package com.youthchina.service;

import com.youthchina.dao.jinhao.AnswerMapper;
import com.youthchina.service.community.*;
import com.youthchina.service.user.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhongyangwu on 4/16/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class AnswerServiceTest {

    @InjectMocks
    AnswerServiceImpl answerService;

    @Mock
    AnswerMapper answerMapper;

    @Mock
    RichTextServiceImpl richTextService;

    @Mock
    QuestionServiceImpl questionService;

    @Mock
    CommentServiceImpl commentService;

    @Mock
    UserServiceImpl userService;

    @Mock
    AttentionService attentionService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void delete() throws Exception {

    }
}
