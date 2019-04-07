package com.youthchina.dto.search;

/**
 * Created by zhongyangwu on 4/7/19.
 */
public enum SearchType {
    ARTICLE("article");


    private final String type;

    SearchType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return this.type;
    }
}
