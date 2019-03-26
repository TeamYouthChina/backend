package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyEmployeeMapper;
import com.youthchina.domain.qingyang.CompanyEmployee;
import com.youthchina.exception.zhongyang.NotFoundException;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-24
 **/
public class CompanyEmployeeCURDServiceImpl implements CompanyEmployeeCURDService{
    @Resource
    CompanyEmployeeMapper companyEmployeeMapper;

    @Override
    public CompanyEmployee get(Integer id) throws NotFoundException {
        return companyEmployeeMapper.getCompanyEmployee(id);
    }

    @Override
    public List<CompanyEmployee> get(List<Integer> idList) throws NotFoundException {
        return companyEmployeeMapper.getCompanyEmployeeByIdList(idList);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Integer result = companyEmployeeMapper.deleteCompanyEmployee(id);
    }

    @Override
    public CompanyEmployee update(CompanyEmployee companyEmployee) throws NotFoundException {
        Integer result = companyEmployeeMapper.updateCompanyEmployee(companyEmployee);
        return companyEmployeeMapper.getCompanyEmployee(companyEmployee.getCompanyId());
    }

    @Override
    public CompanyEmployee add(CompanyEmployee entity) {
        Integer result = companyEmployeeMapper.insertCompanyEmployee(entity);
        return companyEmployeeMapper.getCompanyEmployee(entity.getCompanyId());
    }
}