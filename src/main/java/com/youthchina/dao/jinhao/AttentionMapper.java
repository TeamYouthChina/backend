package com.youthchina.dao.jinhao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AttentionMapper {
    void follow(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void cancel(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    List<Integer> getAllfollows(@Param("type") Integer type, @Param("userId") Integer userId);
}
