package com.youthchina.service.zhongyang;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.tianjian.ComMediaDocument;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.tianjian.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by zhongyangwu on 2/14/19.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:media.xml"})
public class StaticFileServiceTest {

    StaticFileService staticFileService;

    StaticFileSystemMapper staticFileSystemMapper;

    FileNameGenerate fileNameGenerate;

    SnowFlakeIdGenerate snowFlakeIdGenerate;

    AliCloudFileStorageService aliCloudFileStorageService;

    @Before
    public void setup() {
        this.staticFileSystemMapper = mock(StaticFileSystemMapper.class);
        this.fileNameGenerate = mock(FileNameGenerate.class);
        this.snowFlakeIdGenerate = mock(SnowFlakeIdGenerate.class);
        this.aliCloudFileStorageService = mock(AliCloudFileStorageService.class);

        this.staticFileService = new StaticFileService(this.staticFileSystemMapper, this.fileNameGenerate, new FileStorageService[]{this.aliCloudFileStorageService}, this.snowFlakeIdGenerate);

    }

    @Test
    public void testUpload() throws IOException {
        when(snowFlakeIdGenerate.nextId()).thenReturn(1234494L);
        when(fileNameGenerate.generateFileName()).thenReturn("Test");
        when(staticFileSystemMapper.saveFileInfo(any())).thenReturn(1);
        staticFileService.saveFile(new File("ii.txt"), new User().getId());
    }

    @Test
    public void testDownload() throws MalformedURLException {
        ComMediaDocument comMediaDocument = new ComMediaDocument();
        comMediaDocument.setDocu_server_ali_id("2856327168068161536");
        comMediaDocument.setDocu_local_id("2856306669745344512");
        when(aliCloudFileStorageService.downloadFile("2856327168068161536")).thenReturn(new URL("http://alicoud.oss.com/te"));
        when(staticFileSystemMapper.getFileInfo("2856306669745344512")).thenReturn(comMediaDocument);
        URL url = staticFileService.getFileUrl("2856306669745344512", "China");
        Assert.assertEquals(url.getHost(),"alicoud.oss.com");
    }

}