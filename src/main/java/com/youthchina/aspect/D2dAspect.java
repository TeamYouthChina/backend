package com.youthchina.aspect;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.dto.ListResponse;
import com.youthchina.dto.Response;
import com.youthchina.util.zhongyang.DomainToDTOConverterFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        ResponseBodyDTO responseBodyDTOAnnotation = signature.getMethod().getAnnotation(ResponseBodyDTO.class);
        ResponseEntity responseEntity = (ResponseEntity) pjp.proceed();
        if (responseEntity.getBody() instanceof Response) {
            Response result = (Response) responseEntity.getBody();
            result.setContent(domainToDTOConverterFactory.getConverter(responseBodyDTOAnnotation.value()).convert(result.getContent()));
            return responseEntity;
        }
        if (responseEntity.getBody() instanceof ListResponse) {
            ListResponse listResponse = (ListResponse) responseEntity.getBody();
            for (Map.Entry<String, Object> entry : listResponse.getContent().entrySet()) {
                if (!entry.getValue().equals("pagination")) {
                    Converter converter = domainToDTOConverterFactory.getConverter(responseBodyDTOAnnotation.value());
                    Collection collection = (Collection) entry.getValue();
                    List result = new ArrayList();
                    for (Object object : collection) {
                        result.add(converter.convert(object));
                    }
                    listResponse.getContent().put(entry.getKey(), result);
                }
            }
        }
        return responseEntity;
    }
}
