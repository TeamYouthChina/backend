package com.youthchina.util;

import com.youthchina.domain.zhongyang.Gender;
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

import javax.annotation.Nonnull;
import java.sql.Timestamp;

/**
 * Created by zhongyangwu on 2/6/19.
 */
@Transactional
public class AuthGenerator {
    private JwtAuthentication createAuthentication(Role role) {
        User user = getUser(role);
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
        User user = this.getUser(role);
        user.setId(userId);
        return new JwtAuthentication(user, true);
    }

    public @Nonnull User getUser(Role role) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setRole(role);
        user.setLastName("Guo");
        user.setFirstName("Yihao");
        user.setRegisterTime(Timestamp.valueOf("2019-01-01 01:01:01.0"));
        user.setPassword(encoder.encode("123456"));
        user.setId(1);
        user.setEmail("test@test.com");
        user.setGender(Gender.MALE);
        user.setPhonenumber("2022922222");
        user.setMailVerified(true);
        user.setPhoneVerified(true);
        return user;
    }

    public RequestPostProcessor authentication() {
        return this.authentication(Role.APPLICANT);
    }

    public RequestPostProcessor authentication(Integer userId) {
        return this.authentication(Role.APPLICANT, userId);
    }
}
