package com.youthchina.domain.Qinghong;

import java.util.Date;

public class IntroductionVideo {
    private int intro_video_id;
    private String intro_video_path;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;

    public int getIntro_video_id() {
        return intro_video_id;
    }

    public void setIntro_video_id(int intro_video_id) {
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

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Date is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
