package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyTestMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyCURDServiceImpl implements CompanyCURDService {
    @Resource
    CompanyTestMapper companyTestMapper;

    @Override
    public Company get(Integer id) throws NotFoundException {
        return companyTestMapper.selectCompany(id);
    }

    @Override
    public List<Company> get(List<Integer> id) throws NotFoundException {
        return companyTestMapper.selectCompanyByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyTestMapper.deleteCompany(id);
    }

    @Override
    public Company update(Company company_qingyang) throws NotFoundException {
        Integer result = companyTestMapper.updateCompany(company_qingyang);
        return this.get(result);
    }

    @Override
    public Company add(Company entity) {
        Integer result = companyTestMapper.insertCompany(entity);
        return companyTestMapper.selectCompany(result);
    }
}
