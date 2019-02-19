package com.youthchina.domain.Qinghong;

import com.youthchina.domain.qingyang.USAState;
import com.youthchina.dto.LocationDTO;

import java.sql.Timestamp;

/**
 * @program: V-0.1
 * @description: 地区实体
 * @author: Qinghong Wang
 * @create: 2019-01-22 10:57
 **/
public class Location {
    private Integer region_num;
    private String region_chn;
    private String region_eng;
    private Integer region_level;
    private Integer region_parent_num;
    private Timestamp start_time;
    private Integer is_delete;
    private Timestamp is_delete_time;



    //for insert to JOB_LOCATION
    private Integer jobId;

    private String nation_code;

    //USA
    private String regionCity;
    private USAState usaState;

    public String getRegionCity() {
        return regionCity;
    }

    public void setRegionCity(String regionCity) {
        this.regionCity = regionCity;
    }

    public USAState getUsaState() {
        return usaState;
    }

    public void setUsaState(USAState usaState) {
        this.usaState = usaState;
    }

    public String getNation_code() {
        return nation_code;
    }

    public void setNation_code(String nation_code) {
        this.nation_code = nation_code;
    }

    public Location(LocationDTO locationDTO){
        this.nation_code = locationDTO.getNation_code();
        this.region_num = Integer.valueOf(locationDTO.getLocation_code());
    }

    public Location(){}

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getRegion_num() {
        return region_num;
    }

    public void setRegion_num(Integer region_num) {
        this.region_num = region_num;
    }

    public String getRegion_chn() {
        return region_chn;
    }

    public void setRegion_chn(String region_chn) {
        this.region_chn = region_chn;
    }

    public String getRegion_eng() {
        return region_eng;
    }

    public void setRegion_eng(String region_eng) {
        this.region_eng = region_eng;
    }

    public Integer getRegion_level() {
        return region_level;
    }

    public void setRegion_level(Integer region_level) {
        this.region_level = region_level;
    }

    public Integer getRegion_parent_num() {
        return region_parent_num;
    }

    public void setRegion_parent_num(Integer region_parent_num) {
        this.region_parent_num = region_parent_num;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
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

    @Override
    public String toString() {
        return region_chn.toString();
    }
}
