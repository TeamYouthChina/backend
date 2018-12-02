package com.youthchina.dto;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchResultDTO implements ResponseDTO {
    private List<String> searchResult;

    @Override
    public StatusDTO getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusDTO status) {

    }
}
