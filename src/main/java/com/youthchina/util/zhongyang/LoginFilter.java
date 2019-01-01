package com.youthchina.util.zhongyang;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.StatusDTO;
import com.youthchina.service.zhongyang.JwtService;
import jdk.jshell.Snippet;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.server.MethodNotAllowedException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * If http request has a JWT, this filter will decode it.
 * <p>
 * Created by zhongyangwu on 11/11/18.
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    private JwtService jwtService;

    protected LoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public LoginFilter(String url, AuthenticationManager authenticationManager, JwtService tokenAuthenticationService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
        this.jwtService = tokenAuthenticationService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        if (!httpServletRequest.getMethod().equals("POST")) {
            List<HttpMethod> supportedMethod = new ArrayList<>();
            supportedMethod.add(HttpMethod.POST);
            throw new MethodNotAllowedException(httpServletRequest.getMethod(), supportedMethod);
        }
        Authentication authentication = jwtService.getAuthentication(httpServletRequest);
        if(authentication != null){
            return authentication;
        }
        User user = new User();
        try {
            user.setId(Integer.valueOf(httpServletRequest.getParameter("id")));
            user.setPassword(httpServletRequest.getParameter("password"));
        } catch (NumberFormatException e) {
            throw new BadCredentialsException("Bad Credential");
        }
        return getAuthenticationManager().authenticate(
                new JwtAuthentication(user)
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        jwtService.addAuthentication(response, (User) authResult.getPrincipal());
    }
}
