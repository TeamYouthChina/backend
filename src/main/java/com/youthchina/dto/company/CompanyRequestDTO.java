package com.youthchina.dto.company;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.LocationDTO;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
public class CompanyRequestDTO implements RequestDTO<Company> {

    private Integer id;
    private String name;
    private LocationDTO location;
    private String website; // pattern: (http|https)://(.?)*
    private String nation;
    private String avatarUrl;
    private String note;
    private Integer userId;

    public CompanyRequestDTO() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
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

    @Override
    public Company convertToDomain() {
        return new Company(this);
    }
}
