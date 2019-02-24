package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.Work;
import com.youthchina.dto.DurationDTO;
import com.youthchina.dto.LocationDTO;

/**
 * @program: youthchina
 * @description: 工作信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:32
 **/
public class WorkResponseDTO {
    private String employer;
    private String position;
    private DurationDTO duration;
    private String location;
    private String note;

    public WorkResponseDTO() {
    }

    public WorkResponseDTO(Work work) {
        this.employer = work.getWork_company();
        this.position = work.getWork_position();
        this.duration = new DurationDTO(work.getWork_start_time(), work.getWork_end_time());
        this.location=work.getLocation().getRegion_chn();

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
