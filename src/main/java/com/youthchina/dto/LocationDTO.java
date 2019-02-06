package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Location;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class LocationDTO {
    private Integer region_num;

    public LocationDTO(Location location) {
        this.region_num = location.getRegion_num();
    }

    public void setRegion_num(Integer region_num) {
        this.region_num = region_num;
    }

    public Integer getRegion_num() {
        return region_num;
    }
}
