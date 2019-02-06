package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Job;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class SimpleJobDTO {
    private int id;
    private String name;
    private OrganizationDTO organization;
    private String type;
    private List<LocationDTO> LocationList;

    public SimpleJobDTO(Job job) {
        this.id = job.getJobId();
        this.name = job.getJobName();
        this.organization = new OrganizationDTO(job.getCompany());
        for(int i =0; i < job.getJobLocationList().size(); i++){
            this.LocationList.add(new LocationDTO(job.getJobLocationList().get(i)));  //todo: create location object
        }
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

    public List<LocationDTO> getJobLocationList() {
        return LocationList;
    }

    public void setLocationList(List<LocationDTO> jobLocationList) {
        this.LocationList = LocationList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    private Timestamp deadline;
}
