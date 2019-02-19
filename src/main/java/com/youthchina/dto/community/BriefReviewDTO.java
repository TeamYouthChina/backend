package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.zhongyang.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewDTO {
    private Integer id;
    private String body;
    private List<CommentDTO> comments = new ArrayList<CommentDTO>();
    private User author;


    public BriefReviewDTO (BriefReview briefReview){
        this.id = briefReview.getReview_id();
        this.body = briefReview.getReview_content();
        Iterator it = briefReview.getComments().iterator();
        while(it.hasNext()){
            Comment comment = (Comment) it.next();
            CommentDTO commentDTO = new CommentDTO(comment);
            comments.add(commentDTO);
        }
    }

    public BriefReviewDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
