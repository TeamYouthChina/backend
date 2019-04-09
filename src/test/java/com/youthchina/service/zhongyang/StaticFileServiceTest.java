package com.youthchina.service.zhongyang;

/**
 * Created by zhongyangwu on 2/14/19.
 */

/*
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
        ClassPathResource classPathResource = new ClassPathResource("rank.xml");
        staticFileService.saveFile(classPathResource, new User().getId());
    }

//    @Test
//    public void testDownload() throws MalformedURLException {
//        ComMediaDocument comMediaDocument = new ComMediaDocument();
//        comMediaDocument.setDocu_server_ali_id("2856327168068161536");
//        comMediaDocument.setDocu_local_id("2856306669745344512");
//        when(aliCloudFileStorageService.downloadFile("2856327168068161536")).thenReturn(new URL("http://alicoud.oss.com/te"));
//        when(staticFileSystemMapper.getFileInfo("2856306669745344512")).thenReturn(comMediaDocument);
//        URL url = staticFileService.getFileUrl("2856306669745344512", "China");
//        Assert.assertEquals(url.getHost(), "alicoud.oss.com");
//    }

} */