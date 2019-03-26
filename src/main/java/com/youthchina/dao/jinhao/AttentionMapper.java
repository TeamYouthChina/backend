package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Attention;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AttentionMapper {
    Attention isEverAttention(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void reFollow(Integer id);
    void follow(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void cancel(Integer id);
    List<Integer> getAllfollows(@Param("type") Integer type, @Param("userId") Integer userId);
}
