package com.youthchina.dto.company;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyPhoto;
import com.youthchina.domain.qingyang.Country;
import com.youthchina.domain.qingyang.Logo;
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

    public CompanyResponseDTO() {

    }

    public CompanyResponseDTO(Company company) {
        convertToDTO(company);
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

    @Override
    public void convertToDTO(Company company) {
        this.id = company.getCompanyId();
        this.name = company.getCompanyName();
        List<Logo> logoList = company.getLogoList();
        if (logoList != null && logoList.size() > 0) {
            this.avatarUrl = company.getLogoList().get(0).getDocuLocalId();
        }
        Location location = company.getLocation();
        if (location != null) {
            this.location = location.getRegionName();
        }
        Country country = company.getCountry();
        if (country != null) {
            this.nation = country.getCountryChn();
        }
        //Test
        System.out.println("convertToDTO(Company company)");
        List<CompanyPhoto> photoList = company.getPhotoList();
        if(photoList != null && photoList.size() > 0){
            //Test
            System.out.println("photoList.size() : " + photoList.size());
            this.photoUrlList = new ArrayList<>();
            for(CompanyPhoto photo : photoList){
                //Test
                System.out.println("photo.getDocuLocalId() : " + photo.getDocuLocalId() );
                this.photoUrlList.add(photo.getDocuLocalId());
            }
        }
        this.website = company.getCompanyWebsite();
        this.note = company.getCompanyIntroduc();
    }
}
