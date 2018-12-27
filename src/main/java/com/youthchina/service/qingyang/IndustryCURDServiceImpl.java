package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Industry_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndustryCURDServiceImpl implements IndustryCURDService{
    @Resource
    CompanyMapper companyMapper;

    @Override
    public Industry_qingyang get(Integer id) throws NotFoundException {
        return companyMapper.selectIndustry(id);
    }

    @Override
    public List<Industry_qingyang> get(List<Integer> id) throws NotFoundException {
        return companyMapper.selectIndustryByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteIndustry();
    }

    @Override
    public Industry_qingyang update(Industry_qingyang industry_qingyang) throws NotFoundException {
        Integer result = companyMapper.updateIndustry(industry_qingyang);
        return  this.get(result);
    }

    @Override
    public Industry_qingyang add(Industry_qingyang entity) {
        Integer result = companyMapper.insertIndustry(entity);
        return companyMapper.selectIndustry(result);
    }
}
