package com.youthchina.dto;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public interface RequestDTO<T> extends DTO{
    T convertToDomain();
}
