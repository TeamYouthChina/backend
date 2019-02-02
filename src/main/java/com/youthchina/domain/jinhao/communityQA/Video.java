package com.youthchina.domain.jinhao.communityQA;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

public class Video {
    private Integer video_id;
    private String video_title;
    private String video_path;
    private Timestamp video_upload_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private User user;
    private List<VideoAttention> videoAttentions;
    private List<VideoComment> videoComments;
    private List<VideoEvaluate> videoEvaluates;
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getVideo_path() {
        return video_path;
    }

    public void setVideo_path(String video_path) {
        this.video_path = video_path;
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
