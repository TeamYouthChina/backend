package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;
import java.util.Date;

public class IntroductionVideo {
    private Integer intro_video_id;
    private String intro_video_path;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Integer getIntro_video_id() {
        return intro_video_id;
    }

    public void setIntro_video_id(Integer intro_video_id) {
        this.intro_video_id = intro_video_id;
    }

    public String getIntro_video_path() {
        return intro_video_path;
    }

    public void setIntro_video_path(String intro_video_path) {
        this.intro_video_path = intro_video_path;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
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
