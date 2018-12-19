package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class CommentEvaluate {
    private Integer evaluate_id;
    private Integer user_id;
    private Integer evaluate_type;
    private Timestamp evaluate_time;

    public Integer getEvaluate_id() {
        return evaluate_id;
    }

    public void setEvaluate_id(Integer evaluate_id) {
        this.evaluate_id = evaluate_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getEvaluate_type() {
        return evaluate_type;
    }

    public void setEvaluate_type(Integer evaluate_type) {
        this.evaluate_type = evaluate_type;
    }

    public Timestamp getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(Timestamp evaluate_time) {
        this.evaluate_time = evaluate_time;
    }
}
