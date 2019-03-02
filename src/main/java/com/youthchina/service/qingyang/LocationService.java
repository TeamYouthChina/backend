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

    public List<Location> getChildren(String parent){
        Integer parentId = Integer.valueOf(parent);
        return locationMapper.getChildrenChn(parentId);
    }

    public Location getLocation(Integer region_num){
        String regionString = "" + region_num;
        Location location;
        if(regionString.charAt(0) == '9'){
            location = locationMapper.getUSALocation(region_num);
            if(location == null) { return  new Location();}
            location.setNation_code("USA");
        } else {
            location = locationMapper.getChnLocation(region_num);
            if(location == null) { return  new Location();}
            location.setNation_code("CHN");
        }

        return location;
    }

}
