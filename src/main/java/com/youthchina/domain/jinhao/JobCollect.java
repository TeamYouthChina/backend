package com.youthchina.domain.jinhao;

import java.sql.Timestamp;

public class JobCollect {
    private Integer collect_id;
    private Integer job_id;
    private Timestamp job_coll_time;

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public Timestamp getJob_coll_time() {
        return job_coll_time;
    }

    public void setJob_coll_time(Timestamp job_coll_time) {
        this.job_coll_time = job_coll_time;
    }
}
