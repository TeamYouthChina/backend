package com.youthchina.dto;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyangwu on 1/31/19.
 */
public class ListResponse implements HasStatus {
    private Map<String, Object> content;
    private StatusDTO status;

    public ListResponse() {
        this.content = new HashMap<>();
        this.status = new StatusDTO(2000, "");
    }

    public ListResponse(Object content, String key) {
        this();
        this.content.put(key, content);
    }

    public ListResponse(Object content, String key, StatusDTO status) {
        this.content.put(key,
                content);
        this.status = status;
    }

    public ListResponse(Object content, String key, int code, String reason) {
        this.content.put(key, content);
        this.status = new StatusDTO(code, reason);
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Object content, String key) {
        this.content = new HashMap<>();
        this.content.put(key, content);
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }
}
