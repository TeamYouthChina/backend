package com.youthchina.dto.job;

import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.applicant.OrganizationDTO;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-24
 **/
public class JobResponseDTO implements ResponseDTO<Job> {
    private int id;
    private String name;
    private OrganizationDTO organization;
    private List<String> location;
    private String type;
    private Timestamp startTime;
    private Timestamp deadLine;
    private String job_duty;
    private String job_description;
    private Boolean isCollected = false;

    /*{
  "content": {
    "id": 0,
    "name": "string",
    "organization": {
      "id": 0,
      "name": "string",
      "avatarUrl": "string",
      "location": "string",
      "website": "string",
      "note": "string",
      "nation": "string"
    },
    "location": "string",
    "type": "full-time",
    "deadLine": "string"
  },
  "status": {
    "code": 0,
    "reason": "string"
  }
}*/

    public JobResponseDTO() {

    }

    public JobResponseDTO(Job job) {
        this.convertToDTO(job);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }


    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonTimeStamp
    public Timestamp getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Timestamp deadLine) {
        this.deadLine = deadLine;
    }

    public String getJob_duty() {
        return job_duty;
    }

    public void setJob_duty(String job_duty) {
        this.job_duty = job_duty;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    @JsonTimeStamp
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    @Override
    public void convertToDTO(Job job) {
        this.id = job.getJobId();
        this.name = job.getJobName();
        Company company = job.getCompany();
        this.organization = company == null ? null : new OrganizationDTO(company);
        List<Location> locationList = job.getJobLocationList();
        if (locationList != null && locationList.size() > 0) {
//            Location location = locationList.get(0);
//            if (location != null) {
//                this.location = location.getRegionName(); // 默认中文名
//            }
            this.location = new ArrayList<>();
            for(Location location : locationList){
                this.location.add("" + location.getRegionId());
            }
        }
        int jobType = job.getJobType();

        switch (jobType){
            case Job.internJobType: this.type = "实习"; break;
            case Job.partJobType: this.type = "兼职"; break;
            case Job.fullJobType: this.type = "全职"; break;
        }


        this.startTime = new Timestamp(job.getJobStartTime().getTime());
        this.deadLine = new Timestamp(job.getJobEndTime().getTime());
        this.job_duty = job.getJobDuty();
        this.job_description = job.getJobDescription();
        if(job.getCollected() != null){
            this.isCollected = job.getCollected();
        }
    }

    public List<JobResponseDTO> convertToDTO(List<Job> jobList){
        if(jobList != null && jobList.size() > 0){
            List<JobResponseDTO> jobResponseDTOList = new ArrayList<>();
            for(Job job : jobList){
                JobResponseDTO jobResponseDTO = new JobResponseDTO();
                jobResponseDTO.convertToDTO(job);
                jobResponseDTOList.add(jobResponseDTO);
            }
            return jobResponseDTOList;
        } else {
            return null;
        }
    }
}
