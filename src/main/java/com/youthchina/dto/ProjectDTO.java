package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Project;

/**
 * Created by zhong on 2018/12/30.
 */
public class ProjectDTO {
    private String name;
    private String role;
    private DurationDTO duration;
    private String note;

    public ProjectDTO() {
    }

    public ProjectDTO(Project project) {
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
