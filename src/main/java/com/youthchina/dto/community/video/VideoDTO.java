package com.youthchina.dto.community.video;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.dto.community.comment.CommentDTO;
import com.youthchina.dto.security.UserDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VideoDTO {
    private Integer id;
    private String url;
    private List<CommentDTO> comments = new ArrayList<CommentDTO>();
    private UserDTO uploader;

    public VideoDTO (){}

    public VideoDTO (Video video){
        this.id = video.getVideo_id();
        if(video.getComments() != null){
            Iterator it = video.getComments().iterator();
            while(it.hasNext()){
                CommentDTO commentDTO = new CommentDTO((Comment)it.next());
                this.comments.add(commentDTO);
            }
        }
        this.uploader = new UserDTO(video.getUser());
        this.url = video.getVideo_name();
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
}
