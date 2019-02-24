package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.tianjian.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.io.IOException;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:users.xml"})
@WebAppConfiguration
public class StaticFileSystemTest {
    @Autowired
   private StaticFileSystemMapper staticFileSystemMapper;
    @Autowired
    private FileNameGenerate fileNameGenerate;
    @Autowired
    private AliCloudFileStorageService aliCloudFileStorageService;

    @Autowired
    private SnowFlakeIdGenerate snowFlakeIdGenerate;
   @Test
    public void testUploadFile() throws IOException {
       FileStorageService[] fileStorageService = {aliCloudFileStorageService};
       StaticFileService staticFileService = new StaticFileService(staticFileSystemMapper,fileNameGenerate,fileStorageService,snowFlakeIdGenerate);
      staticFileService.saveFile(new File("D:\\video.mp4"),2);
   }

    @Test
    public void testDownload() throws IOException {
        FileStorageService[] fileStorageService = {aliCloudFileStorageService};
        StaticFileService staticFileService = new StaticFileService(staticFileSystemMapper,fileNameGenerate,fileStorageService,snowFlakeIdGenerate);
        staticFileService.saveFile(new File("D:\\video.mp4"),15);
    }

    @Test
    public void testSaveInfo(){
       staticFileSystemMapper.setCloudStorageId("DOCU_SERVER_ALI_ID", "55555555555","2854956924430979072");
    }
}
