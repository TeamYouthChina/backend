package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyTestMapper;
import com.youthchina.domain.qingyang.Hr_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrCURDServiceImpl implements HrCURDService{
    @Resource
    CompanyTestMapper companyTestMapper;

    @Override
    public Hr_qingyang get(Integer id) throws NotFoundException {
        return companyTestMapper.selectHr(id);
    }

    @Override
    public List<Hr_qingyang> get(List<Integer> ids) throws NotFoundException {
        return companyTestMapper.selectHrByIdList(ids);
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        companyTestMapper.deleteHr(id);
    }

    @Override
    public Hr_qingyang update(Hr_qingyang hr_qingyang) throws NotFoundException {
        Integer result = companyTestMapper.updateHr(hr_qingyang);
        return companyTestMapper.selectHr(result);
    }

    @Override
    public Hr_qingyang add(Hr_qingyang entity) {
        Integer result = companyTestMapper.insertHr(entity);
        return companyTestMapper.selectHr(result);
    }
}
