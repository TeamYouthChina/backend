package com.youthchina.dto;

/**
 * Created by zhong on 2018/12/30.
 */
public class ApplicantResponseDTO extends ApplicantDTO implements ResponseDTO {
    private StatusDTO status;

    public ApplicantResponseDTO() {
        this.status = new StatusDTO();
    }

    @Override
    public StatusDTO getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusDTO status) {

    }
}
