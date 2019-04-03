package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface CompanyMapper {

    Company selectCompany(Integer id);

    List<Company> selectCompanyByIdList(List<Integer> id);

    Integer deleteCompany(Integer id);

    Integer updateCompany(Company company);

    Integer insertCompany(Company entity);

    Industry selectIndustry(Integer id);

    List<Industry> selectIndustryByIdList(List<Integer> ids);

    Integer deleteIndustry(Integer id);

    Integer updateIndustry(Industry industry);

    Integer insertIndustry(Industry entity);

    CompanyVerification selectCompanyVerification(Integer id);

    List<CompanyVerification> selectCompanyVerificationByIdList(List<Integer> id);

    Integer deleteCompanyVerificationById(Integer id);

    Integer updateCompanyVerification(CompanyVerification companyVerification);

    Integer insertCompanyVerification(CompanyVerification entity);

    Integer deleteCompanyInd(Integer id);

    Integer deleteCompanyEmployee(Integer id);

    Integer deleteCompanyVerificationByComId(Integer id);

    Integer deleteCompanyPhoto(Integer id);

    Integer deleteCompanyEvaluate(Integer id);

    Integer deleteStudentComCollection(Integer id);

    Integer deleteCompanyLogo(Integer id);

    Integer insertCompanyInd(@Param("companyId") Integer companyId, @Param("industries") List<Industry> industries);

    Integer insertCompanyLogo(@Param("companyId") Integer companyId, @Param("logos") List<Logo> logos);

    Integer insertCompanyPhoto(@Param("companyId") Integer companyId, @Param("photoList") List<CompanyPhoto> photoList);

    List<Company> selectCompanyByName(String comName);

    List<Company> selectAllCompany();
}

