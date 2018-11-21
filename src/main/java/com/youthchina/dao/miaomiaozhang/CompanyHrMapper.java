package com.youthchina.dao.miaomiaozhang;

import com.youthchina.domain.miaomiaozhang.CompanyHr;
import com.youthchina.domain.miaomiaozhang.CompanyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * CompanyHrMapper
 *
 * @author miaomiaozhang
 * @date 2018/11/13
 */
@Mapper
@Component
public interface CompanyHrMapper {
    /**add hr*/
    Integer insertHr(CompanyHr companyHr);

    /**update hr information*/
    Integer updateHr(Integer hr_id);

    /**select hr information*/
    CompanyHr selectHr(Integer hr_id);

    /**select Enterprise Verification*/
    CompanyInfo selectEnterpriseVerification(Integer hr_id);

    /**update Personal Verification*/
    Integer updatePersonalVerification(Integer hr_id);

    /**select Personal Verification*/
    CompanyHr selectPersonalVerification(Integer hr_id);
}
