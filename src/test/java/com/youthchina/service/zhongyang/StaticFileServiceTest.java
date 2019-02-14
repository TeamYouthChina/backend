package com.youthchina.service.zhongyang;

import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.service.tianjian.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
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
    public void testUpload() {
        when(snowFlakeIdGenerate.nextId()).thenReturn(1234494L);
        when(fileNameGenerate.generateFileName()).thenReturn("Test");
        when(staticFileSystemMapper.saveFileInfo(any())).thenReturn(1);

        staticFileService.saveFile(new File("ii.txt"), new User().getId());


    }

}