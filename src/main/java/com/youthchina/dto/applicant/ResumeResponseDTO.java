package com.youthchina.dto.applicant;

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
        switch (resumeJson.getJson_count()){
            case 10 : list.add(resumeJson.getJson_10());
            case 9 : list.add(0,resumeJson.getJson_9());
            case 8 : list.add(0,resumeJson.getJson_8());
            case 7 : list.add(0,resumeJson.getJson_7());
            case 6 : list.add(0,resumeJson.getJson_6());
            case 5 : list.add(0,resumeJson.getJson_5());
            case 4 : list.add(0,resumeJson.getJson_4());
            case 3 : list.add(0,resumeJson.getJson_3());
            case 2 : list.add(0,resumeJson.getJson_2());
            case 1 : list.add(0,resumeJson.getJson_1());
        }
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
