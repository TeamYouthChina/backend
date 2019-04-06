package com.youthchina.domain.Qinghong;

import com.youthchina.dto.applicant.WorkRequestDTO;

import java.sql.Timestamp;
import java.util.Date;

public class Work {
    private Integer work_id;
    private String work_company;
    private Location location;
    private String work_position;
    private String work_sector;
    private Date work_start_time;
    private Date work_end_time;
    private String work_duty;
    private Integer work_nature;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Work(WorkRequestDTO workRequestDTO) {
        this.work_id=workRequestDTO.getId();
        this.work_company= workRequestDTO.getEmployer();
        this.work_position= workRequestDTO.getPosition();
        this.work_start_time= workRequestDTO.getDuration().getBegin();
        this.work_end_time= workRequestDTO.getDuration().getEnd();
        this.location=new Location(workRequestDTO.getLocation());
        this.work_duty="backend";
        this.work_sector="backend";
        this.work_nature=1;
    }

    public Work() {

    }

    public Integer getWork_id() {
        return work_id;
    }

    public void setWork_id(Integer work_id) {
        this.work_id = work_id;
    }

    public String getWork_company() {
        return work_company;
    }

    public void setWork_company(String work_company) {
        this.work_company = work_company;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getWork_position() {
        return work_position;
    }

    public void setWork_position(String work_position) {
        this.work_position = work_position;
    }

    public String getWork_sector() {
        return work_sector;
    }

    public void setWork_sector(String work_sector) {
        this.work_sector = work_sector;
    }

    public Date getWork_start_time() {
        return work_start_time;
    }

    public void setWork_start_time(Date work_start_time) {
        this.work_start_time = work_start_time;
    }

    public Date getWork_end_time() {
        return work_end_time;
    }

    public void setWork_end_time(Date work_end_time) {
        this.work_end_time = work_end_time;
    }

    public String getWork_duty() {
        return work_duty;
    }

    public void setWork_duty(String work_duty) {
        this.work_duty = work_duty;
    }

    public Integer getWork_nature() {
        return work_nature;
    }

    public void setWork_nature(Integer work_nature) {
        this.work_nature = work_nature;
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
