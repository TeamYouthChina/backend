package com.youthchina.dto;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public interface SearchDTO extends RequestDTO {

    int getPage();

    void setPage(Integer i);

    int getSize();

    void setSize(Integer i);

    String getKey();

    void setKey(String key);
}
