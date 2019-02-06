package com.youthchina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youthchina.domain.Qinghong.Location;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class LocationDTO {
    private Integer locationID;

    public LocationDTO(Location location){
        this.locationID = location.getRegion_num();
    }

    public LocationDTO(){}

    public Integer getlocationID() {
        return locationID;
    }

    public void setCountry(Integer locationID) {
        this.locationID = locationID;
    }
}
