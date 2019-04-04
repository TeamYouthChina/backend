package com.youthchina.domain.zhongyang;

/**
 * Created by zhongyangwu on 4/4/19.
 */
public class PageRequest {

    private int limit;
    private int offset;

    public PageRequest(){
        this.limit = Integer.MAX_VALUE;
        this.offset = 0;
    }

    public PageRequest(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
