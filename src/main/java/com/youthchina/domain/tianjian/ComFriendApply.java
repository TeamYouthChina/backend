package com.youthchina.domain.tianjian;


import java.sql.Timestamp;

public class ComFriendApply {
    private Integer applyId;
    private Integer userId;
    private Integer friendId;
    private Timestamp friApplyTime;
    private Integer friApplyAccept;

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
}
