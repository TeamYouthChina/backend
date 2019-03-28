package com.youthchina.dto.util;

import com.youthchina.domain.Qinghong.Location;

/**
 * @program: youthchina
 * @description: 返回地点DTO
 * @author: Qinghong Wang
 * @create: 2019-02-23 16:29
 **/
public class LocationResponseDTO {
    private String location;

    public LocationResponseDTO(Location location) {
        this.location = location.getRegion_chn();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
