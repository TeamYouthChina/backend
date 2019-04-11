package com.youthchina.dto.notification;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;

public class NotificationDTO {
    private Integer id;
    private String content;
    private Timestamp notify_time;
    private boolean is_read;
    private UserDTO user;

    public NotificationDTO() {

    }

    public NotificationDTO(Notification notification) {
        this.id = notification.getId();
        this.content = notification.getContent();
        this.notify_time = notification.getNotifyTime();
        this.is_read = (notification.getIsRead()==1)?true:false;
        this.user = new UserDTO(notification.getUser());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(Timestamp notify_time) {
        this.notify_time = notify_time;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
