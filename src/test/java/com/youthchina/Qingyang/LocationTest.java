package com.youthchina.Qingyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.qingyang.LocationMapper;
import com.youthchina.domain.Qinghong.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-17
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml", "classpath:location.xml"})
public class LocationTest {
    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testGetLocationChn() {
        List<Location> locationList = locationMapper.getChildrenChn(110000);
        Assert.assertEquals(6, locationList.size());
    }

    @Test
    public void testGetLocationUSA() {
        List<Location> locationList = locationMapper.getChildrenUSA(10);
        Assert.assertEquals(5, locationList.size());
    }

    @Test
    public void testGetUSALocationByLocationList() {
        List<Location> input = new ArrayList<>();
        Location l1 = new Location();
        Location l2 = new Location();
        l1.setRegion_num(920001);
        l2.setRegion_num(920002);
        input.add(l1);
        input.add(l2);
        List<Location> locationList = locationMapper.getUSALocationByLocationList(input);
        Assert.assertEquals(2, locationList.size());
    }


}
