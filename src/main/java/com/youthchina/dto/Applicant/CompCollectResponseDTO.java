package com.youthchina.dto.Applicant;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.dto.OrganizationDTO;

/**
 * @program: youthchina
 * @description: 公司收藏DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 11:31
 **/
public class CompCollectResponseDTO {
    private Integer collect_id;
    private OrganizationDTO organization;

    public CompCollectResponseDTO(CompCollect compCollect) {
        this.collect_id = compCollect.getCollect_id();
        this.organization=new OrganizationDTO(compCollect.getCompany());
    }

    public CompCollectResponseDTO() {

    }

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }
}
