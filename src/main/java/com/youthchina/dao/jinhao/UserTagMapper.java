package com.youthchina.dao.jinhao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@Mapper
public interface UserTagMapper {
    void addPreferCity(@Param("regionNum") Integer regionNum, @Param("stuId") Integer stuId);
    void deletePreferCity(@Param("regionNum") Integer regionNum, @Param("stuId") Integer stuId);
    List<Integer> getPreferCities(Integer stuId);

    void addPreferIndustry(@Param("industryCode") Integer industryCode, @Param("stuId") Integer stuId);
    void deletePreferIndustry(@Param("industryCode") Integer industryCode, @Param("stuId") Integer stuId);
    List<Integer> getPreferIndustries(Integer stuId);

    void addPreferProf(@Param("profCode") Integer profCode, @Param("preAvailTime") Date preAvailTime, @Param("stuId") Integer stuId);
    void deletePreferProf(@Param("profCode") Integer profCode, @Param("stuId") Integer stuId);
    List<Integer> getPreferProf(Integer stuId);
    Integer getPreProId(@Param("stuId") Integer stuId, @Param("preProfCode") Integer preProfCode);

}
