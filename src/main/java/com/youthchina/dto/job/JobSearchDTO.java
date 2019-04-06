package com.youthchina.dto.job;

import com.youthchina.domain.qingyang.Industry;
import com.youthchina.dto.util.DurationDTO;
import com.youthchina.dto.util.LocationDTO;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchDTO {

    private String jobName;
    private Integer comId;
    private String companyName;
    private DurationDTO duration;
    private Integer jobType;
    private Integer salaryFloor;
    private Integer salaryCap;
    private Boolean activate;
    private List<Industry> industryList;
    private DurationDTO durationDTO;
    private LocationDTO location;


    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public Integer getSalaryFloor() {
        return salaryFloor;
    }

    public void setSalaryFloor(Integer salaryFloor) {
        this.salaryFloor = salaryFloor;
    }

    public Integer getSalaryCap() {
        return salaryCap;
    }

    public void setSalaryCap(Integer salaryCap) {
        this.salaryCap = salaryCap;
    }

    public Boolean getActivate() {
        return activate;
    }

    public void setActivate(Boolean activate) {
        this.activate = activate;
    }

    public List<Industry> getIndustryList() {
        return industryList;
    }

    public void setIndustryList(List<Industry> industryList) {
        this.industryList = industryList;
    }

    public DurationDTO getDurationDTO() {
        return durationDTO;
    }

    public void setDurationDTO(DurationDTO durationDTO) {
        this.durationDTO = durationDTO;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }
}
