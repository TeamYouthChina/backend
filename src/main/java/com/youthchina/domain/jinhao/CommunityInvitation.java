package com.youthchina.domain.jinhao;

import java.sql.Timestamp;

public class CommunityInvitation {
    private Integer id;
    private Timestamp time;
    private Integer targetType;
    private Integer targetId;
    private Integer invitUserId;
    private Integer invitedUserId;
    private Integer accept;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }

    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
    }

    public Integer getInvitUserId() {
        return invitUserId;
    }

    public void setInvitUserId(Integer invitUserId) {
        this.invitUserId = invitUserId;
    }

    public Integer getInvitedUserId() {
        return invitedUserId;
    }

    public void setInvitedUserId(Integer invitedUserId) {
        this.invitedUserId = invitedUserId;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }
}
