package com.youthchina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class LocationDTO {
    private String country;
    private String provinceOrState;
    private String cityOrRegion;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("province/state")
    public String getProvinceOrState() {
        return provinceOrState;
    }

    public void setProvinceOrState(String provinceOrState) {
        this.provinceOrState = provinceOrState;
    }

    @JsonProperty("city/region")
    public String getCityOrRegion() {
        return cityOrRegion;
    }

    public void setCityOrRegion(String cityOrRegion) {
        this.cityOrRegion = cityOrRegion;
    }
}
