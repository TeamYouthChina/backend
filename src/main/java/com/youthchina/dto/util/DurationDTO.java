package com.youthchina.dto.util;

import com.youthchina.annotation.JsonTimeStamp;

import java.sql.Date;
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

    public DurationDTO(java.util.Date begin, java.util.Date end) {
        this.begin = new Timestamp(begin.getTime());
        this.end = new Timestamp(end.getTime());
    }

    public DurationDTO(Timestamp begin, Timestamp end) {
        this.begin = begin;
        this.end = end;
    }

    public DurationDTO(String begin, String end) {
        this.begin = new Timestamp(Date.valueOf(begin).getTime());
        this.end = new Timestamp(Date.valueOf(end).getTime());

    }

    public DurationDTO() {

    }

    public DurationDTO(java.sql.Date begin, java.sql.Date end) {
        this.begin = new Timestamp(begin.getTime());
        this.end = new Timestamp(end.getTime());
    }


    @JsonTimeStamp
    public Timestamp getBegin() {
        return begin;
    }

    public void setBegin(Timestamp begin) {
        this.begin = begin;
    }

    @JsonTimeStamp
    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }
}
