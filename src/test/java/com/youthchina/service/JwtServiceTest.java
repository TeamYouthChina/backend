package com.youthchina.service;

import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.service.user.JwtService;
import com.youthchina.util.AuthGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by zhongyangwu on 5/6/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtServiceTest {
    private final AuthGenerator authGenerator;
    @Autowired
    private JwtService jwtService;

    public JwtServiceTest() {
        authGenerator = new AuthGenerator();
    }


    @Test
    public void encodeDecodeRegisterToken() throws ForbiddenException {
        User user = authGenerator.getUser(Role.APPLICANT);
        String token = this.jwtService.encodeRegisterToken(user);
        Integer id = this.jwtService.decodeRegisterToken(token);
        Assert.assertNotNull(id);
        Assert.assertEquals(Integer.valueOf(1), id);
    }
}
