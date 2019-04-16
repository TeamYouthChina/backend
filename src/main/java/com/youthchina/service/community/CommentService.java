package com.youthchina.service.community;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CommentService extends DomainCRUDService<Comment, Integer> {
    /**
     * get all the comments of a commentable entity
     * @param entity can be Answer, Video, BriefReview, Article
     * @return return a list of Comment
     */
    List<Comment> getComments(Commentable entity);

    List<Comment> getComments(Commentable entity, Integer start, Integer end);

    void isCommentExist(Integer id) throws NotFoundException;

    Comment add(Comment comment, Commentable entity) throws NotFoundException;

    void delete(Commentable commentable);

    Integer countComments(Commentable entity);

}
