package com.youthchina.util.permission.policy;

import com.youthchina.domain.zhongyang.User;

/**
 * Created by zhongyangwu on 5/24/19.
 */
public interface PermissionPolicy {
    /**
     * @param user current logged user
     * @param domain domain object
     * @return true if user has permission
     */
    boolean hasPermission(User user, Object domain);
}
