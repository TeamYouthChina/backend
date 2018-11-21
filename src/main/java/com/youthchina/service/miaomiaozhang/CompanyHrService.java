package com.youthchina.service.miaomiaozhang;

import com.youthchina.domain.miaomiaozhang.CompanyHr;
import com.youthchina.domain.miaomiaozhang.CompanyInfo;

import java.util.Map;

/**
 * CompanyHrService class
 *
 * @author miaomiaozhang
 * @date 2018/11/13
 */
public interface CompanyHrService {
    /**添加HR*/
    Map<String,Object> addHr(CompanyHr companyHr);

    /**修改HR信息*/
    Map<String,Object> updateHr(Integer hr_id);

    /**查询HR信息*/
    CompanyHr selectHr(Integer hr_id);

    /**查询HR的企业资质认证*/
    CompanyInfo selectEnterpriseVerification(Integer hr_id);

    /**查询HR的个人资质认证*/
    CompanyHr selectPersonalVerification(Integer hr_id);

    /**修改HR的个人资质认证*/
    Map<String,Object> updatePersonalVerification(Integer hr_id);
}
