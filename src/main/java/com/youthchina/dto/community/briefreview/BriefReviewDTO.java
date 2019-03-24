package com.youthchina.dto.community.briefreview;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.ResponseCommentDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTOResponse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewDTO {
    private Integer id;
    private RichTextDTOResponse body;
    private ResponseCommentDTO comments = new ResponseCommentDTO();
    private UserDTO author;


    public BriefReviewDTO(BriefReview briefReview) {
        this.id = briefReview.getId();
        RichTextDTOResponse richt = new RichTextDTOResponse(briefReview.getRichText());
        this.body = richt;
        List<CommentDTO> commentDTOS = new ArrayList<>();
        Iterator it = briefReview.getComments().iterator();
        while (it.hasNext()) {
            Comment comment = (Comment) it.next();
            CommentDTO commentDTO = new CommentDTO(comment);
            comments.getComments().add(commentDTO);
        }

    }

    public BriefReviewDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
        this.body = body;
    }

    public ResponseCommentDTO getComments() {
        return comments;
    }

    public void setComments(ResponseCommentDTO comments) {
        this.comments = comments;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
