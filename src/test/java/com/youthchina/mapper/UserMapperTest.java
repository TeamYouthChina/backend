package com.youthchina.mapper;

import com.google.common.collect.Lists;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.Gender;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 11/12/18.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetUser() {
        User user = userMapper.findOne(2);
        Assert.assertEquals("DDD", user.getFirstName());
        Assert.assertEquals(true, user.getHired());
        Assert.assertEquals(true, user.getMailVerified());
        Assert.assertEquals("1970-01-01", user.getDateOfBirth().toString());
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setPassword("sldjflskjlksf");
        user.setAvatarUrl("");
        user.setEmail("test@test.com");
        user.setPhonenumber("12321312334");
        user.setFirstName("Test");
        user.setLastName("test");
        user.setNation("China");
        user.setRegisterDate(Timestamp.valueOf("2018-10-11 11:11:11"));
        user.setGender(Gender.MALE);
        user.setHired(false);
        user.setRole(Role.APPLICANT);
        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
        User createdUser = userMapper.findOne(user.getId());
        Assert.assertNotNull(createdUser);
        Assert.assertEquals(createdUser.getEmail(), user.getEmail());
    }

    @Test
    public void testDeleteUser() {
        User user = userMapper.findOne(2);
        Assert.assertNotNull(user);
        userMapper.delete(2);
        user = userMapper.findOne(2);
        Assert.assertNull(user);
    }

    @Test
    public void testUpdateUser() {
        User user = userMapper.findOne(3);
        Assert.assertNotNull(user);
        Assert.assertEquals("CHN", user.getNation());
        Assert.assertNotNull(user.getEmail());
        user.setNation("USA");
        userMapper.update(user);
        user = userMapper.findOne(3);
        Assert.assertEquals("USA", user.getNation());
    }

    @Test
    public void testCanRegister() {
        User user = new User();
        user.setPassword("sldjflskjlksf");
        user.setAvatarUrl("");
        user.setEmail("testNew!@test.com");
        user.setPhonenumber("00000011112222");
        user.setFirstName("Test");
        user.setNation("China");
        user.setRegisterDate(Timestamp.valueOf("2018-10-11 11:11:11"));
        user.setGender(Gender.MALE);
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
        List<Role> roles = Lists.newArrayList(Role.HR, Role.ADMIN);
        userMapper.setRole(9, roles);

    }
}

