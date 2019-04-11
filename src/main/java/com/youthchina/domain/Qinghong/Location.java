package com.youthchina.domain.Qinghong;

import com.youthchina.dto.util.LocationDTO;
import com.youthchina.exception.zhongyang.BaseException;

/**
 * @program: V-0.1
 * @description: 地区实体
 * @author: Qinghong Wang
 * @create: 2019-01-22 10:57
 **/
public class Location {
    private Integer regionId;
    private Integer regionNum;
    private String regionName;
    private String country;
    private String countryName;



    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Location(LocationDTO locationDTO) {
        this.country = locationDTO.getNation_code();
        String locationCode = locationDTO.getLocation_code().trim();
        if(locationCode.length() == 5){ // Convert to USA
            locationCode = 9 + locationCode;
        }
//        else if(locationCode.length() != 6){
//            throw new BaseException(4040, 404, "Location Format Error");
//        }
        //todo string 转成Intger的异常判断
        this.regionId = Integer.valueOf(locationDTO.getLocation_code());
    }

    
    public Location(Integer regionNum) {
        this.regionNum = regionNum;
    }

    public Location() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getRegionNum() {
        return regionNum;
    }

    public void setRegionNum(Integer regionNum) {
        this.regionNum = regionNum;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }


}
