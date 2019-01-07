package com.youthchina.dao.qingyang;

import com.youthchina.domain.qingyang.Hr;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface HrMapper {

    Integer insertHr(Hr hr);

    Integer updateHr(Hr hr);

    Integer deleteHr(Integer id);

    Hr selectHrById(Integer id);

    List<Hr> selectHrByIdList(List<Integer> ids);
}


