package com.youthchina.dto;

import com.youthchina.domain.qingyang.Company;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-16
 **/
public class CompanyDTO {

    /*TODO
avatarUrl	string
pattern: (http|https)://(.?)*
note	string*/

    private Integer id;
    private String name;
    private LocationDTO location;
    private String website; // pattern: (http|https)://(.?)*
    private NationDTO nation;

    //TODO avatarUrl ; note
    public CompanyDTO(Company domain) {
        this.id = domain.getCompanyId();
        this.name = domain.getCompanyName();
        this.location = new LocationDTO(domain.getLocation()); // Default: Chinese Location
        this.website = domain.getCompanyWebsite();
        this.nation = new NationDTO(domain.getCountry()); // Default: Chinese Location
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
}
