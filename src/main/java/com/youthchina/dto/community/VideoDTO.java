package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.jinhao.communityQA.VideoComment;
import com.youthchina.domain.zhongyang.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoDTO {
    private Integer id;
    private String url;
    private List<CommentDTO> comments = new ArrayList<>();
    private User uploader;

    public VideoDTO (){}

    public VideoDTO (Video video){
        this.id = video.getVideo_id();
        this.url = video.getVideo_name();
        Iterator it = video.getVideoComments().iterator();
        while(it.hasNext()){
            CommentDTO commentDTO = new CommentDTO((VideoComment)it.next());
            System.out.println("commentid:"+commentDTO.getId());
            this.comments.add(commentDTO);
        }
        this.uploader = video.getUser();
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

    public User getUploader() {
        return uploader;
    }

    public void setUploader(User uploader) {
        this.uploader = uploader;
    }
}
