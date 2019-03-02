package com.youthchina.dto;

import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import java.sql.Date;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchDTO implements SearchDTO {
    private List<String> industry;
    private List<String> tags;
    private int page;
    private int size;
    private String key;

    private Integer id;
    private String jobName;
    private Integer comId;
    private String companyName;
    private DurationDTO duration;
    private Integer jobType;
    private Integer salaryFloor;
    private Integer salaryCap;
    private Integer activate;
    //private String location;
    //private List<Degree> jobReqList;
    private List<Industry> industryList;
    private DurationDTO durationDTO;
    private LocationDTO location;

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getActivate() {
        return activate;
    }

    public void setActivate(Integer activate) {
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

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public int getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
