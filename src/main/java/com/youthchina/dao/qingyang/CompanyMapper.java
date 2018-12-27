package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyVerification_qingyang;
import com.youthchina.domain.qingyang.Industry_qingyang;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface CompanyMapper {

    Company selectCompany(Integer id);

    List<Company> selectCompanyByIdList(List<Integer> id);

    Integer deleteCompany(Integer id);

    Integer updateCompany(Company company_qingyang);

    Integer insertCompany(Company entity);

    Industry_qingyang selectIndustry(Integer id);

    List<Industry_qingyang> selectIndustryByIdList(List<Integer> id);

    Integer deleteIndustry();

    Integer updateIndustry(Industry_qingyang industry_qingyang);

    Integer insertIndustry(Industry_qingyang entity);
    
    CompanyVerification_qingyang selectCompanyVerfication(Integer id);

    List<CompanyVerification_qingyang> selectCompanyVerficationByIdList(List<Integer> id);

    Integer deleteCompanyVerfication(Integer id);

    Integer updateCompanyVerification(CompanyVerification_qingyang companyVerification_qingyang);

    Integer insertCompanyVerfication(CompanyVerification_qingyang entity);
}

