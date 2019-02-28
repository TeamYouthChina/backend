package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.ResumeJson;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 简历json返回
 * @author: Qinghong Wang
 * @create: 2019-02-28 10:04
 **/
public class ResumeResponseDTO {
    private Integer id;
    private List<String> jsons;

    public ResumeResponseDTO() {

    }

    public ResumeResponseDTO(ResumeJson resumeJson) {
        List<String> list=new ArrayList<>();
        if(resumeJson.getJson_1() != null) list.add(resumeJson.getJson_1());
        if(resumeJson.getJson_2() != null) list.add(resumeJson.getJson_2());
        if(resumeJson.getJson_3() != null) list.add(resumeJson.getJson_3());
        if(resumeJson.getJson_4() != null) list.add(resumeJson.getJson_4());
        if(resumeJson.getJson_5() != null) list.add(resumeJson.getJson_5());
        if(resumeJson.getJson_6() != null) list.add(resumeJson.getJson_6());
        if(resumeJson.getJson_7() != null) list.add(resumeJson.getJson_7());
        if(resumeJson.getJson_8() != null) list.add(resumeJson.getJson_8());
        if(resumeJson.getJson_9() != null) list.add(resumeJson.getJson_9());
        if(resumeJson.getJson_10() != null) list.add(resumeJson.getJson_10());
        this.id = resumeJson.getResume_id();
        this.jsons = list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<String> getJsons() {
        return jsons;
    }

    public void setJsons(List<String> jsons) {
        this.jsons = jsons;
    }
}
