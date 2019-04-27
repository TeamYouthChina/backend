package com.youthchina.util;

import com.youthchina.dto.ResponseDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhongyangwu on 3/20/19.
 */
public class DomainToDTOConverterFactory implements ConverterFactory<Object, ResponseDTO> {
    private static class DomainToDTOConverter<T extends ResponseDTO> implements Converter<Object, T> {
        Class<T> targetType;

        private DomainToDTOConverter(Class<T> targetType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            this.targetType = targetType;
        }

        @Override
        public T convert(Object source) {
            T dto = null;
            try {
                dto = this.targetType.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            dto.convertToDTO(source);
            return dto;
        }
    }

    @Override
    public <T extends ResponseDTO> Converter<Object, T> getConverter(Class<T> targetType) {
        Converter<Object, T> converter = null;
        try {
            converter = new DomainToDTOConverter<>(targetType);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return converter;
    }
}
