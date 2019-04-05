package com.youthchina.domain.Qinghong;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 学位表
 * @author: Qinghong Wang
 * @create: 2019-03-28 10:10
 **/
public class Diploma {
    private Integer diploma_num;
    private String diploma_chn;
    private String diploma_eng;
    private String diploma_eng_abbre;
    private Timestamp start_date;

    public Integer getDiploma_num() {
        return diploma_num;
    }

    public void setDiploma_num(Integer diploma_num) {
        this.diploma_num = diploma_num;
    }

    public String getDiploma_chn() {
        return diploma_chn;
    }

    public void setDiploma_chn(String diploma_chn) {
        this.diploma_chn = diploma_chn;
    }

    public String getDiploma_eng() {
        return diploma_eng;
    }

    public void setDiploma_eng(String diploma_eng) {
        this.diploma_eng = diploma_eng;
    }

    public String getDiploma_eng_abbre() {
        return diploma_eng_abbre;
    }

    public void setDiploma_eng_abbre(String diploma_eng_abbre) {
        this.diploma_eng_abbre = diploma_eng_abbre;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }
}
