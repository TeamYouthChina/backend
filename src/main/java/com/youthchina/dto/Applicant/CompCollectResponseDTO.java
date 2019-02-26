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
    private OrganizationDTO organizationDTO;

    public CompCollectResponseDTO(CompCollect compCollect) {
        this.collect_id = compCollect.getCollect_id();
        this.organizationDTO=new OrganizationDTO(compCollect.getCompany());
    }

    public CompCollectResponseDTO() {

    }

    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public OrganizationDTO getOrganizationDTO() {
        return organizationDTO;
    }

    public void setOrganizationDTO(OrganizationDTO organizationDTO) {
        this.organizationDTO = organizationDTO;
    }
}
