package com.youthchina.dto.job;

import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.StatusDTO;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchResultDTO<T extends JobRequestDTO> implements ResponseDTO {
    private List<T> searchResult;
    private StatusDTO status;


    public List<T> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<T> searchResult) {
        this.searchResult = searchResult;
    }
}
