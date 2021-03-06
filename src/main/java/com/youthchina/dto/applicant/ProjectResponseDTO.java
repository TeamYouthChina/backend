package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Project;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.util.DurationDTO;

/**
 * @program: youthchina
 * @description: 项目信息
 * @author: Qinghong Wang
 * @create: 2019-02-24 15:37
 **/
public class ProjectResponseDTO implements ResponseDTO<Project> {
    private Integer id;
    private String name;
    private String role;
    private DurationDTO duration;
    private String note;

    public ProjectResponseDTO() {
    }

    public ProjectResponseDTO(Project project) {
        if(project!=null){
            this.id = project.getProj_id();
            this.name = project.getProj_name();
            this.role = project.getProj_role();
            this.duration = new DurationDTO(project.getProj_start_time(), project.getProj_end_time());
        }




    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public void convertToDTO(Project project) {
        this.id = project.getProj_id();
        this.name = project.getProj_name();
        this.role = project.getProj_role();
        this.duration = new DurationDTO(project.getProj_start_time(), project.getProj_end_time());
    }
}
