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
    private boolean needRenew;

    public JwtAuthentication() {
        this.isAuthenticated = false;
        this.needRenew = false;
    }

    public JwtAuthentication(User user) {
        this();
        this.user = user;
    }

    public JwtAuthentication(User user, boolean isAuthenticated) {
        this.user = user;
        this.isAuthenticated = isAuthenticated;
    }

    public JwtAuthentication(User user, boolean isAuthenticated, boolean needRenew) {
        this.user = user;
        this.isAuthenticated = isAuthenticated;
        this.needRenew = needRenew;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.user == null) {
            return null;
        } else {
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

    public boolean isNeedRenew() {
        return needRenew;
    }

    public void setNeedRenew(boolean needRenew) {
        this.needRenew = needRenew;
    }
}
