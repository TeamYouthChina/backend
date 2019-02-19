package com.youthchina.dto;

import com.youthchina.domain.qingyang.Degree;
import com.youthchina.domain.qingyang.Industry;
import java.sql.Date;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobSearchDTO implements SearchDTO {
    private String[] industry;
    private List<String> tagList;
    private int page;
    private int size;
    private String key;
    private String jobName;

    private String comName;
    private DurationDTO duration;

    private Integer type;
    private Integer salaryFloor;
    private Integer salaryCap;
    private Integer active;
    private String location;
    private List<Degree> jobReqList;
    private List<Industry> industryList;



    public String getJobName(){return jobName;}
    public void setJobName(String jobName){this.jobName=jobName;}


    public String getComName(){return comName;}
    public void setComName(String comName){this.comName = comName;}

    public DurationDTO getDuration() {
        return duration;

    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public Integer getType(){return type;}
    public void setType(Integer type){this.type = type;}

    public Integer getSalaryFloor(){return salaryFloor;}
    public void setSalaryFloor(Integer salaryFloor){this.salaryFloor = salaryFloor;}

    public Integer getSalaryCap(){return salaryCap;}
    public void setSalaryCap(Integer salaryCap){this.salaryCap = salaryCap;}

    public Integer getActive(){return active;}
    public void setActive(Integer active){this.active = active;}

    public String getLocation(){return location;}
    public void setLocation(String location){this.location = location;}

    public List<Degree> getJobReqList(){return jobReqList;}
    public void setJobReqList(List<Degree> jobReqList){this.jobReqList = jobReqList;}

    public List<Industry> getIndustryList(){return industryList;}
    public void setIndustryList(List<Industry> industryList){this.industryList = industryList;}

    public String[] getIndustry() {
        return industry;
    }

    public void setIndustry(String[] industry) {
        this.industry = industry;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
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
