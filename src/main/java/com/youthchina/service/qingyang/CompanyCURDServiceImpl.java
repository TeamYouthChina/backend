package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyCURDServiceImpl implements CompanyCURDService {
    @Resource
    CompanyMapper companyMapper;

    @Override
    public Company get(Integer id) throws NotFoundException {
        return companyMapper.selectCompany(id);
    }

    @Override
    public List<Company> get(List<Integer> id) throws NotFoundException {
        return companyMapper.selectCompanyByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteCompany(id);
        companyMapper.deleteCompanyInd(id); // CompanyID
    }

    @Override
    public Company update(Company company) throws NotFoundException {
        Integer result = companyMapper.updateCompany(company);
        this.updateInd(company);
        return companyMapper.selectCompany(company.getCompanyId());
    }

    @Override
    public Company add(Company entity) {
        Integer result = companyMapper.insertCompany(entity);
        this.addInd(entity);
        return companyMapper.selectCompany(result);
    }



    public void addInd(Company company) {
        companyMapper.insertCompanyInd(company.getIndList());
    }

    public void deleteInd(Company company) throws NotFoundException {
        companyMapper.deleteCompanyInd(company.getCompanyId());
    }

    public void updateInd(Company company) throws NotFoundException {
        this.deleteInd(company);
        this.addInd(company);
    }


    @Override
    public List<Company> getByName(String comName) {
        return companyMapper.selectCompanyByName(comName);
    }
}
