package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.Country;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class OrganizationDTO {

    private int id;
    private String name;
    private String avatarUrl;
    private String location;
    private String website;
    private String note;
    private String nation;


    public OrganizationDTO(Company company) {
        if (company == null) return;
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
        this.avatarUrl = company.getLogos().get(0).getDocuLocalId();
        Location location = company.getLocation();
        this.location = location == null ? null : location.getRegion_chn(); // 中文名
        this.website = company.getCompanyWebsite();
        Country country = company.getCountry();
        this.nation = country == null ? null : country.getCountryChn();// 中文名
        this.note = company.getCompanyIntroduc();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
