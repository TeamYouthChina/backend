package com.youthchina.domain.zhongyang;

/**
 * Created by zhongyangwu on 4/7/19.
 */
public class SearchResultItem {
    private String type;
    private Object domain;

    public SearchResultItem(Object comEssay, String type) {
        this.type = type;
        this.domain = comEssay;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDomain() {
        return domain;
    }

    public void setDomain(Object domain) {
        this.domain = domain;
    }
}
