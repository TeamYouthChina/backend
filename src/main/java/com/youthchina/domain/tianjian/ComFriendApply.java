package com.youthchina.domain.tianjian;


import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.friend.FriendApplicationRequestDTO;

import java.sql.Timestamp;

public class ComFriendApply {
    private Integer applyId;
    private Integer userId;
    private Integer friendId;
    private Timestamp friApplyTime;
    private Integer friApplyAccept;
    private Integer friIsRead;
    private User user;
    private String applyMessage;

    public ComFriendApply(FriendApplicationRequestDTO friendApplicationRequestDTO) {
       this.applyMessage = friendApplicationRequestDTO.getMessage();
    }

    public ComFriendApply(){}

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Timestamp getFriApplyTime() {
        return friApplyTime;
    }

    public void setFriApplyTime(Timestamp friApplyTime) {
        this.friApplyTime = friApplyTime;
    }

    public Integer getFriApplyAccept() {
        return friApplyAccept;
    }

    public void setFriApplyAccept(Integer friApplyAccept) {
        this.friApplyAccept = friApplyAccept;
    }

    public Integer getFriIsRead() {
        return friIsRead;
    }

    public void setFriIsRead(Integer friIsRead) {
        this.friIsRead = friIsRead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getApplyMessage() {
        return applyMessage;
    }

    public void setApplyMessage(String applyMessage) {
        this.applyMessage = applyMessage;
    }
}
