package com.youthchina.domain.Qinghong;

import com.youthchina.dto.Applicant.ResumeRequestDTO;
import com.youthchina.dto.Applicant.ResumeResponseDTO;

import java.sql.Timestamp;

/**
 * @program: youthchina
 * @description: 简历json字段
 * @author: Qinghong Wang
 * @create: 2019-02-28 09:37
 **/
public class ResumeJson {
    private Integer resume_id;
    private Integer json_count;
    private String  json_1;
    private String  json_2;
    private String  json_3;
    private String  json_4;
    private String  json_5;
    private String  json_6;
    private String  json_7;
    private String  json_8;
    private String  json_9;
    private String  json_10;

    private Timestamp create_time;

    public ResumeJson() {
    }

    public ResumeJson(ResumeRequestDTO requestDTO) {

    }



    public Integer getResume_id() {
        return resume_id;
    }

    public void setResume_id(Integer resume_id) {
        this.resume_id = resume_id;
    }

    public Integer getJson_count() {
        return json_count;
    }

    public void setJson_count(Integer json_count) {
        this.json_count = json_count;
    }

    public String getJson_1() {
        return json_1;
    }

    public void setJson_1(String json_1) {
        this.json_1 = json_1;
    }

    public String getJson_2() {
        return json_2;
    }

    public void setJson_2(String json_2) {
        this.json_2 = json_2;
    }

    public String getJson_3() {
        return json_3;
    }

    public void setJson_3(String json_3) {
        this.json_3 = json_3;
    }

    public String getJson_4() {
        return json_4;
    }

    public void setJson_4(String json_4) {
        this.json_4 = json_4;
    }

    public String getJson_5() {
        return json_5;
    }

    public void setJson_5(String json_5) {
        this.json_5 = json_5;
    }

    public String getJson_6() {
        return json_6;
    }

    public void setJson_6(String json_6) {
        this.json_6 = json_6;
    }

    public String getJson_7() {
        return json_7;
    }

    public void setJson_7(String json_7) {
        this.json_7 = json_7;
    }

    public String getJson_8() {
        return json_8;
    }

    public void setJson_8(String json_8) {
        this.json_8 = json_8;
    }

    public String getJson_9() {
        return json_9;
    }

    public void setJson_9(String json_9) {
        this.json_9 = json_9;
    }

    public String getJson_10() {
        return json_10;
    }

    public void setJson_10(String json_10) {
        this.json_10 = json_10;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
