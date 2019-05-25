package com.youthchina.config;

import com.youthchina.service.util.OwnerService;
import com.youthchina.util.permission.spel.CustomSecurityExpressionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created by zhongyangwu on 5/24/19.
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    private final
    OwnerService ownerService;

    @Autowired
    public MethodSecurityConfig(OwnerService ownerService) {
        this.ownerService = ownerService;
    }


    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomSecurityExpressionHandler expressionHandler =
                new CustomSecurityExpressionHandler(ownerService);
        return expressionHandler;
    }
}
