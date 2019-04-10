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
}
