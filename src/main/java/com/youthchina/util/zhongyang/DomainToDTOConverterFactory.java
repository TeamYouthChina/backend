package com.youthchina.util.zhongyang;

import com.youthchina.dto.ResponseDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zhongyangwu on 3/20/19.
 */
public class DomainToDTOConverterFactory implements ConverterFactory<Object, ResponseDTO> {
    private static class DomainToDTOConverter<T extends ResponseDTO> implements Converter<Object, T> {
        T dto;

        private DomainToDTOConverter(Class<T> targetType) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            dto = targetType.getConstructor().newInstance();
        }

        @Override
        public T convert(Object source) {
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
