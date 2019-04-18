package com.youthchina.mapper;

import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-17
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class LocationTest {
    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testGetLocationView() {
        Location location = locationMapper.getLocationViewById(110000);
        Assert.assertEquals("北京市", location.getRegionName());
        Assert.assertEquals("CHN", location.getCountry());
        Assert.assertEquals("中国", location.getCountryName());

        location = locationMapper.getLocationViewById(994710);
        Assert.assertEquals("Berkeley", location.getRegionName());

    }

    @Test
    public void testGetLocationViewByIdList() {
        List<Integer> list = new ArrayList<>();
        list.add(110000);
        list.add(994710);
        List<Location> locationList = locationMapper.getLocationViewByIdList(list);
        Assert.assertEquals(2, locationList.size());
        Assert.assertEquals("北京市", locationList.get(0).getRegionName());
        Assert.assertEquals("CHN", locationList.get(0).getCountry());
        Assert.assertEquals("中国", locationList.get(0).getCountryName());
        Assert.assertEquals("Berkeley", locationList.get(1).getRegionName());
        Assert.assertEquals("USA", locationList.get(1).getCountry());
        Assert.assertEquals("美国", locationList.get(1).getCountryName());
    }




}
