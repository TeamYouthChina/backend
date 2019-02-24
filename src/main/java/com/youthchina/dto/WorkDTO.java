package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Work;

/**
 * Created by zhong on 2018/12/30.
 */
public class WorkDTO {
    private String employer;
    private String position;
    private DurationDTO duration;
    private LocationDTO location;
    private String note;

    public WorkDTO() {
    }

    public WorkDTO(Work work) {
        this.employer = work.getWork_company();
        this.position = work.getWork_position();
        this.duration = new DurationDTO(work.getWork_start_time(), work.getWork_end_time());
        this.location=new LocationDTO(work.getLocation());

    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
