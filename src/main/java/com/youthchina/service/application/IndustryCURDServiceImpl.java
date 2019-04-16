package com.youthchina.service.application;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 行业CURD
 */
@Service
public class IndustryCURDServiceImpl implements IndustryCURDService {
    @Resource
    CompanyMapper companyMapper;

    /**
     * 搜索行业
     *
     * @param id 行业Id
     * @return
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Industry get(Integer id) throws NotFoundException {
        return companyMapper.selectIndustry(id);
    }


    /**
     * 按行业Id删除行业
     *
     * @param id id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteIndustry(id);
    }

    /**
     * 更新行业
     *
     * @param industry 行业类
     * @return 更新后的行业类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Industry update(Industry industry) throws NotFoundException {
        Integer result = companyMapper.updateIndustry(industry);
        return companyMapper.selectIndustry(industry.getIndNum());
    }

    /**
     * 添加行业
     *
     * @param entity 行业类
     * @return 添加的行业类
     */
    @Override
    @Transactional
    public Industry add(Industry entity) {
        Integer result = companyMapper.insertIndustry(entity);
        return companyMapper.selectIndustry(entity.getIndNum());
    }
}
