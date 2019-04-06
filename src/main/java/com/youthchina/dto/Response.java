package com.youthchina.dto;

/**
 * Created by zhong on 2018/12/30.
 */
public class Response implements HasStatus {
    private Object content;
    private StatusDTO status;

    public Response() {
        this.status = new StatusDTO(2000, "");
    }

    public Response(Object content) {
        this();
        this.content = content;
    }

    public Response(Object content, StatusDTO status) {
        this.content = content;
        this.status = status;
    }

    public Response(Object content, int code, String reason) {
        this.content = content;
        this.status = new StatusDTO(code, reason);
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
