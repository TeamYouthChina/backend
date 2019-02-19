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

import java.util.List;

/**
 * @author: Qingyang Zhao
 * @create: 2019-02-17
 **/


@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:company.xml","classpath:location.xml"})
public class LocationTest {
    @Autowired
    private LocationMapper locationMapper;

    @Test
    public void testGetLocationChn() {
        List<Location> locationList = locationMapper.getChildrenChn(110000);
        Assert.assertEquals(6, locationList.size());
    }
}
