package com.youthchina.domain.jinhao.recommend;

import java.sql.Timestamp;

public class JobRecommendation {
    private Integer job_id;
    private String job_name;
    private String job_description;
    private String job_location;
    private String job_highlight;
    private Timestamp data_time;

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_location() {
        return job_location;
    }

    public void setJob_location(String job_location) {
        this.job_location = job_location;
    }

    public String getJob_highlight() {
        return job_highlight;
    }

    public void setJob_highlight(String job_highlight) {
        this.job_highlight = job_highlight;
    }

    public Timestamp getData_time() {
        return data_time;
    }

    public void setData_time(Timestamp data_time) {
        this.data_time = data_time;
    }
}
