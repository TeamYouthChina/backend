package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.Entry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DictionaryMapper {
    List<Entry> getLocation(Integer target,Integer id);
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
    List<Entry> getAllLocation();

}
