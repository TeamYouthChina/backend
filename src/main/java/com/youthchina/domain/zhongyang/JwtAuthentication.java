package com.youthchina.domain.zhongyang;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by zhongyangwu on 11/11/18.
 */
public class JwtAuthentication implements Authentication {
    private User user;
    private boolean isAuthenticated;

    public JwtAuthentication() {
        this.isAuthenticated = false;
    }

    public JwtAuthentication(User user) {
        this();
        this.user = user;
    }

    public JwtAuthentication(User user, boolean isAuthenticated){
        this.user = user;
        this.isAuthenticated = isAuthenticated;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.user == null){
            return null;
        }
        else{
            return this.user.getAuthorities();
        }
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return this.user;
    }

    @Override
    public Object getPrincipal() {
        return getDetails();
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {
        this.isAuthenticated = b;

    }

    @Override
    public String getName() {
        return null;//todo: determine the name
    }
}
