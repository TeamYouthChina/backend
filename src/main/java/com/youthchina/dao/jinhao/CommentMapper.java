package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.Comment;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommentMapper {
    List<Comment> getComments(@Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
    Comment get(Integer id);
    void add(Comment comment);
    void delete(Integer id);
    void deleteComments(@Param("type") Integer type, @Param("id") Integer id);
    Integer checkIfCommentExist(Integer id);
    Integer count(@Param("targetType") Integer targetType, @Param("targetId") Integer targetId);
}
