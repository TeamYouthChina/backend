package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

public class Notification {
    private Integer notify_id;
    private String notify_content;
    private Timestamp notify_time;
    private Integer notify_read;
    private Integer stu_id;

    public Integer getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(Integer notify_id) {
        this.notify_id = notify_id;
    }

    public String getNotify_content() {
        return notify_content;
    }

    public void setNotify_content(String notify_content) {
        this.notify_content = notify_content;
    }

    public Timestamp getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(Timestamp notify_time) {
        this.notify_time = notify_time;
    }

    public Integer getNotify_read() {
        return notify_read;
    }

    public void setNotify_read(Integer notify_read) {
        this.notify_read = notify_read;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }
}
