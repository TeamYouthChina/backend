package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;
import com.youthchina.dto.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewDTO {
    private Integer id;
    private RichTextDTO body;
    private ResponseCommentDTO comments = new ResponseCommentDTO();
    private UserDTO author;


    public BriefReviewDTO (BriefReview briefReview){
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

    public BriefReviewDTO(){}

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
