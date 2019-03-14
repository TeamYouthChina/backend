package com.youthchina.dto.util;

import com.youthchina.domain.qingyang.Country;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
public class NationDTO {
    private String countryAbbre;

    public NationDTO(Country country) {
        this.countryAbbre = country.getCountryAbbre();
    }

    public NationDTO() {
    }

    public String getCountryAbbre() {
        return countryAbbre;
    }

    public void setCountryAbbre(String countryAbbre) {
        this.countryAbbre = countryAbbre;
    }
}
