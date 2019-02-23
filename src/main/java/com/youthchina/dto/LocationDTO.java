package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Location;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class LocationDTO {

    private String nation_code;
    private String location_code;

    public LocationDTO(Location location) {
        this.location_code = "" + location.getRegion_num();
        this.nation_code = (location_code.charAt(0) == '9') ? "USA": "CHN";
    }

    public LocationDTO(){}

    public String getNation_code() {
        return nation_code;
    }

    public void setNation_code(String nation_code) {
        this.nation_code = nation_code;
    }

    public String getLocation_code() {
        return location_code;
    }

    public void setLocation_code(String location_code) {
        this.location_code = location_code;
    }
}
