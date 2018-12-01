package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyTestMapper;
import com.youthchina.domain.qingyang.CompanyVerification_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CompanyVerificationCURDServiceImpl implements CompanyVerificationCURDService {
    @Resource
    CompanyTestMapper companyTestMapper;


    @Override
    public CompanyVerification_qingyang get(Integer id) throws NotFoundException {
        return companyTestMapper.selectCompanyVerfication(id);
    }

    @Override
    public List<CompanyVerification_qingyang> get(List<Integer> id) throws NotFoundException {
        return companyTestMapper.selectCompanyVerficationByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyTestMapper.deleteCompanyVerfication(id);
    }

    @Override
    public CompanyVerification_qingyang update(CompanyVerification_qingyang companyVerification_qingyang) throws NotFoundException {
        Integer result = companyTestMapper.updateCompanyVerification(companyVerification_qingyang);
        return this.get(result);
    }

    @Override
    public CompanyVerification_qingyang add(CompanyVerification_qingyang entity) {
        Integer result = companyTestMapper.insertCompanyVerfication(entity);
        return companyTestMapper.selectCompanyVerfication(result);
    }
}
