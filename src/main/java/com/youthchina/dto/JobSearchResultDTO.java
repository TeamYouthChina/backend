package com.youthchina.dto;

import com.youthchina.domain.qingyang.Job;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchResultDTO<T extends SimpleJobDTO> implements ResponseDTO {
    private List<T> searchResult;

    @Override
    public StatusDTO getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusDTO status) {

    }

    public List<T> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<T> searchResult) {
        this.searchResult = searchResult;
    }
}
