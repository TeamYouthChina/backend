package com.youthchina.aspect;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.dto.Response;
import com.youthchina.util.zhongyang.DomainToDTOConverterFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by zhongyangwu on 3/21/19.
 */
@Aspect
public class D2dAspect {
    DomainToDTOConverterFactory domainToDTOConverterFactory;

    public D2dAspect() {
        this.domainToDTOConverterFactory = new DomainToDTOConverterFactory();
    }

    @Pointcut("@annotation(com.youthchina.annotation.ResponseBodyDTO)")
    private void aspectMethod() {

    }

    @Around(value = "aspectMethod()")
    @SuppressWarnings("unchecked")
    public Object afterAdvice(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        ResponseBodyDTO responseBodyDTOAnnotation = signature.getMethod().getAnnotation(ResponseBodyDTO.class);
        Response result = (Response) pjp.proceed();
        result.setContent(domainToDTOConverterFactory.getConverter(responseBodyDTOAnnotation.value()).convert(result.getContent()));
        return result;
    }
}
