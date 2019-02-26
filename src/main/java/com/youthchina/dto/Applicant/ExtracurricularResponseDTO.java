package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.Activity;
import com.youthchina.dto.DurationDTO;

/**
 * @program: youthchina
 * @description: 课外活动信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:38
 **/
public class ExtracurricularResponseDTO {
    private String name;
    private String role;
    private String organization;
    private DurationDTO duration;
    private String note;

    public ExtracurricularResponseDTO() {
    }

    public ExtracurricularResponseDTO(Activity activity) {
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
