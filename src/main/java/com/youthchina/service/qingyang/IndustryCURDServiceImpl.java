package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IndustryCURDServiceImpl implements IndustryCURDService{
    @Resource
    CompanyMapper companyMapper;

    @Override
    public Industry get(Integer id) throws NotFoundException {
        return companyMapper.selectIndustry(id);
    }

    @Override
    public List<Industry> get(List<Integer> id) throws NotFoundException {
        return companyMapper.selectIndustryByIdList(id);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteIndustry();
    }

    @Override
    public Industry update(Industry industry_) throws NotFoundException {
        Integer result = companyMapper.updateIndustry(industry_);
        return  this.get(result);
    }

    @Override
    public Industry add(Industry entity) {
        Integer result = companyMapper.insertIndustry(entity);
        return companyMapper.selectIndustry(result);
        //return new Industry();
    }
}
