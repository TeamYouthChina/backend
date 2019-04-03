package com.youthchina.domain.jinhao.property;

import com.youthchina.domain.jinhao.Comment;

import java.util.List;

public interface Commentable {
    List<Comment> getComments();
    void setComments(List<Comment> comments);
    Integer getCommentTargetType();
    Integer getId();
}
