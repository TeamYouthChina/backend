package com.youthchina.dto.community.briefreview;

import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewResponseDTO {
    private Integer id;
    private RichTextResponseDTO body;
    private CommentResponseDTO comments = new CommentResponseDTO();
    private UserDTO author;


    public BriefReviewResponseDTO(BriefReview briefReview) {
        this.id = briefReview.getId();
        RichTextResponseDTO richt = new RichTextResponseDTO(briefReview.getBody());
        this.body = richt;
        List<CommentDTO> commentDTOS = new ArrayList<>();
        Iterator it = briefReview.getComments().iterator();
        while (it.hasNext()) {
            Comment comment = (Comment) it.next();
            CommentDTO commentDTO = new CommentDTO(comment);
            comments.getComments().add(commentDTO);
        }

    }

    public BriefReviewResponseDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

    public CommentResponseDTO getComments() {
        return comments;
    }

    public void setComments(CommentResponseDTO comments) {
        this.comments = comments;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }
}
