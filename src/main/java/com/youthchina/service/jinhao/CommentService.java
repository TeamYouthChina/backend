package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

public interface CommentService extends DomainCRUDService<Comment, Integer> {
    /**
     * get all the comments of a commentable entity
     * @param entity can be Answer, Video, BriefReview, Article
     * @return return a list of Comment
     */
    void getComments(Commentable entity);

    void isCommentExist(Integer id) throws NotFoundException;

    Comment add(Comment comment, Commentable entity) throws NotFoundException;

    void delete(Commentable commentable);

}
