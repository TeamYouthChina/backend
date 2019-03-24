package com.youthchina.domain.Qinghong;

/**
 * @program: youthchina
 * @description: 所有大学
 * @author: Qinghong Wang
 * @create: 2019-03-23 15:36
 **/
public class University {
    private Integer univers_id;
    private String univers_name;
    private String country;

    public Integer getUnivers_id() {
        return univers_id;
    }

    public void setUnivers_id(Integer univers_id) {
        this.univers_id = univers_id;
    }

    public String getUnivers_name() {
        return univers_name;
    }

    public void setUnivers_name(String univers_name) {
        this.univers_name = univers_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
