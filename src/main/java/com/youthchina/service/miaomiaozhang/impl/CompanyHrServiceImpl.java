package com.youthchina.service.miaomiaozhang.impl;


import com.youthchina.dao.miaomiaozhang.CompanyHrMapper;
import com.youthchina.domain.miaomiaozhang.CompanyHr;
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
    @Override
    public Map<String,Object> addHr(CompanyHr companyHr) {
        //定义消息返回值
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);

        Integer result = companyHrMapper.insertHr(companyHr);
        if (result!=1){
            resultMap.put("result", false);
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> updateHr(String hr_id) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", true);

        Integer result = companyHrMapper.updateHr(hr_id);
        if (result!=1){
            resultMap.put("result", false);
        }
        return resultMap;
    }




}
