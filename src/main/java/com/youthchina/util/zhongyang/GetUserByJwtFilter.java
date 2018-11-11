package com.youthchina.util.zhongyang;

import com.youthchina.service.zhongyang.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * get user info from JWT
 * <p>
 * Created by zhongyangwu on 11/11/18.
 */
public class GetUserByJwtFilter extends AbstractAuthenticationProcessingFilter {
    private JwtService jwtService;

    protected GetUserByJwtFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public GetUserByJwtFilter(String url, AuthenticationManager authenticationManager, JwtService jwtService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        this.jwtService = jwtService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        //Get JWT
        return jwtService.getAuthentication(httpServletRequest);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(401);
    }
}
