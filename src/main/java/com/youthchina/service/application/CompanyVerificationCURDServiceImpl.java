package com.youthchina.service.application;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.CompanyVerification;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 公司认证 CURD
 */
@Service
public class CompanyVerificationCURDServiceImpl implements CompanyVerificationCURDService {
    @Resource
    CompanyMapper companyMapper;

    /**
     * 按认证Id 获取公司认证
     *
     * @param id 认证Id
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public CompanyVerification get(Integer id) throws NotFoundException {
        return companyMapper.selectCompanyVerification(id);
    }


    /**
     * 按认证Id 删除认证
     *
     * @param id id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteCompanyVerificationById(id);
    }

    /**
     * 更新认证
     *
     * @param companyVerification 认证类
     * @return 更新后的认证
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public CompanyVerification update(CompanyVerification companyVerification) throws NotFoundException {
        Integer result = companyMapper.updateCompanyVerification(companyVerification);
        return companyMapper.selectCompanyVerification(companyVerification.getVerifyId());
    }

    /**
     * 添加认证
     *
     * @param entity 认证类
     * @return 所添加的认证
     */
    @Override
    @Transactional
    public CompanyVerification add(CompanyVerification entity) {
        Integer result = companyMapper.insertCompanyVerification(entity);
        return companyMapper.selectCompanyVerification(result);
    }
}
