package com.youthchina.service.zhongyang;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by zhongyangwu on 11/11/18.
 */
@Service
public class JwtServiceImpl implements JwtService {
    @Value("${security.token.expirationtime}")
    private String EXPRIATIONTIME;

    @Value("${security.token.key}")
    private String SECRET;

    @Value("${security.token.prefix}")
    private String TOKEN_PREFIX;

    @Value("${security.token.header}")
    private String HEADER;

    private UserMapper userMapper;

    private ObjectMapper objectMapper;

    @Autowired
    public JwtServiceImpl(UserMapper userMapper, ObjectMapper objectMapper) {
        this.userMapper = userMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * Add authentication in header and user body for login request.
     *
     * @param response the response
     * @param user  the user
     */
    @Override
    public void addAuthentication(HttpServletResponse response, User user) {
        Integer id = user.getId();
        String token = Jwts.builder().
                setSubject(id.toString()).
                setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(EXPRIATIONTIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER, TOKEN_PREFIX + " " + token);
        try {
            String responseBody = objectMapper.writeValueAsString(user);
            response.getWriter().write(responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Gets authentication.
     *
     * @param servletRequest the servlet request
     * @return the authentication
     */
    @Override
    public Authentication getAuthentication(HttpServletRequest servletRequest) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException {
        String token = servletRequest.getHeader(HEADER);
        if (token != null) {
            Integer id = null;
            try {
                id = Integer.valueOf(Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject());
            } catch (IllegalArgumentException ignored) {
            }
            User user = userMapper.findOne(id);
            return new JwtAuthentication(user, true);
        }
        return null;

    }
}
