package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.CompanyEmployee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface CompanyEmployeeMapper {
    CompanyEmployee getCompanyEmployee(Integer id);
    List<CompanyEmployee> getCompanyEmployeeByIdList(List<Integer> idList);
    Integer deleteCompanyEmployee(Integer id);
    Integer updateCompanyEmployee(CompanyEmployee employee);
    Integer insertCompanyEmployee(CompanyEmployee entity);
}

