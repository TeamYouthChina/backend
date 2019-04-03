package com.youthchina.util.zhongyang;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.dto.RequestDTO;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by zhongyangwu on 3/24/19.
 */
public class DTOtoDomainArgumentResolver implements HandlerMethodArgumentResolver {
    private final ObjectMapper objectMapper;

    public DTOtoDomainArgumentResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(RequestBodyDTO.class) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestBodyDTO requestBodyDTO = parameter.getParameterAnnotation(RequestBodyDTO.class);
        Class dtoClass = requestBodyDTO.value();
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        JavaType targetClass = null;


        Class parameterType = parameter.getParameter().getType();
        if (parameterType.isInstance(Collection.class)) {
            Class memberClass = parameter.getMember().getDeclaringClass();
            //if we have multiple input DTO class, like List, Array, etc.
            targetClass = this.objectMapper.getTypeFactory().constructCollectionType(parameterType, memberClass);
        } else {
            targetClass = this.objectMapper.getTypeFactory().constructType(dtoClass);
        }


//        if (requestBodyDTO.value().equals(parameter.getParameterType())) {
        //if match
        RequestDTO dto = (RequestDTO) this.objectMapper.readValue(servletRequest.getReader(), targetClass);
        if (dto == null && checkRequired(parameter)) {
            throw new HttpMessageNotReadableException("Required request body is missing: " +
                    parameter.getExecutable().toGenericString(), inputMessage);

        }
        return dto.convertToDomain();
//        }
    }

    private boolean checkRequired(MethodParameter parameter) {
        RequestBodyDTO requestBodyDTO = parameter.getParameterAnnotation(RequestBodyDTO.class);
        return (requestBodyDTO != null && requestBodyDTO.required() && !parameter.isOptional());
    }
}
