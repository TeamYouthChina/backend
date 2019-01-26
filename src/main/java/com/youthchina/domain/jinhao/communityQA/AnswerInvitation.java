package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class AnswerInvitation {
    private Integer invit_id;
    private Timestamp invit_time;
    private Integer invit_accept;
    private Integer invit_ques_id;
    private Integer invit_user_id;

    public Integer getInvit_user_id() {
        return invit_user_id;
    }

    public void setInvit_user_id(Integer invit_user_id) {
        this.invit_user_id = invit_user_id;
    }

    public Integer getInvit_id() {
        return invit_id;
    }

    public void setInvit_id(Integer invit_id) {
        this.invit_id = invit_id;
    }

    public Integer getInvit_accept() {
        return invit_accept;
    }

    public void setInvit_accept(Integer invit_accept) {
        this.invit_accept = invit_accept;
    }

    public Timestamp getInvit_time() {
        return invit_time;
    }

    public void setInvit_time(Timestamp invit_time) {
        this.invit_time = invit_time;
    }

    public Integer getInvit_ques_id() {
        return invit_ques_id;
    }

    public void setInvit_ques_id(Integer invit_ques_id) {
        this.invit_ques_id = invit_ques_id;
    }
}
