package com.youthchina.service.recommendation;

import com.youthchina.domain.jinhao.Salary;

import java.sql.Date;
import java.util.List;

public interface UserTagService {
    void addPreferCity(int regionNum, int stuId);
    void deletePreferCity(int regionNum, int stuId);
    List<Integer> getPreferCities(int stuId);

    void addPreferIndustry(int industryCode, int stuId);
    void deletePreferIndustry(int industryCode, int stuId);
    List<Integer> getPreferIndustries(int stuId);

    void addPreferProf(int profCode, Date preAvailTime, int stuId);
    void deletePreferProf(int proCode, int stuId);
    List<Integer> getPreferProf(int stuId);

    void addPreferJobType(int jobType, int stuId, int profCode);
    void deletePreferJobType(int jobType, int stuId, int profCode);
    List<Integer> getPreferJobTypes(int stuId, int profCode);

    void addPreferSalary(int salaFloor, int salaCap, int profCode, int stuId);
    void deletePreferSalary(int stuId, int profCode);
    List<Salary> getPreferSalary(int stuId, int profCode);
}
