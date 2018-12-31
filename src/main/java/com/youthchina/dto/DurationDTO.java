package com.youthchina.dto;

import java.sql.Timestamp;

/**
 * Created by zhong on 2018/12/30.
 */
public class DurationDTO {
    private Timestamp begin;
    private Timestamp end;

    public DurationDTO(long begin, long end) {
        this.begin = new Timestamp(begin);
        this.end = new Timestamp(end);
    }

    public DurationDTO(Timestamp begin, Timestamp end) {
        this.begin = begin;
        this.end = end;
    }

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
