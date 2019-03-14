package com.youthchina.dto.job;

import com.youthchina.dto.StatusDTO;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchResultDTO<T extends SimpleJobDTO>  {
    private List<T> searchResult;
    private StatusDTO status;


    public List<T> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<T> searchResult) {
        this.searchResult = searchResult;
    }
}
