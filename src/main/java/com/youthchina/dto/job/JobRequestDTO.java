package com.youthchina.dto.job;

import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.RequestDTO;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobRequestDTO implements JobDTOInterface, RequestDTO<Job> {
    /*
    * {
  "name": "string",
  "organization_id": 0,
  "location": [
    0
  ],
  "type": "full-time",
  "deadLine": "string",
  "job_description": "string",
  "job_duty": "string"
}*/
    private int id;
    private String name;
    private Integer organization_id;
    private String type;
    private Integer userId;
    private List<Integer> location;
    private String deadLine;
    private String job_description;
    private String job_duty;


    public JobRequestDTO() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public List<Integer> getLocation() {
        return location;
    }

    public void setLocation(List<Integer> location) {
        this.location = location;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getJob_duty() {
        return job_duty;
    }

    public void setJob_duty(String job_duty) {
        this.job_duty = job_duty;
    }

    @Override
    public Job convertToDomain() {
        return new Job(this);
    }
}
