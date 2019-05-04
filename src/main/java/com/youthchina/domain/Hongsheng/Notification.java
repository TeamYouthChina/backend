package com.youthchina.domain.Hongsheng;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.notification.NotificationDTO;

import java.sql.Timestamp;
public class Notification {
    private Integer id;
    private String content;
    private Timestamp notifyTime;
    private Integer isRead;
    private User user;

    public Notification() {

    }

    public Notification(NotificationDTO notificationDTO,Integer user_id) {
        this.id = notificationDTO.getNotification_id();
        this.content = notificationDTO.getText();
        this.notifyTime = notificationDTO.getCreate_at();
        this.isRead = (notificationDTO.isIs_read())?1:0;
        User user=new User();
        user.setId(user_id);
        this.user=user;
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

    public Timestamp getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Timestamp notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
