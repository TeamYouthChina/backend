package com.youthchina.dto.notification;

import com.youthchina.domain.Hongsheng.Notification;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.security.UserDTO;

import java.sql.Timestamp;

public class NotificationDTO {
    private Integer notification_id;
    private String text;
    private Timestamp create_at;
    private boolean is_read;


    public NotificationDTO() {

    }

    public NotificationDTO(Notification notification) {
        if(notification!=null){
            this.notification_id = notification.getId();
            this.text = notification.getContent();
            this.create_at = notification.getNotifyTime();
            this.is_read = (notification.getIsRead()==1)?true:false;
        }

    }

    public Integer getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(Integer notification_id) {
        this.notification_id = notification_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public boolean isIs_read() {
        return is_read;
    }

    public void setIs_read(boolean is_read) {
        this.is_read = is_read;
    }
}
