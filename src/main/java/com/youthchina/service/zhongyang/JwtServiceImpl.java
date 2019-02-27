package com.youthchina.service.zhongyang;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.dao.zhongyang.UserMapper;
import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.BaseException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
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
     * @param user     the user
     */
    @Override
    public void addAuthentication(HttpServletResponse response, User user) throws BaseException {
        Integer id = user.getId();
        JwtSubject jwtSubject = new JwtSubject(id.toString(), Calendar.getInstance().getTimeInMillis());
        String subject = "";
        try {
            subject = objectMapper.writeValueAsString(jwtSubject);
        } catch (JsonProcessingException e) {
            throw new BaseException(500, 5000, "cannot serilize the jwt");
        }
        String token = Jwts.builder().
                setSubject(subject).
                setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(EXPRIATIONTIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER, TOKEN_PREFIX + " " + token);
        try {
            String responseBody = objectMapper.writeValueAsString(new Response(user));
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
            String subject = "";
            try {
                subject = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
                JwtSubject jwtSubject = objectMapper.readValue(subject, JwtSubject.class);
                id = Integer.valueOf(jwtSubject.id);
                Timestamp createAt = new Timestamp(jwtSubject.create_at);
            } catch (IllegalArgumentException | IOException ignored) {
            }
            User user = userMapper.findOne(id);
            return new JwtAuthentication(user, true);
        }
        return null;

    }

    class JwtSubject {
        String id;
        Long create_at;

        JwtSubject() {
        }

        JwtSubject(String id, Long create_at) {
            this.id = id;
            this.create_at = create_at;
        }
    }
}
