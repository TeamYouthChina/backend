package com.youthchina.Qinghong;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.Qinghong.DictionaryMapper;
import com.youthchina.domain.Qinghong.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * @program: youthchina
 * @description: 字典表测试
 * @author: Qinghong Wang
 * @create: 2019-04-10 14:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:test.xml"})
@WebAppConfiguration
public class DictionaryTest {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Test
    public void getLocation(){
        List<Entry> locations=dictionaryMapper.getLocation(2,4400000);
        System.out.print(locations);
    }

}
