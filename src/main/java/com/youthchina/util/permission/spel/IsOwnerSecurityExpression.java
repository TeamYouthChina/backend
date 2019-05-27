package com.youthchina.util.permission.spel;

import com.youthchina.util.permission.HasOwner;

/**
 * Created by zhongyangwu on 5/24/19.
 */
public interface IsOwnerSecurityExpression {
    boolean isOwner(HasOwner hasOwner);

    boolean isOwner(Integer entityId, String entityType);
}
