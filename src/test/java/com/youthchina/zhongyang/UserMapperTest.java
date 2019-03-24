package com.youthchina.zhongyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.google.common.collect.Lists;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.Role;
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
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.findOne(1);
        Assert.assertEquals("yihao guo", user.getUsername());
        Assert.assertEquals("None", user.getFirstName());
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
        user.setFirstName("Test");
        user.setLastName("test");
        user.setNation("China");
        user.setRegisterDate("2018-10-11 11:11:11");
        user.setGender("male");
        user.setHired(false);
        user.setRole(Role.APPLICANT);
        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
        User createdUser = userMapper.findOne(user.getId());
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(createdUser.getEmail(), user.getEmail());
    }

    @Test
    public void testGetByList() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);

        List<User> users = userMapper.findAll(ids);
        Assert.assertEquals(2, users.size());
        for (User user : users) {
            if (user.getId() != 1 && user.getId() != 2) {
                Assert.fail();
            }
        }
    }

    @Test
    public void testDeleteUser() {
        User user = userMapper.findOne(1);
        Assert.assertNotNull(user);
        userMapper.delete(1);
        user = userMapper.findOne(1);
        Assert.assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        User user = userMapper.findOne(1);
        Assert.assertNotNull(user);
        Assert.assertEquals("CHN", user.getNation());
        Assert.assertNotNull(user.getEmail());
        user.setNation("USA");
        userMapper.update(user);
        user = userMapper.findOne(1);
        Assert.assertEquals("US", user.getNation());
    }

    @Test
    public void testCanRegister() {
        User user = new User();
        user.setUsername("Newtest");
        user.setPassword("sldjflskjlksf");
        user.setAge(12);
        user.setAvatarUrl("");
        user.setEmail("testNew!@test.com");
        user.setPhonenumber("00000011112222");
        user.setFirstName("Test");
        user.setNation("China");
        user.setRegisterDate("2018-10-11 11:11:11");
        user.setGender("male");
        user.setRole(Role.APPLICANT);
        Assert.assertTrue(userMapper.canRegister(user));
    }

    @Test
    public void testGetRoles() {
        List<Role> roles = userMapper.getRoles(1);
        Assert.assertTrue(roles.size() != 0);
    }

    @Test
    public void testSetRoles() {
        List<Role> roles = Lists.newArrayList(Role.APPLICANT, Role.ADMIN);
        userMapper.setRole(3, roles);

    }
}
