package com.youthchina.util;

import com.youthchina.domain.zhongyang.JwtAuthentication;
import com.youthchina.domain.zhongyang.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 4/17/19.
 */
@Aspect
@Component
public class MockUserAspect {
    private AuthGenerator authGenerator = new AuthGenerator();


    @Pointcut("@annotation(com.youthchina.util.WithMockUser)")
    private void aspectMethod() {

    }

    @Around(value = "aspectMethod()")
    @SuppressWarnings("unchecked")
    public void setAuthentication(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        WithMockUser annotation = signature.getMethod().getAnnotation(WithMockUser.class);
        Integer userId = annotation.value();
        User user = new User();
        user.setId(userId);
        Authentication authentication = new JwtAuthentication(user);
        authentication.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        pjp.proceed();
        SecurityContextHolder.getContext().setAuthentication(null);
    }
}
