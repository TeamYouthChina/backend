package com.youthchina.dao.Qinghong;

import com.youthchina.domain.Qinghong.Entry;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DictionaryMapper {
    List<Entry> getLocation(Integer target,Integer id);
}
