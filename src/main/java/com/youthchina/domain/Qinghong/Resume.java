package com.youthchina.domain.Qinghong;

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

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public List<EducationInfo> getEducationInfos() {
        return educationInfos;
    }

    public void setEducationInfos(List<EducationInfo> educationInfos) {
        this.educationInfos = educationInfos;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
