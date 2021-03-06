package com.youthchina.dto;

import com.youthchina.exception.zhongyang.exception.InternalStatusCode;

public class StatusDTO {
    private int code;
    private String reason;

    public StatusDTO() {
        this.code = 2000;
        this.reason = "";
    }

    public StatusDTO(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public StatusDTO(InternalStatusCode internalStatusCode, String reason){
        this.code = internalStatusCode.value();
        this.reason = reason;
    }

    public StatusDTO(InternalStatusCode internalStatusCode) {
        this.code = internalStatusCode.value();
        this.reason = internalStatusCode.getMessage();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
