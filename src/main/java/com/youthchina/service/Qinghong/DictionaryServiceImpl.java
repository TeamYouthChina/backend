package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.DictionaryMapper;
import com.youthchina.domain.Qinghong.Entry;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: youthchina
 * @description: 字典表service实现
 * @author: Qinghong Wang
 * @create: 2019-04-10 14:43
 **/
@Service
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Override
    public List<Entry> getLocations(Integer target, Integer id) throws NotFoundException {
        List<Entry> locations=dictionaryMapper.getLocation(target,id);
        return locations;
    }

    @Override
    public List<Entry> getMajorByAbbre() {
        return dictionaryMapper.getMajorByAbbre();
    }

    @Override
    public List<Entry> getMajorByFull() {
        return dictionaryMapper.getMajorByFull();
    }

    @Override
    public List<Entry> getDegree() {
        return dictionaryMapper.getDegree();
    }

    @Override
    public List<Entry> getDiploma() {
        return dictionaryMapper.getDiploma();
    }

    @Override
    public List<Entry> getAdvantageSkill() {
        return dictionaryMapper.getAdvantageSkill();
    }

    @Override
    public List<Entry> getIndustry() {
        return dictionaryMapper.getIndustry();
    }

    @Override
    public List<Entry> getJob() {
        return dictionaryMapper.getJob();
    }

    @Override
    public List<Entry> getCompanyScale() {
        return dictionaryMapper.getCompanyScale();
    }

    @Override
    public List<Entry> getCompanyNature() {
        return dictionaryMapper.getCompanyNature();
    }

    @Override
    public List<Entry> getCountry() {
        return dictionaryMapper.getCountry();
    }

    @Override
    public List<Entry> getChinaRegionByAbbre() {
        return dictionaryMapper.getChinaRegionByAbbre();
    }

    @Override
    public List<Entry> getChinaRegionByFull() {
        return dictionaryMapper.getChinaRegionByFull();
    }

    @Override
    public List<Entry> getUSAStateByAbbre() {
        return dictionaryMapper.getUSAStateByAbbre();
    }

    @Override
    public List<Entry> getUSAStateByFull() {
        return dictionaryMapper.getUSAStateByFull();
    }

    @Override
    public List<Entry> getUSARegion() {
        return dictionaryMapper.getUSARegion();
    }

    @Override
    public List<Entry> getCHNUniversity() {
        return dictionaryMapper.getCHNUniversity();
    }

    @Override
    public List<Entry> getUSAUniversity() {
        return dictionaryMapper.getUSAUniversity();
    }

    @Override
    public List<Entry> getGBRUniversity() {
        return dictionaryMapper.getGBRUniversity();
    }

    @Override
    public List<Entry> getCANUniversity() {
        return dictionaryMapper.getCANUniversity();
    }

    @Override
    public List<Entry> getTIMESRank() {
        return dictionaryMapper.getTIMESRank();
    }

    @Override
    public List<Entry> getUSNEWSRank() {
        return dictionaryMapper.getUSNEWSRank();
    }

    @Override
    public List<Entry> getQSRank() {
        return dictionaryMapper.getQSRank();
    }

    @Override
    public List<Entry> getTopCompany() {
        return dictionaryMapper.getTopCompany();
    }

    @Override
    public List<Entry> getUniversity() {
        return dictionaryMapper.getUniversity();
    }
}
