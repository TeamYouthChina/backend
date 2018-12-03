package com.youthchina.domain.Qinghong;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Resume {
    private int id;
    private BaseInfo baseInfo;
    private List<EducationInfo> educationInfos;
    private List<Work> works;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("basic_info")
    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    @JsonProperty("education_info")
    public List<EducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(List<EducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    @JsonProperty("work")
    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
