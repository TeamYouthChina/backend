package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Project;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.DurationDTO;

/**
 * Created by zhong on 2018/12/30.
 */
public class ProjectRequestDTO implements RequestDTO {
    private String name;
    private String role;
    private DurationDTO duration;
    private String note;

    public ProjectRequestDTO() {
    }

    public ProjectRequestDTO(Project project) {
        this.name = project.getProj_name();
        this.role = project.getProj_role();
        this.duration = new DurationDTO(project.getProj_start_time(), project.getProj_end_time());
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
