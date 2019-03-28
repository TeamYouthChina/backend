package com.youthchina.util.zhongyang;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.zhongyang.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 11/11/18.
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @Autowired
    public JwtAuthenticationProvider(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (authentication.isAuthenticated()) {
            return authentication;
        }
        User intentUser = (User) authentication.getPrincipal();
        User actualUser = null;
        try {
            actualUser = userService.get(intentUser.getId());
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("Cannot find user");
        }
        if (actualUser == null) {
            //if no user with target id is in DB
            return null;
        }
        if (passwordEncoder.matches(intentUser.getPassword(), actualUser.getPassword())) {
            //if password is correct
            return new JwtAuthentication(actualUser);
        } else {
            throw new BadCredentialsException("Password does not match");
        }

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtAuthentication.class.isAssignableFrom(aClass);
    }
}
