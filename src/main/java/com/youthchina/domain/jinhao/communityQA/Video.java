package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class Video {
    private Integer video_id;
    private String video_title;
    private String video_name;
    private Timestamp video_upload_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User user;
    private List<VideoAttention> videoAttentions;
    private List<VideoComment> videoComments;
    private List<VideoEvaluate> videoEvaluates;
    private Integer rela_type;
    private Integer rela_id;

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VideoAttention> getVideoAttentions() {
        return videoAttentions;
    }

    public void setVideoAttentions(List<VideoAttention> videoAttentions) {
        this.videoAttentions = videoAttentions;
    }

    public List<VideoComment> getVideoComments() {
        return videoComments;
    }

    public void setVideoComments(List<VideoComment> videoComments) {
        this.videoComments = videoComments;
    }

    public List<VideoEvaluate> getVideoEvaluates() {
        return videoEvaluates;
    }

    public void setVideoEvaluates(List<VideoEvaluate> videoEvaluates) {
        this.videoEvaluates = videoEvaluates;
    }

    public Integer getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Integer video_id) {
        this.video_id = video_id;
    }

    public String getVideo_title() {
        return video_title;
    }

    public void setVideo_title(String video_title) {
        this.video_title = video_title;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public Timestamp getVideo_upload_time() {
        return video_upload_time;
    }

    public void setVideo_upload_time(Timestamp video_upload_time) {
        this.video_upload_time = video_upload_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
