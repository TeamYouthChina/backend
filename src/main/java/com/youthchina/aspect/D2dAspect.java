package com.youthchina.aspect;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.dto.Response;
import com.youthchina.util.zhongyang.DomainToDTOConverterFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created by zhongyangwu on 3/21/19.
 */
@Aspect
@Component
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
        System.out.println("AAAAAAAAAAAA");
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        ResponseBodyDTO responseBodyDTOAnnotation = signature.getMethod().getAnnotation(ResponseBodyDTO.class);
        ResponseEntity responseEntity = (ResponseEntity) pjp.proceed();
        Response result = (Response) responseEntity.getBody();
        //todo: add support for ListResponse
        result.setContent(domainToDTOConverterFactory.getConverter(responseBodyDTOAnnotation.value()).convert(result.getContent()));
        return responseEntity;
    }
}
