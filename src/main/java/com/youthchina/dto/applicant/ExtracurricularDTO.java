package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Activity;
import com.youthchina.dto.util.DurationDTO;

/**
 * Created by zhong on 2018/12/30.
 */
public class ExtracurricularDTO {
    private String name;
    private String role;
    private String organization;
    private DurationDTO duration;
    private String note;

    public ExtracurricularDTO() {
    }

    public ExtracurricularDTO(Activity activity) {
        this.name = activity.getAct_name();
        this.role = activity.getAct_role();
        this.organization = activity.getAct_organization();
        this.duration = new DurationDTO(activity.getAct_start_time(), activity.getAct_end_time());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
