package com.youthchina.dto.util;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.dto.ResponseDTO;

/**
 * @program: youthchina
 * @description: 返回地点DTO
 * @author: Qinghong Wang
 * @create: 2019-02-23 16:29
 **/
public class LocationResponseDTO implements ResponseDTO {
    private String location;

    public LocationResponseDTO(Location location) {
        this.location = location.getRegionName();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
