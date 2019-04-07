package com.youthchina.dto.search;

import com.youthchina.dto.ResponseDTO;

/**
 * Created by zhongyangwu on 4/7/19.
 */
public class SearchResponseDTO {

    private ResponseDTO content;
    private String type;

    public SearchResponseDTO(ResponseDTO dto, String type) {
        this.content = dto;
        this.type = type;
    }

    public ResponseDTO getContent() {
        return content;
    }

    public void setContent(ResponseDTO content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
