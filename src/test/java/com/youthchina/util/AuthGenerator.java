package com.youthchina.util;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

/**
 * Created by zhongyangwu on 2/6/19.
 */
public class AuthGenerator {
    private JwtAuthentication createAuthentication(Integer role) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setRole(role);
        user.setUsername("YihaoGuo");
        user.setPassword(encoder.encode("123456"));
        user.setId(1);
        user.setEmail("test@test.com");
        user.setNation("China");
        user.setGender("male");
        user.setPhonenumber("2022922222");
        return new JwtAuthentication(user, true);
    }

    public RequestPostProcessor authentication(Integer role) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(this.createAuthentication(role));
        return SecurityMockMvcRequestPostProcessors.securityContext(securityContext);
    }

    public RequestPostProcessor authentication(){
        return this.authentication(1);
    }
}
