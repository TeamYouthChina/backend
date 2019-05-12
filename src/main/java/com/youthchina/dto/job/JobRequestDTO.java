package com.youthchina.dto.job;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.qingyang.Job;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.LocationDTO;

import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class JobRequestDTO implements RequestDTO<Job> {
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
    private List<LocationDTO> location;
    private String startTime;
//    private String endTime;
    private String deadLine;
    private String job_description;
    private String job_duty;
    private String mail;


    public JobRequestDTO() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

//    public List<Integer> getLocation() {
//        return location;
//    }
//
//    public void setLocation(List<Integer> location) {
//        this.location = location;
//    }


    public List<LocationDTO> getLocation() {
        return location;
    }

    public void setLocation(List<LocationDTO> location) {
        this.location = location;
    }

    @JsonProperty("dead_line")
    @JsonTimeStamp
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

    @JsonProperty("start_time")
    @JsonTimeStamp
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Override
    public Job convertToDomain() {
        return new Job(this);
    }


}
