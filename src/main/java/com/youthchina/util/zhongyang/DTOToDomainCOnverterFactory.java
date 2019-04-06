package com.youthchina.util.zhongyang;

import com.youthchina.dto.RequestDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by zhongyangwu on 3/24/19.
 */
public class DTOToDomainCOnverterFactory implements ConverterFactory<RequestDTO, Object> {
    @Override
    public <T> Converter<RequestDTO, T> getConverter(Class<T> targetType) {
        return null;
    }

    private static class DTOtoDomainConverter implements Converter<RequestDTO, Object> {

        @Override
        public Object convert(RequestDTO source) {
            return source.convertToDomain();
        }
    }


}
