package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.CommentMapper;
import com.youthchina.domain.jinhao.Comment;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:New_Community_test.xml","classpath:New_SYS_test.xml"})
public class CommentTest {
    @Resource
    CommentMapper commentMapper;

    @Test
    public void add(){
        Comment comment = new Comment();
        comment.setTargetId(1);
        comment.setTargetType(1);
        comment.setContent("111234");
        User user = new User();
        user.setId(1);
        comment.setUser(user);
        comment.setIsAnony(0);
        commentMapper.add(comment);
        Assert.assertNotNull(comment.getId());
    }

    @Test
    public void get(){
        Comment comment = commentMapper.get(1);
        Assert.assertEquals(Integer.valueOf(2),comment.getUser().getId());
    }

    @Test
    public void getComments(){
        List<Comment> commentList = commentMapper.getComments(3,1);
        Assert.assertEquals(2,commentList.size());
    }

    @Test
    public void delete(){
        commentMapper.delete(1);
        Assert.assertNull(commentMapper.get(1));
    }

    @Test
    public void deleteComments(){
        commentMapper.deleteComments(3,1);
        List<Comment> comments = commentMapper.getComments(3,1);
        Assert.assertEquals(0,comments.size());
    }

    @Test
    public void checkIfExist(){
        Assert.assertNotNull(commentMapper.checkIfCommentExist(1));
    }
}
