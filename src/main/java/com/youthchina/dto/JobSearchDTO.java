package com.youthchina.dto;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchDTO implements SearchDTO {
    private String industry;
    private List<String> tagList;
    private StatusDTO statusDTO;
    private int page;
    private int size;
    private String key;

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

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public StatusDTO getStatusDTO() {
        return statusDTO;
    }

    public void setStatusDTO(StatusDTO statusDTO) {
        this.statusDTO = statusDTO;
    }

    @Override
    public int getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
