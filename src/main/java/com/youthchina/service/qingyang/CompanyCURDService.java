package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompanyCURDService extends DomainCRUDService<Company, Integer> {

    List<Company> getByName(String comName);
}
