package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompanyCURDService extends DomainCRUDService<Company, Integer> {

    List<Company> getByName(String comName);

    Company getCompanyWithCollected(Integer companyId, Integer userId) throws NotFoundException;
}
