package com.youthchina.dto.community.briefreview;

import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.comment.CommentResponseDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BriefReviewResponseDTO implements ResponseDTO<BriefReview> {
    private Integer id;
    private RichTextResponseDTO body;
    private List<CommentResponseDTO> comments = new ArrayList<>();
    private UserDTO author;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;
    private Timestamp modified_at;


    public BriefReviewResponseDTO(BriefReview briefReview) {
        this.id = briefReview.getId();
        RichTextResponseDTO richt = new RichTextResponseDTO(briefReview.getBody());
        this.body = richt;
        if(briefReview.getComments()!=null){
            Iterator it = briefReview.getComments().iterator();
            while (it.hasNext()) {
                Comment comment = (Comment) it.next();
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
                comments.add(commentResponseDTO);
            }
        }
        this.author = new UserDTO(briefReview.getUser());
        this.modified_at = briefReview.getTime();
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

    public List<CommentResponseDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponseDTO> comments) {
        this.comments = comments;
    }

    @JsonTimeStamp
    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }

    public UserDTO getAuthor() {
        return author;
    }

    public void setAuthor(UserDTO author) {
        this.author = author;
    }

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(Integer downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    @Override
    public void convertToDTO(BriefReview briefReview) {
        this.id = briefReview.getId();
        RichTextResponseDTO richt = new RichTextResponseDTO(briefReview.getBody());
        this.body = richt;
        if(briefReview.getComments()!=null){
            Iterator it = briefReview.getComments().iterator();
            while (it.hasNext()) {
                Comment comment = (Comment) it.next();
                CommentResponseDTO commentResponseDTO = new CommentResponseDTO(comment);
                comments.add(commentResponseDTO);
            }
        }

        this.author = new UserDTO(briefReview.getUser());
    }
}
