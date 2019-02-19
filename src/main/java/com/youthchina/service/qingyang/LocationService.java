package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
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
        return locationMapper.getChildren(parentId);
    }

}
