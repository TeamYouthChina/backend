package com.youthchina.dto.community.briefreview;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.StatusDTO;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewResponseDTO implements ResponseDTO {
    private Integer id;
    private RichTextDTO body;
    private CommentResponseDTO comments = new CommentResponseDTO();
    private UserDTO author;


    public BriefReviewResponseDTO(BriefReview briefReview){
        this.id = briefReview.getReview_id();
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(briefReview.getReview_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }

        List<CommentDTO> commentDTOS = new ArrayList<>();
        Iterator it = briefReview.getComments().iterator();
        while(it.hasNext()){
            Comment comment = (Comment) it.next();
            CommentDTO commentDTO = new CommentDTO(comment);
            comments.getComments().add(commentDTO);
        }

    }

    public BriefReviewResponseDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RichTextDTO getBody() {
        return body;
    }

    public void setBody(RichTextDTO body) {
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

    @Override
    public StatusDTO getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusDTO status) {

    }
}
