package com.youthchina.util.permission.spel;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.util.OwnerService;
import com.youthchina.util.permission.HasOwner;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * Created by zhongyangwu on 5/24/19.
 */
public class CustomSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations, IsOwnerSecurityExpression {

    private Object filterObject;
    private Object returnObject;
    private Object target;
    private final OwnerService ownerService;

    CustomSecurityExpressionRoot(Authentication a, OwnerService ownerService) {
        super(a);
        this.ownerService = ownerService;
    }

    public void setFilterObject(Object filterObject) {
        this.filterObject = filterObject;
    }

    public Object getFilterObject() {
        return filterObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    /**
     * Sets the "this" property for use in expressions. Typically this will be the "this"
     * property of the {@code JoinPoint} representing the method invocation which is being
     * protected.
     *
     * @param target the target object on which the method in is being invoked.
     */
    void setThis(Object target) {
        this.target = target;
    }

    public Object getThis() {
        return target;
    }


    @Override
    public boolean isOwner(HasOwner hasOwner) {
        User user = ((User) this.getPrincipal());
        return user.equals(hasOwner.getOwner());
    }

    @Override
    public boolean isOwner(Integer entityId, String entityType) {
        return this.isOwner(this.ownerService.getEntityByOwnerId(entityId, entityType));
    }
}
