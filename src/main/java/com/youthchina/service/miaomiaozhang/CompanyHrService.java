package com.youthchina.service.miaomiaozhang;

import com.youthchina.domain.miaomiaozhang.CompanyHr;

import java.util.Map;

/**
 * CompanyHrService class
 *
 * @author miaomiaozhang
 * @date 2018/11/13
 */
public interface CompanyHrService {
    Map<String,Object> addHr(CompanyHr companyHr);

    Map<String,Object> updateHr(String hr_id);


}
