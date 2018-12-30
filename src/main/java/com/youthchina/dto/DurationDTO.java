package com.youthchina.dto;

import java.sql.Timestamp;

/**
 * Created by zhong on 2018/12/30.
 */
public class DurationDTO {
    private Timestamp begin;
    private Timestamp end;

    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
