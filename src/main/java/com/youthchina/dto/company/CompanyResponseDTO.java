package com.youthchina.dto.company;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.*;
import com.youthchina.dto.ResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-24
 **/
public class CompanyResponseDTO implements ResponseDTO<Company> {
    private Integer id;
    private String name;
    private String avatarUrl;
    private String location;
    private String website;
    private String note;
    private String nation;
    private List<String> photoUrlList;
    private Integer jobCount;
//    private List<String> industryList;
    private Boolean isCollected = false;

    public CompanyResponseDTO() {

    }

    public CompanyResponseDTO(Company company) {
        convertToDTO(company);
    }


    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

//    public List<String> getIndustryList() {
//        return industryList;
//    }
//
//    public void setIndustryList(List<String> industryList) {
//        this.industryList = industryList;
//    }

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

    public List<String> getPhotoUrlList() {
        return photoUrlList;
    }

    public void setPhotoUrlList(List<String> photoUrlList) {
        this.photoUrlList = photoUrlList;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    @Override
    public void convertToDTO(Company company) {
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
        List<Logo> logoList = company.getLogoList();
        if (logoList != null && logoList.size() > 0) {
            this.avatarUrl = company.getLogoList().get(0).getUrl();
        }
        Location location = company.getLocation();
        if (location != null) {
            this.location = location.getRegionName();
        }
        Country country = company.getCountry();
        if (country != null) {
            this.nation = country.getCountryChn();
        }
        List<CompanyPhoto> photoList = company.getPhotoList();
        if(photoList != null && photoList.size() > 0){
            this.photoUrlList = new ArrayList<>();
            for(CompanyPhoto photo : photoList){
                this.photoUrlList.add(photo.getUrl());
            }
        }
        this.website = company.getCompanyWebsite();
        this.note = company.getCompanyIntroduc();
        this.jobCount = company.getJobCount();
        if(company.getCollected() != null){
            this.isCollected = company.getCollected();
        }
//        List<Industry> industryObjList = company.getIndList();
//        if(industryObjList != null && industryObjList.size() > 0){
//            this.industryList = new ArrayList<>();
//            for(Industry ind : industryObjList){
//                this.industryList.add(ind.getIndChn());
//            }
//        }
    }
}
