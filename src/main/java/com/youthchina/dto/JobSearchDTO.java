package com.youthchina.dto;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchDTO extends SearchDTO{
    private String industry;
    private List<String> tagList;
    private StatusDTO statusDTO;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
