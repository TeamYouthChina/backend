package com.youthchina.dto;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public interface ResponseDTO<T> extends DTO {
    void convertToDTO(T domain);
}

