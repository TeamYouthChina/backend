package com.youthchina.util.permission.spel;

import com.youthchina.service.util.OwnerService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

/**
 * Created by zhongyangwu on 5/24/19.
 */
public class CustomSecurityExpressionHandler
        extends DefaultMethodSecurityExpressionHandler {
    private AuthenticationTrustResolver trustResolver =
            new AuthenticationTrustResolverImpl();
    private final OwnerService ownerService;

    public CustomSecurityExpressionHandler(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
            Authentication authentication, MethodInvocation invocation) {
        CustomSecurityExpressionRoot root =
                new CustomSecurityExpressionRoot(authentication, ownerService);
        root.setPermissionEvaluator(getPermissionEvaluator());
        root.setTrustResolver(this.trustResolver);
        root.setRoleHierarchy(getRoleHierarchy());
        return root;
    }
}