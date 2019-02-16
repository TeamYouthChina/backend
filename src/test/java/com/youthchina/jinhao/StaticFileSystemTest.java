package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.service.tianjian.StaticFileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:mediadocument.xml"})

public class StaticFileSystemTest {

    @Autowired
    StaticFileService staticFileService;

    @Test
    public void testGetFileSize() throws ParseException, IOException {


        Timestamp startTime = new Timestamp(100,06,01,00,00,00,00);
        Timestamp endTime = new Timestamp(115,06,00,00,00,00,00);
        Float fileSize = staticFileService.getFileSizeOfUserUpoloading(1,startTime,endTime);
        Float f = new Float(214);
        Assert.assertEquals(f , fileSize);
    }
}
