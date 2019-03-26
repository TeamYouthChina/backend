package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.CompanyEmployee;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-24
 **/
public class CompanyEmployeeCURDServiceImpl implements CompanyEmployeeCURDService{
    @Override
    public CompanyEmployee get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<CompanyEmployee> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public CompanyEmployee update(CompanyEmployee companyEmployee) throws NotFoundException {
        return null;
    }

    @Override
    public CompanyEmployee add(CompanyEmployee entity) {
        return null;
    }
}
