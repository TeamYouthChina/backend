package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.AttentionMapper;
import com.youthchina.domain.jinhao.Attention;
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
@DatabaseSetup({"classpath:test.xml"})
public class AttentionTest {

    @Resource
    AttentionMapper attentionMapper;

    @Test
    public void isEverAttention(){
        Attention attention = attentionMapper.isEverAttention(1,1,2);
        Assert.assertEquals(Integer.valueOf(0),attention.getIsCancel());
    }

    @Test
    public void cancelAndReFollow(){
        attentionMapper.cancel(17);
        Assert.assertNull(attentionMapper.get(17));
        attentionMapper.reFollow(17);
        Assert.assertNotNull(attentionMapper.get(17));
    }

    @Test
    public void follow(){
        attentionMapper.follow(5,1,5);
        Assert.assertNotNull(attentionMapper.isEverAttention(5,1,5));
    }

    @Test
    public void getAllFollows(){
        List<Integer> ids = attentionMapper.getAllfollows(1,2);
        Assert.assertEquals(2,ids.size());
        for(Integer id : ids){
            if(id != 1 && id != 3){
                Assert.fail();
            }
        }
    }
}
