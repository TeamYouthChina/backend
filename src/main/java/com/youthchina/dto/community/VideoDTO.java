package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.jinhao.communityQA.VideoComment;
import com.youthchina.dto.UserDTO;

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
        Iterator it = video.getVideoComments().iterator();
        while(it.hasNext()){
            CommentDTO commentDTO = new CommentDTO((VideoComment)it.next());
            this.comments.add(commentDTO);
        }
        this.uploader = new UserDTO(video.getUser());
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
