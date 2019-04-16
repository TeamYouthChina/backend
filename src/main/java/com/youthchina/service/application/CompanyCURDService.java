package com.youthchina.service.application;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompanyCURDService extends DomainCRUDService<Company, Integer> {

    List<Company> getByName(String comName);

    Company getCompanyWithCollected(Integer companyId, Integer userId) throws NotFoundException;
}
