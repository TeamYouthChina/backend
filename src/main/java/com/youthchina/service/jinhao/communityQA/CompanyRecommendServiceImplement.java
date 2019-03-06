package com.youthchina.service.jinhao.communityQA;

import com.youthchina.dao.jinhao.RecommendMapper;
import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.qingyang.CompanyCURDServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class CompanyRecommendServiceImplement implements CompanyRecommendService {

    @Resource
    RecommendMapper recommendMapper;

    @Resource
    CompanyMapper companyMapper;

    @Autowired
    CompanyCURDServiceImpl companyCURDService;

    @Override
    public List<Company> getPopCompanyForYou() throws NotFoundException{
        List<Integer> companyIds =  recommendMapper.getRandomPopCompany();
        List<Company> companies = companyCURDService.get(companyIds);

//                = new ArrayList<>();
//        for(Integer id : companyIds){
//            companies.add(companyCURDService.get(id));
//        }
        return companies;
    }

    @Override
    public List<Company> getNewCompanyForYou() {
        List<Integer> companyIds = recommendMapper.getRandomNewCompany();
        List<Company> companies = new ArrayList<>();
        for(Integer id : companyIds){
            companies.add(companyMapper.selectCompany(id));
        }
        return companies;
    }

    @Override
    public Company get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Company> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Company update(Company company) throws NotFoundException {
        return null;
    }

    @Override
    public Company add(Company entity) {
        return null;
    }
}
