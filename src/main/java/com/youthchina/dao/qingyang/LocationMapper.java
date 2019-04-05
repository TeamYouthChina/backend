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

    Location getChnLocation(Integer regionNum);

    List<Location> getChildrenChn(Integer parentId);

    List<Location> getChnLocationByLocationList(List<Location> locationList);


    Location getUSALocation(Integer zipCode);

    List<Location> getUSALocationByLocationList(List<Location> locationList);

    List<Location> getChildrenUSA(Integer stateId);

    Location getLocationViewById(Integer regionId);

    List<Location> getLocationViewByIdList(List<Integer> idList);


}
