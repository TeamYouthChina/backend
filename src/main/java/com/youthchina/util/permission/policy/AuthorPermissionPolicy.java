package com.youthchina.util.permission.policy;

import com.youthchina.domain.jinhao.property.HasAuthor;
import com.youthchina.domain.zhongyang.User;

import javax.annotation.Nonnull;

/**
 * Created by zhongyangwu on 5/19/19.
 * <p>
 * Permission:
 * <p>
 * If the user is the author of the resource, then return true.
 * <p>
 * If resource don't have an author, return false
 */
public class AuthorPermissionPolicy implements PermissionPolicy {

    @Override
    public boolean hasPermission(@Nonnull User user, @Nonnull Object domain) {
        if (!(domain instanceof HasAuthor)) {
            return false;
        } else {
            HasAuthor hasAuthor = (HasAuthor) domain;
            return hasAuthor.getAuthor() != null && (hasAuthor.getAuthor().getId() != null && hasAuthor.getAuthor().getId().equals(user.getId()));
        }
    }
}
