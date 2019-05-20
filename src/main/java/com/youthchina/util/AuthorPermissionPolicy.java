package com.youthchina.util;

import com.youthchina.domain.jinhao.property.HasAuthor;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.community.EssayService;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by zhongyangwu on 5/19/19.
 *
 * Permission:
 *
 * If the user is the author of the resource, then return true.
 *
 * If resource don't have an author, return false
 */
public class AuthorPermissionPolicy implements PermissionEvaluator {
    final EssayService essayService;

    public AuthorPermissionPolicy(EssayService essayService) {
        this.essayService = essayService;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (!(targetDomainObject instanceof HasAuthor)) {
            return false;
        }
        HasAuthor hasAuthor = (HasAuthor) targetDomainObject;
        return hasAuthor.getAuthor().getId().equals(((User) authentication.getPrincipal()).getId());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if (targetId instanceof Integer) {
            if (Objects.equals(targetType, ComEssay.class.getSimpleName())) {
                //if target type is essay
                try {
                    ComEssay essay = essayService.get((Integer) targetId);
                    return this.hasPermission(authentication, essay, permission);
                } catch (NotFoundException e) {
                    return false;
                }
            }
            //todo: add more HasAuthor resource
        }
        return false;

    }
}
