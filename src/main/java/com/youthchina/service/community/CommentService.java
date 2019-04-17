package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.util.List;

public interface CommentService{
    /**
     * get all the comments of a commentable entity
     * @param entity can be Answer, Video, BriefReview, Article
     * @return return a list of Comment
     */
    List<Comment> getComments(Commentable entity);

    List<Comment> getComments(Commentable entity, Integer start, Integer end);

    List<Comment> get(List<Integer> ids);

    void isCommentExist(Integer id) throws NotFoundException;

    Comment add(Comment comment, Commentable entity) throws NotFoundException;

    Integer countComments(Commentable entity);

    Comment get(Integer id) throws NotFoundException;

    void delete(Integer id) throws NotFoundException;

}
