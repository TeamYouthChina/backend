package com.youthchina.dto.community.video;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.EvaluateService;
import com.youthchina.service.jinhao.EvaluateServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoResponseDTO {
    private Integer id;
    private String url;
    private List<CommentDTO> comments = new ArrayList<CommentDTO>();
    private UserDTO uploader;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;

    public VideoResponseDTO(){}

    @Autowired
    EvaluateServiceImpl evaluateService;
    public VideoResponseDTO(Video video) throws NotFoundException {
        this.id = video.getId();
        if(video.getComments() != null){
            Iterator it = video.getComments().iterator();
            while(it.hasNext()){
                CommentDTO commentDTO = new CommentDTO((Comment)it.next());
                commentDTO.setUpvoteCount(evaluateService.countUpvote(video));
                commentDTO.setDownvoteCount(evaluateService.countDownvote(video));
                this.comments.add(commentDTO);
            }
        }
        this.uploader = new UserDTO(video.getUser());
        this.url = video.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public UserDTO getUploader() {
        return uploader;
    }

    public void setUploader(UserDTO uploader) {
        this.uploader = uploader;
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
}
