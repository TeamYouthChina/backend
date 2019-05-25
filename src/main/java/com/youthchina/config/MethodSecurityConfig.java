package com.youthchina.config;

import com.youthchina.util.permission.spel.CustomSecurityExpressionHandler;
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
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        CustomSecurityExpressionHandler expressionHandler =
                new CustomSecurityExpressionHandler();
        return expressionHandler;
    }
}
