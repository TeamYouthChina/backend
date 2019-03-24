package com.youthchina.dto.job;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.applicant.OrganizationDTO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-24
 **/
public class JobResponseDTO {
    private int id;
    private String name;
    private OrganizationDTO organization;
    private String location;
    private String type;
    private String deadLine;
    private String job_duty;
    private String job_description;

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
        Company company = job.getCompany();
        this.organization = company == null ? null : new OrganizationDTO(company);
        List<Location> locationList = job.getJobLocationList();
        if (locationList != null && locationList.size() > 0) {
            Location location = locationList.get(0);
            if (location != null) {
                this.location = location.getRegion_chn(); // 默认中文名
            }

        }
        int jobType = job.getJobType();
        if (jobType == 1) {
            this.type = "实习";
        } else if (jobType == 2) {
            this.type = "兼职";
        } else {
            this.type = "全职";
        }
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        this.deadLine = df.format(job.getJobEndTime());
        this.job_duty = job.getJobDuty();
        this.job_description = job.getJobDescription();
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
}
