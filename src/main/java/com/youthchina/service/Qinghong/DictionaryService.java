package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.Entry;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface DictionaryService {
    List<Entry> getLocations(Integer target,Integer id) throws NotFoundException;
}
