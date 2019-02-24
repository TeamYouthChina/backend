package com.youthchina.dto;

import com.youthchina.domain.qingyang.Job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-24
 **/
public class JobResponseDTO implements ResponseDTO {
    private int id;
    private String name;
    private OrganizationDTO organization;
    private String location;
    private String type;
    private String deadLine;
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
    public JobResponseDTO(Job job) {
        this.id = job.getJobId();
        this.name = job.getJobName();
        this.organization = new OrganizationDTO(job.getCompany());
        this.location = job.getJobLocationList().get(0).getRegion_chn(); // 默认中文名
        int jobType = job.getJobType();
        if(jobType == 1){
            this.type = "实习";
        } else if (jobType == 2 ){
            this.type = "兼职";
        } else {
            this.type = "全职";
        }
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.deadLine = df.format(job.getJobEndTime());
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    @Override
    public StatusDTO getStatus() {
        return null;
    }

    @Override
    public void setStatus(StatusDTO status) {

    }
}
