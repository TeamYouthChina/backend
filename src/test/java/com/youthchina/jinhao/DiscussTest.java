package com.youthchina.jinhao;


import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.DiscussMapper;
import com.youthchina.domain.jinhao.Discuss;
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
public class DiscussTest {
    @Resource
    DiscussMapper discussMapper;

    @Test
    public void get(){
        Discuss discuss = discussMapper.get(1);
        Assert.assertEquals(Integer.valueOf(3),discuss.getUser().getId());
    }
    @Test
    public void getDiscusses(){
        List<Discuss> discussList = discussMapper.getDiscusses(1);
        Assert.assertEquals(7,discussList.size());
    }

    @Test
    public void add(){
        Discuss discuss = new Discuss();
        discuss.setCommentId(1);
        discuss.setContent("1111");
        discuss.setIsAnony(0);
        User u = new User();
        u.setId(1);
        discuss.setUser(u);
        discussMapper.add(discuss);
        Assert.assertNotNull(discuss.getId());
    }

    @Test
    public void delete(){
        discussMapper.delete(1);
        Assert.assertNull(discussMapper.get(1));
    }

    @Test
    public void checkIfDiscussExist(){
        Assert.assertNotNull(discussMapper.checkIfDiscussExist(1));
    }

    @Test
    public void deleteAllDiscussOfComment(){
        discussMapper.deleteAllDiscussOfComment(1);
        Assert.assertEquals(0,discussMapper.getDiscusses(1).size());
    }
}
