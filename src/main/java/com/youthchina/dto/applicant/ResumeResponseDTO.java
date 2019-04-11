package com.youthchina.dto.applicant;

import com.youthchina.domain.qingyang.ResumeJson;
import com.youthchina.dto.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 简历json返回
 * @author: Qinghong Wang
 * @create: 2019-02-28 10:04
 **/
public class ResumeResponseDTO implements ResponseDTO<ResumeJson> {
    private Integer id;
    private List<String> jsons;

    public ResumeResponseDTO() {

    }

    public ResumeResponseDTO(ResumeJson resumeJson) {
        List<String> list = new ArrayList<>();
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


    @Override
    public void convertToDTO(ResumeJson domain) {

    }
}
