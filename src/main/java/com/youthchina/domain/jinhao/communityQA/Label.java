package com.youthchina.domain.jinhao.communityQA;

import java.sql.Timestamp;

public class Label {
    private Integer lab_num;
    private String lab_chn;
    private String lab_eng;
    private Integer start_status;
    private Timestamp start_date;

    public Integer getLab_num() {
        return lab_num;
    }

    public void setLab_num(Integer lab_num) {
        this.lab_num = lab_num;
    }

    public String getLab_chn() {
        return lab_chn;
    }

    public void setLab_chn(String lab_chn) {
        this.lab_chn = lab_chn;
    }

    public String getLab_eng() {
        return lab_eng;
    }

    public void setLab_eng(String lab_eng) {
        this.lab_eng = lab_eng;
    }

    public Integer getStart_status() {
        return start_status;
    }

    public void setStart_status(Integer start_status) {
        this.start_status = start_status;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }
}
