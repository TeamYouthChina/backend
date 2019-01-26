package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.CompanyVerification;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyVerificationCURDServiceImpl implements CompanyVerificationCURDService {
    @Resource
    CompanyMapper companyMapper;


    @Override
    public CompanyVerification get(Integer id) throws NotFoundException {
        return companyMapper.selectCompanyVerification(id);
    }

    @Override
    public List<CompanyVerification> get(List<Integer> id) throws NotFoundException {
        return companyMapper.selectCompanyVerificationByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteCompanyVerification(id);
    }

    @Override
    public CompanyVerification update(CompanyVerification companyVerification_) throws NotFoundException {
        Integer result = companyMapper.updateCompanyVerification(companyVerification_);
        return this.get(result);
    }

    @Override
    public CompanyVerification add(CompanyVerification entity) {
        Integer result = companyMapper.insertCompanyVerification(entity);
        return companyMapper.selectCompanyVerification(result);
    }
}
