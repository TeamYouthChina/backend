package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-17
 **/
@Service
public class LocationService {

    @Resource
    LocationMapper locationMapper;

    List<Location> getChildren(String parent){
        Integer parentId = Integer.valueOf(parent);
        return locationMapper.getChildrenChn(parentId);
    }

    public Location getLocation(String nation_code,Integer region_num){
        if(nation_code.equals("USA")){
            Location location=locationMapper.getUSALocation(region_num);
            location.setNation_code(nation_code);
            return location;
        }else {
            Location location=locationMapper.getChnLocation(region_num);
            location.setNation_code(nation_code);
            return location;
        }

    }

}
