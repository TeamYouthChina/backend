package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyPhoto;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.domain.qingyang.Logo;

import java.util.ArrayList;
import java.util.List;

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

    private List<String> photoUrlList;
    private Integer jobCount;
    private Boolean isCollected = false;


    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }

    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    public OrganizationDTO(Company company) {
        if (company == null) return;
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
        //TODO
        List<Logo> logoList = company.getLogoList();
        if(logoList != null && logoList.size() > 0){
            this.avatarUrl = company.getLogoList().get(0).getDocuLocalId();
        }
        Location location = company.getLocation();
        if (location != null) {
            this.location = "" + location.getRegionId();
                    //.getRegionName();
        }
        this.website = company.getCompanyWebsite();
        Country country = company.getCountry();
        this.nation = country == null ? null : country.getCountryChn();// 中文名
        this.note = company.getCompanyIntroduc();
        List<CompanyPhoto> photoList = company.getPhotoList();

        if(photoList != null && photoList.size() > 0){
            this.photoUrlList = new ArrayList<>();
            for(CompanyPhoto photo : photoList){
                photoUrlList.add(photo.getUrl());
            }
        }
        this.jobCount = company.getJobCount();
        if(company.getCollected() != null){
            this.isCollected = company.getCollected();
        }
    }

    public OrganizationDTO() {
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
