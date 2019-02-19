package com.youthchina.dao.qingyang;

import com.youthchina.domain.Qinghong.Location;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-17
 **/

@Mapper
@Component
public interface LocationMapper {

    List<Location> getChildren(Integer parentId);
}
