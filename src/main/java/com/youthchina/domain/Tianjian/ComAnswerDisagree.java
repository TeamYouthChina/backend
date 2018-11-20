package com.youthchina.domain.Tianjian;

import java.sql.Timestamp;

public class ComAnswerDisagree {
    private Integer disagree_id;
    private Integer user_id;
    private Timestamp disagree_time;
    private Integer disagree_cancel;
    private Timestamp disagree_cancel_time;

    public Integer getDisagree_id() {
        return disagree_id;
    }

    public void setDisagree_id(Integer disagree_id) {
        this.disagree_id = disagree_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDisagree_time() {
        return disagree_time;
    }

    public void setDisagree_time(Timestamp disagree_time) {
        this.disagree_time = disagree_time;
    }

    public Integer getDisagree_cancel() {
        return disagree_cancel;
    }

    public void setDisagree_cancel(Integer disagree_cancel) {
        this.disagree_cancel = disagree_cancel;
    }

    public Timestamp getDisagree_cancel_time() {
        return disagree_cancel_time;
    }

    public void setDisagree_cancel_time(Timestamp disagree_cancel_time) {
        this.disagree_cancel_time = disagree_cancel_time;
    }
}
