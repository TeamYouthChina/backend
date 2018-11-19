package com.youthchina.service.miaomiaozhang.impl;


import com.youthchina.dao.miaomiaozhang.CompanyHrMapper;
import com.youthchina.domain.miaomiaozhang.CompanyHr;
import com.youthchina.domain.miaomiaozhang.CompanyInfo;
import com.youthchina.service.miaomiaozhang.CompanyHrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
@Service
@Transactional(rollbackFor = Exception.class)
public class CompanyHrServiceImpl implements CompanyHrService {

    @Autowired
    CompanyHrMapper companyHrMapper;
    /**添加HR*/
    @Override
    public Map<String,Object> addHr(CompanyHr companyHr) {
        //定义消息返回值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = companyHrMapper.insertHr(companyHr);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /**更新HR信息*/
    @Override
    public Map<String, Object> updateHr(String hr_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = companyHrMapper.updateHr(hr_id);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    /** 查询Hr信息*/
    @Override
    public CompanyHr selectHr(String hr_id) {
        return companyHrMapper.selectHr(hr_id);
    }

    /**查询企业资质认证的信息*/
    @Override
    public CompanyInfo selectEnterpriseVerification(String hr_id) {
        return companyHrMapper.selectEnterpriseVerification(hr_id);
    }

    /**查询个人资质认证的信息*/
    @Override
    public CompanyHr selectPersonalVerification(String hr_id) {
        return companyHrMapper.selectPersonalVerification(hr_id);
    }

    /**修改个人资质认证的信息*/
    @Override
    public Map<String, Object> updatePersonalVerification(String hr_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);
        Integer result = companyHrMapper.updatePersonalVerification(hr_id);
        if (1!=result){
            resultMap.put("result", false);
        }
        return resultMap;
    }
}
