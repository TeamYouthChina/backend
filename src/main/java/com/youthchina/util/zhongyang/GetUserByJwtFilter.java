package com.youthchina.util.zhongyang;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.zhongyang.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * get user info from JWT
 * <p>
 * Created by zhongyangwu on 11/11/18.
 */
public class GetUserByJwtFilter extends GenericFilter {
    private JwtService jwtService;


    public GetUserByJwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            Authentication authentication = null;
            try {
                authentication = jwtService.getAuthentication((HttpServletRequest) servletRequest);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(servletRequest, servletResponse);
                if (authentication instanceof JwtAuthentication && ((JwtAuthentication) authentication).isNeedRenew()) {
                    //renew token
                    jwtService.addAuthentication((HttpServletResponse) servletResponse, (User) authentication.getPrincipal());
                }
                SecurityContextHolder.getContext().setAuthentication(null);//Clear after request
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
                BadCredentialsException exception = new BadCredentialsException("cannot auth");
                SecurityContextHolder.getContext().setAuthentication(null);
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(401, exception.toString());
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
}
