package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.property.Commentable;
import com.youthchina.service.DomainCRUDService;

public interface CommentService extends DomainCRUDService<Comment, Integer> {
    /**
     * get all the comments of a commentable entity
     * @param entity can be Answer, Video, BriefReview, Article
     * @return return a list of Comment
     */
    void getComments(Commentable entity);


}
