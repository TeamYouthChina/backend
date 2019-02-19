package com.youthchina.service.zhongyang;

import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.tianjian.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        when(aliCloudFileStorageService.downloadFile("testFile")).thenReturn(new URL("http://alicoud.oss.com/te"));
        URL url = staticFileService.getFileUrl("testFile", "China");
        Assert.assertEquals(url.getHost(),"alicoud.oss.com");
    }

}