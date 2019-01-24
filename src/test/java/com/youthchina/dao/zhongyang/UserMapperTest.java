package com.youthchina.dao.zhongyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/12/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:users.xml"})
@DatabaseTearDown(value = {"classpath:users.xml"}, type = DatabaseOperation.DELETE)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.findOne(1);
        Assert.assertEquals("yihao guo", user.getUsername());
        Assert.assertEquals("None", user.getRealName());
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("sldjflskjlksf");
        user.setAge(12);
        user.setAvatarUrl("");
        user.setEmail("test@test.com");
        user.setPhonenumber("12321312334");
        user.setRealName("Test");
        user.setNation("China");
        user.setRegisterDate("2018-10-11 11:11:11");
        user.setGender("male");
        user.setRole(1);
        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
        User createdUser = userMapper.findOne(user.getId());
        Assert.assertNotNull(createdUser);
    }

    @Test
    public void testGetByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<User> users = userMapper.findAll(ids);
        Assert.assertEquals(2, users.size());
        for (User user : users) {
            if(user.getId() != 1 && user.getId() != 2){
                Assert.fail();
            }
        }
    }

}
