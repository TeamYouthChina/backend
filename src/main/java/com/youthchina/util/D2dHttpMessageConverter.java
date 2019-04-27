package com.youthchina.util;

import com.youthchina.annotation.ResponseBodyDTO;
import com.youthchina.dto.DTO;
import com.youthchina.dto.Response;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by zhongyangwu on 3/21/19.
 */
public class D2dHttpMessageConverter implements HttpMessageConverter {

    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
    private final DomainToDTOConverterFactory domainToDTOConverterFactory;


    public D2dHttpMessageConverter(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter, DomainToDTOConverterFactory domainToDTOConverterFactory) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;

        this.domainToDTOConverterFactory = domainToDTOConverterFactory;
    }


    @Override
    public boolean canRead(Class clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(DTO.class);
    }

    @Override
    public boolean canWrite(Class clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Arrays.asList(MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_JSON);
    }

    @Override
    public Object read(Class clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object dto = this.mappingJackson2HttpMessageConverter.read(clazz, inputMessage);
        return null;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        writeInternal((Response) o, contentType, outputMessage);
    }


    @SuppressWarnings("unchecked")
    protected void writeInternal(Response response, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        Object object = response.getContent();
        Class targetType = object.getClass().getAnnotation(ResponseBodyDTO.class).value();
        Object dto = this.domainToDTOConverterFactory.getConverter(targetType).convert(object);
        response.setContent(dto);
        mappingJackson2HttpMessageConverter.write(response, contentType, outputMessage);
    }
}
