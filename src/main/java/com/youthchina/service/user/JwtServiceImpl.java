package com.youthchina.service.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.exception.ForbiddenException;
import com.youthchina.exception.zhongyang.exception.InternalStatusCode;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
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

    @Value("${security.register.token.key}")
    private String REGISTER_SECRET;

    @Value("${security.register.token.expirationtime}")
    private String REGISTER_EXPRIATIONTIME;

    @Autowired
    private UserService userService;

    private ObjectMapper objectMapper;

    @Autowired
    public JwtServiceImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Add authentication in header and user body for login request.
     *
     * @param response the response
     * @param user     the user
     */
    @Override
    public void addAuthentication(HttpServletResponse response, User user) throws IOException {
        response.setHeader("Content-Type", "application/json;charset=utf8");
        Integer id = user.getId();
        String token = this.getAuthenticationToken(id);
        response.addHeader(HEADER, TOKEN_PREFIX + " " + token);
        String responseBody = objectMapper.writeValueAsString(new Response(new UserDTO(user)));
        response.getWriter().write(responseBody);


    }

    /**
     * Get token by user id
     *
     * @param userId id of authenticated user
     * @return token
     */
    @Override
    public String getAuthenticationToken(Integer userId) {
        return Jwts.builder().
                setSubject(userId.toString()).
                setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(EXPRIATIONTIME)))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
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
            boolean needRenew = false;
            Date expireTime = null;
            try {
                Jws<Claims> jwtClaim = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, ""));
                expireTime = jwtClaim.getBody().getExpiration();
                id = Integer.valueOf(jwtClaim.getBody().getSubject());
                if (expireTime.before(new Timestamp(Calendar.getInstance().getTimeInMillis()
                        + 15 * 100))) {
                    // if the expired time is within 15min from now.
                    // renew token
                    needRenew = true;
                }
            } catch (IllegalArgumentException ignored) {
            }
            User user = null;
            try {
                user = userService.get(id);
            } catch (NotFoundException e) {
                return null; //cannot auth
            }
            return new JwtAuthentication(user, true, needRenew);
        }
        return null;

    }

    @Override
    public String encodeRegisterToken(@Nonnull User user) {
        return Jwts.builder().
                setSubject(user.getId().toString()).
                setExpiration(new Date(System.currentTimeMillis() + Long.valueOf(REGISTER_EXPRIATIONTIME)))
                .signWith(SignatureAlgorithm.HS512, REGISTER_SECRET)
                .compact();
    }

    @Override
    public Integer decodeRegisterToken(String token) throws ForbiddenException {
        try {
            Jws<Claims> jwtClaim = Jwts.parser().setSigningKey(REGISTER_SECRET).parseClaimsJws(token);
            Date expireTime = jwtClaim.getBody().getExpiration();
            Integer id = Integer.valueOf(jwtClaim.getBody().getSubject());
            if (expireTime.before(new Timestamp(Calendar.getInstance().getTimeInMillis()))) {
                //if expire
                throw new ForbiddenException(InternalStatusCode.EXPIRED);
            }
            return id;
        } catch (ForbiddenException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ForbiddenException(InternalStatusCode.ACCESS_DENY);
        }
    }
}
