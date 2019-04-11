package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.Entry;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface DictionaryService {
    List<Entry> getLocations(Integer target,Integer id) throws NotFoundException;

    List<Entry> getMajorByAbbre();
    List<Entry> getMajorByFull();
    List<Entry> getDegree();
    List<Entry> getDiploma();
    List<Entry> getAdvantageSkill();
    List<Entry> getIndustry();
    List<Entry> getJob();
    List<Entry> getCompanyScale();
    List<Entry> getCompanyNature();
    List<Entry> getCountry();
    List<Entry> getChinaRegionByAbbre();
    List<Entry> getChinaRegionByFull();
    List<Entry> getUSAStateByAbbre();
    List<Entry> getUSAStateByFull();
    List<Entry> getUSARegion();
    List<Entry> getCHNUniversity();
    List<Entry> getUSAUniversity();
    List<Entry> getGBRUniversity();
    List<Entry> getCANUniversity();
    List<Entry> getTIMESRank();
    List<Entry> getUSNEWSRank();
    List<Entry> getQSRank();
    List<Entry> getTopCompany();
    List<Entry> getUniversity();
}
