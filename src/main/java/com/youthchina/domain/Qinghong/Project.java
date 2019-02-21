package com.youthchina.domain.Qinghong;

import com.youthchina.dto.ProjectDTO;

import java.sql.Timestamp;
import java.util.Date;

public class Project {
    private Integer proj_id;
    private String proj_name;
    private String proj_role;
    private Date proj_start_time;
    private Date proj_end_time;
    private String proj_deliver;
    private Integer deliver_publish;
    private String deliver_pub_insti;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Project(ProjectDTO projectDTO) {
        this.proj_name=projectDTO.getName();
        this.proj_role=projectDTO.getRole();
        this.proj_start_time=projectDTO.getDuration().getBegin();
        this.proj_end_time=projectDTO.getDuration().getEnd();
    }

    public Project() {

    }

    public Integer getProj_id() {
        return proj_id;
    }

    public void setProj_id(Integer proj_id) {
        this.proj_id = proj_id;
    }

    public String getProj_name() {
        return proj_name;
    }

    public void setProj_name(String proj_name) {
        this.proj_name = proj_name;
    }

    public String getProj_role() {
        return proj_role;
    }

    public void setProj_role(String proj_role) {
        this.proj_role = proj_role;
    }

    public Date getProj_start_time() {
        return proj_start_time;
    }

    public void setProj_start_time(Date proj_start_time) {
        this.proj_start_time = proj_start_time;
    }

    public Date getProj_end_time() {
        return proj_end_time;
    }

    public void setProj_end_time(Date proj_end_time) {
        this.proj_end_time = proj_end_time;
    }

    public String getProj_deliver() {
        return proj_deliver;
    }

    public void setProj_deliver(String proj_deliver) {
        this.proj_deliver = proj_deliver;
    }

    public Integer getDeliver_publish() {
        return deliver_publish;
    }

    public void setDeliver_publish(Integer deliver_publish) {
        this.deliver_publish = deliver_publish;
    }

    public String getDeliver_pub_insti() {
        return deliver_pub_insti;
    }

    public void setDeliver_pub_insti(String deliver_pub_insti) {
        this.deliver_pub_insti = deliver_pub_insti;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
