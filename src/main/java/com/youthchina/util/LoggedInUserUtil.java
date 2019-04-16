package com.youthchina.util;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.error.BaseError;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by zhongyangwu on 4/16/19.
 */
public class LoggedInUserUtil {

    public static User currentUser() {
        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null || !principal.isAuthenticated()) {
            return null;
        }
        if (!(principal instanceof JwtAuthentication)) {
            throw new BaseError("Authentication is not JwtAuthentication", LoggedInUserUtil.class);
        }
        return (User) principal.getDetails();
    }
}
