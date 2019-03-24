package com.youthchina.domain.zhongyang;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by zhongyangwu on 2/20/19.
 */
public enum Role implements GrantedAuthority {
    ROOT(0),
    ADMIN(1),
    APPLICANT(2),
    HR(3),
    EMPLOYER(4);

    int value;

    Role(int value) {
        this.value = value;
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
