package com.youthchina.domain.Qinghong;

public class Invitation {
    private Integer invite_id;
    private Integer invite_time;
    private Integer invite_user_id;
    private InviationMapper inviationMapper;

    public Integer getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(Integer invite_id) {
        this.invite_id = invite_id;
    }

    public Integer getInvite_time() {
        return invite_time;
    }

    public void setInvite_time(Integer invite_time) {
        this.invite_time = invite_time;
    }

    public Integer getInvite_user_id() {
        return invite_user_id;
    }

    public void setInvite_user_id(Integer invite_user_id) {
        this.invite_user_id = invite_user_id;
    }

    public InviationMapper getInviationMapper() {
        return inviationMapper;
    }

    public void setInviationMapper(InviationMapper inviationMapper) {
        this.inviationMapper = inviationMapper;
    }
}
