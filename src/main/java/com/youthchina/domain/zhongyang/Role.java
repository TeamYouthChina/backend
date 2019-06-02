package com.youthchina.domain.zhongyang;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by zhongyangwu on 2/20/19.
 */
public enum Role implements GrantedAuthority {
    ROOT(1),
    ADMIN(2),
    APPLICANT(3),
    HR(4),
    EMPLOYER(5);

    int value;

    Role(int value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return this.name();
    }

    public SimpleGrantedAuthority getSimpleGrantedAuthority(){
        return new SimpleGrantedAuthority(this.name());
    }
}
