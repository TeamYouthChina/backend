package com.youthchina.service.jinhao.communityQA;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompanyRecommendService extends DomainCRUDService<Company, Integer> {
    List<Company> getPopCompanyForYou();
    List<Company> getNewCompanyForYou();
}
