package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyEvaluate;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-24
 **/
public class CompanyEvaluateCURDService implements DomainCRUDService<CompanyEvaluate, Integer> {
    @Override
    public CompanyEvaluate get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<CompanyEvaluate> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public CompanyEvaluate update(CompanyEvaluate companyEvaluate) throws NotFoundException {
        return null;
    }

    @Override
    public CompanyEvaluate add(CompanyEvaluate entity) {
        return null;
    }
}
