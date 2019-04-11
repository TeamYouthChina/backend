package com.youthchina.util;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhongyangwu on 2/6/19.
 */
@Transactional
public class AuthGenerator {
    private JwtAuthentication createAuthentication(Role role) {
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

    public RequestPostProcessor authentication(Role role) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(this.createAuthentication(role));
        return SecurityMockMvcRequestPostProcessors.securityContext(securityContext);
    }

    public RequestPostProcessor authentication(Role role, Integer userId) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(this.createAuthentication(role, userId));
        return SecurityMockMvcRequestPostProcessors.securityContext(securityContext);
    }

    private JwtAuthentication createAuthentication(Role role, Integer userId) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setRole(role);
        user.setUsername("YihaoGuo");
        user.setPassword(encoder.encode("123456"));
        user.setId(userId);
        user.setEmail("test@test.com");
        user.setNation("China");
        user.setGender("male");
        user.setPhonenumber("2022922222");
        return new JwtAuthentication(user, true);
    }

    public RequestPostProcessor authentication() {
        return this.authentication(Role.APPLICANT);
    }

    public RequestPostProcessor authentication(Integer userId) {
        return this.authentication(Role.APPLICANT, userId);
    }
}
