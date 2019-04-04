package com.youthchina.service.jinhao.toBeDeleted;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompanyRecommendService extends DomainCRUDService<Company, Integer> {
    List<Company> getPopCompanyForYou() throws NotFoundException;
    List<Company> getNewCompanyForYou();
}
