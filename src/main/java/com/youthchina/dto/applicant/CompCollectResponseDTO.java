package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.CompCollect;

/**
 * @program: youthchina
 * @description: 公司收藏DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 11:31
 **/
public class CompCollectResponseDTO {
    private Integer id;
    private OrganizationDTO organization;

    public CompCollectResponseDTO(CompCollect compCollect) {
        this.id = compCollect.getCollect_id();
        this.organization = new OrganizationDTO(compCollect.getCompany());
    }

    public CompCollectResponseDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
}
