package com.youthchina.service.user;

import com.youthchina.domain.zhongyang.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhongyangwu on 11/11/18.
 */
@Service
public interface JwtService {
    void addAuthentication(HttpServletResponse response, User user) throws IOException;

    Authentication getAuthentication(HttpServletRequest servletRequest) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException;
}
