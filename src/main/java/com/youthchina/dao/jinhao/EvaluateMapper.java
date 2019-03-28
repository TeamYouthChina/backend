package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Evaluate;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface EvaluateMapper {
    Evaluate isEverEvaluate(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void reUpvote(Integer id);
    void reDownVote(Integer id);
    void upvote(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void downvote(@Param("type") Integer type, @Param("targetId") Integer targetId, @Param("userId") Integer userId);
    void cancel(Integer id);
    Integer countUpvote(@Param("type") Integer type, @Param("targetId") Integer targetId);
}
