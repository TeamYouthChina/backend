package com.youthchina.dto;

import com.youthchina.dto.util.PageRequest;
import com.youthchina.dto.util.PageResponse;

/**
 * Created by zhongyangwu on 1/31/19.
 */
public class ListResponse implements HasStatus {
    private PageResponse content;
    private StatusDTO status;

    public ListResponse() {
        this.content = new PageResponse();
        this.status = new StatusDTO(2000, "");
    }


    public ListResponse(PageResponse content) {
        this();
        this.content = content;
    }

    public ListResponse(PageRequest pageRequest, int count, Object data) {
        this();
        this.content = new PageResponse(pageRequest, count, data);
    }

    public ListResponse(PageResponse content, StatusDTO statusDTO) {
        this.content = content;
        this.status = statusDTO;
    }


    public PageResponse getContent() {
        return content;
    }


    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
