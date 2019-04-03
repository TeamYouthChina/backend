package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.VideoMapper;
import com.youthchina.domain.jinhao.Video;
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
@DatabaseSetup({"classpath:New_Community_test.xml","classpath:New_SYS_test.xml"})
public class VideoTest {
    @Resource
    VideoMapper videoMapper;

    @Test
    public void add(){
        Video video = new Video();
        video.setTitle("11");
        video.setName("11");
        video.setRelaType(1);
        video.setRelaId(1);
        video.setViewCount(0);
        video.setDescription("111");
        User user = new User();
        user.setId(1);
        video.setUser(user);
        videoMapper.add(video);
        Assert.assertNotNull(video.getId());
    }

    @Test
    public void get(){
        Video video = videoMapper.get(1);
        Assert.assertEquals(Integer.valueOf(1),video.getUser().getId());
    }

    @Test
    public void update(){
        Video video = videoMapper.get(1);
        video.setViewCount(10);
        videoMapper.update(video);
        Assert.assertEquals(Integer.valueOf(10),video.getViewCount());
    }

    @Test
    public void delete(){
        videoMapper.delete(1);
        Assert.assertNull(videoMapper.get(1));
    }

    @Test
    public void checkIfVideoExist(){
        Assert.assertNotNull(videoMapper.checkIfVideoExist(1));
    }
}
