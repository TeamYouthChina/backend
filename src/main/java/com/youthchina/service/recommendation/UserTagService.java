package com.youthchina.service.recommendation;

import java.sql.Date;
import java.util.List;

public interface UserTagService {
    void addPreferCity(int regionNum, int stuId);
    void deletePreferCity(int regionNum, int stuId);
    List<Integer> getPreferCities(int stuId);

    void addPreferIndustry(int industryCode, int stuId);
    void deletePreferIndustry(int industryCode, int stuId);
    List<Integer> getPreferIndustries(int stuId);

    void addPreferJobType(int jobType, int stuId, int preProfId);
    void deletePreferJobType(int jobType, int stuId, int preProfId);
    List<Integer> getPreferJobTypes(int stuId);

    void addPreferProf(int profCode, Date preAvailTime, int stuId);
    void deletePreferProf(int proCode, int stuId);
    List<Integer> getPreferProf(int stuId);

    void addPreferSalary(int salaFloor, int salaCap, int preProfId, int stuId);


}
