package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class AnswerAgree {
    private Integer agree_id;
    private String user_id;
    private Timestamp agree_time;
    private Integer agree_cancel;
    private Timestamp agree_cancel_time;

    public Integer getAgree_cancel() {
        return agree_cancel;
    }

    public void setAgree_cancel(Integer agree_cancel) {
        this.agree_cancel = agree_cancel;
    }

    public Timestamp getAgree_cancel_time() {
        return agree_cancel_time;
    }

    public void setAgree_cancel_time(Timestamp agree_cancel_time) {
        this.agree_cancel_time = agree_cancel_time;
    }

    public Integer getAgree_id() {
        return agree_id;
    }

    public void setAgree_id(Integer agree_id) {
        this.agree_id = agree_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Timestamp getAgree_time() {
        return agree_time;
    }

    public void setAgree_time(Timestamp agree_time) {
        this.agree_time = agree_time;
    }
}
