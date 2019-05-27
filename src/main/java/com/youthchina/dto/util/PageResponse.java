package com.youthchina.dto.util;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhongyangwu on 4/4/19.
 */
public class PageResponse {
    private int offset;
    private int limit;
    private int pageCount;
    private int itemCount;
    private int pageIndex;
    private boolean isFirst;
    private boolean isLast;
    private Object data;

    public PageResponse() {

    }

    public PageResponse(int start, int end, int count, int limit) {
        this.pageCount = count / limit;
        this.itemCount = count;
        this.isFirst = start == 0;
        this.isLast = end == count - 1;
        this.pageIndex = start % limit;
        this.limit = limit;
        this.offset = start;
    }

    public PageResponse(PageRequest pageRequest, int itemCount) {
        this(pageRequest.getStart(), pageRequest.getEnd(), itemCount, pageRequest.getLimit() == 0 ? 1 : pageRequest.getLimit());
    }

    public PageResponse(PageRequest pageRequest, int itemCount, Object data){
        this(pageRequest, itemCount);
        this.data = data;
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

    @JsonProperty("page_count")
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @JsonProperty("item_count")
    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @JsonProperty("page_index")
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @JsonProperty("is_first")
    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    @JsonProperty("is_last")
    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
