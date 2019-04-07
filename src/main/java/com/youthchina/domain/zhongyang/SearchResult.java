package com.youthchina.domain.zhongyang;

import java.util.List;

/**
 * Created by zhongyangwu on 4/7/19.
 */
public class SearchResult {
    private List<SearchResultItem> result;
    private Integer count;

    public SearchResult(List<SearchResultItem> items, int count) {
        this.result = items;
        this.count = count;
    }

    public SearchResult() {

    }

    public List<SearchResultItem> getResult() {
        return result;
    }

    public void setResult(List<SearchResultItem> result) {
        this.result = result;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
