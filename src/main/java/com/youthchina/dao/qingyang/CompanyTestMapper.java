package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.CompanyVerification_qingyang;
import com.youthchina.domain.qingyang.Company_qingyang;
import com.youthchina.domain.qingyang.Hr_qingyang;
import com.youthchina.domain.qingyang.Industry_qingyang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface CompanyTestMapper {

    Company_qingyang selectCompany(Integer id);

    List<Company_qingyang> selectCompanyByIdList(List<Integer> ids);

    Integer deleteCompany(Integer id);

    Integer updateCompany(Company_qingyang company_qingyang);

    Integer insertCompany(Company_qingyang entity);

    Industry_qingyang selectIndustry(Integer id);

    List<Industry_qingyang> selectIndustryByIdList(List<Integer> ids);

    Integer deleteIndustry(Integer id);

    Integer updateIndustry(Industry_qingyang industry_qingyang);

    Integer insertIndustry(Industry_qingyang entity);
    
    CompanyVerification_qingyang selectCompanyVerfication(Integer id);

    List<CompanyVerification_qingyang> selectCompanyVerficationByIdList(List<Integer> ids);

    Integer deleteCompanyVerfication(Integer id);

    Integer updateCompanyVerification(CompanyVerification_qingyang companyVerification_qingyang);

    Integer insertCompanyVerfication(CompanyVerification_qingyang entity);

    Hr_qingyang selectHr(Integer id);

    List<Hr_qingyang> selectHrByIdList(List<Integer> ids);

    Integer deleteHr(Integer id);

    Integer updateHr(Hr_qingyang hr_qingyang);

    Integer insertHr(Hr_qingyang entity);
}

