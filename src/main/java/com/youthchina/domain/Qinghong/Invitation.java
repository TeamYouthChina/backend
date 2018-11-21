package com.youthchina.domain.Qinghong;

public class Invitation {
    private int invite_id;
    private int invite_time;
    private int invite_user_id;
    private InviationMapper inviationMapper;

    public int getInvite_id() {
        return invite_id;
    }

    public void setInvite_id(int invite_id) {
        this.invite_id = invite_id;
    }

    public int getInvite_time() {
        return invite_time;
    }

    public void setInvite_time(int invite_time) {
        this.invite_time = invite_time;
    }

    public int getInvite_user_id() {
        return invite_user_id;
    }

    public void setInvite_user_id(int invite_user_id) {
        this.invite_user_id = invite_user_id;
    }

    public InviationMapper getInviationMapper() {
        return inviationMapper;
    }

    public void setInviationMapper(InviationMapper inviationMapper) {
        this.inviationMapper = inviationMapper;
    }
}
