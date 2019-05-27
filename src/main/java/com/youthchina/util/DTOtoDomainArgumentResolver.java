package com.youthchina.util;

import com.youthchina.annotation.RequestBodyDTO;
import com.youthchina.dto.RequestDTO;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.Collection;
import java.util.List;

/**
 * Created by zhongyangwu on 3/24/19.
 */
public class DTOtoDomainArgumentResolver extends RequestResponseBodyMethodProcessor {

    public DTOtoDomainArgumentResolver(List<HttpMessageConverter<?>> converters) {
        super(converters);
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestBodyDTO.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        RequestBodyDTO requestBodyDTO = parameter.getParameterAnnotation(RequestBodyDTO.class);
        Class dtoClass = requestBodyDTO.value();
        HttpInputMessage inputMessage = createInputMessage(webRequest);
        Assert.state(RequestDTO.class.isAssignableFrom(dtoClass), "Class in RequestBodyDTO must be instance of RequestDTO interface");


        Class parameterType = parameter.getParameter().getType();
        if (parameterType.isInstance(Collection.class)) {
            Class memberClass = parameter.getMember().getDeclaringClass();
            //if we have multiple input DTO class, like List, Array, etc.
//            Object dto = this.readWithMessageConverters(inputMessage, parameter, dtoClass);
            throw new HttpMessageNotReadableException("Please do not use @ResponseBodyDTO on collections", inputMessage);
        } else {
            RequestDTO dto = (RequestDTO) this.readWithMessageConverters(inputMessage, parameter, dtoClass);
            if (dto == null && checkRequired(parameter)) {
                throw new HttpMessageNotReadableException("Required request body is missing: " +
                        parameter.getExecutable().toGenericString(), inputMessage);
            } else {
                WebDataBinder binder = binderFactory.createBinder(webRequest, dto, dto.getClass().getSimpleName());
                validateIfApplicable(binder, parameter);
                if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(binder, parameter)) {
                    throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
                }
            }
            return dto.convertToDomain();
        }
    }

    @Override
    public boolean checkRequired(MethodParameter parameter) {
        RequestBodyDTO requestBodyDTO = parameter.getParameterAnnotation(RequestBodyDTO.class);
        return (requestBodyDTO != null && requestBodyDTO.required() && !parameter.isOptional());
    }
}
