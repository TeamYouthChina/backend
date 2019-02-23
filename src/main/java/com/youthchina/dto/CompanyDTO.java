package com.youthchina.dto;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Industry;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
public class CompanyDTO {

    private Integer id;
    private String name;
    private LocationDTO location;
    private String website; // pattern: (http|https)://(.?)*
    private NationDTO nation;
    private String avatarUrl;
    private String note;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CompanyDTO() {
    }

    public CompanyDTO(Company domain) {
        this.id = domain.getCompanyId();
        this.name = domain.getCompanyName();
        this.location = new LocationDTO(domain.getLocation()); // Default: Chinese Location
        this.website = domain.getCompanyWebsite();
        this.nation = new NationDTO(domain.getCountry()); // Default: Chinese Location
        this.avatarUrl = domain.getCompanyLogo();
        this.note = domain.getCompanyIntroduc();
        this.userId = domain.getUserId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public NationDTO getNation() {
        return nation;
    }

    public void setNation(NationDTO nation) {
        this.nation = nation;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
