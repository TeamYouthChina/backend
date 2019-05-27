package com.youthchina.util.permission;

import com.youthchina.domain.zhongyang.User;

/**
 * Created by zhongyangwu on 5/24/19.
 */
public interface HasOwner {
    default User getOwner() {
        User user = new User();
        user.setId(this.getOwnerId());
        return user;
    }

    Integer getOwnerId();

}
