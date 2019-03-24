package com.youthchina;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.jinhao.BriefReview;
import com.youthchina.domain.jinhao.Video;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.domain.tianjian.ComRichText;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.toBeDeleted.BriefReviewRecommendServiceImplement;
import com.youthchina.service.jinhao.toBeDeleted.VideoRecommendServiceImplement;
import com.youthchina.service.tianjian.AliCloudFileStorageService;
import com.youthchina.service.tianjian.EssayServiceImpl;
import com.youthchina.service.tianjian.LocalFileManage;
import com.youthchina.service.tianjian.SnowFlakeIdGenerate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:briefreview.xml","classpath:comments.xml","classpath:users.xml","classpath:recombriefreview.xml"})
@WebAppConfiguration
public class YouthchinaApplicationTests {
    @Autowired
    SnowFlakeIdGenerate snowFlakeIdGenerate;
    @Autowired
    AliCloudFileStorageService staticFileSystemServiceImplALiCloud;
    @Autowired
    LocalFileManage localFileManage;

    @Autowired
    EssayServiceImpl essayService;

    @Autowired
    StaticFileSystemMapper staticFileSystemMapper;

    @Autowired
    VideoRecommendServiceImplement videoRecommendServiceImplement;

    @Autowired
    BriefReviewRecommendServiceImplement briefReviewRecommendServiceImplement;
   /* @Test
    public void testupLoadFile() {
       File file = new File("D:\\LocalFileStore\\video.mp4");
       long i = staticFileSystemServiceImplALiCloud.uploadFile("video",file ,"mp4",1);
       System.out.println(i);
    }

    @Test
    public void verifyFile() {
        System.out.println( staticFileSystemServiceImplALiCloud.verifyFile("nihaouip"));
    }

    @Test
    public void downloadFile() {
        System.out.println( staticFileSystemServiceImplALiCloud.downloadFile("2848699711584473088"));
    }*/


    @Test
    public void testGenerateId() {
        Long i = snowFlakeIdGenerate.nextId();
        System.out.println(i);
    }

//    @Test
//    public void testuploadFiletoLocal() {
//        File file = new File("D:\\video.mp4");
//        String path = localFileManage.uploadFileToLocal(file,".mp4");
//        System.out.println(path);
//    }

    @Test
    public void testEssayService() throws NotFoundException {
        ComEssay comEssay = new ComEssay();
        comEssay.setEssayTitle("title1");
        comEssay.setEssayAbbre("this essay describe ...");
        comEssay.setUserAnony(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssayPubTime(time);
        comEssay.setEssayEditTime(time);
        comEssay.setIsDelete(0);
        comEssay.setUserAnony(0);
        comEssay.setUserId(1);
        comEssay.setRelaType(0);
        ComRichText comRichText = new ComRichText();
        comRichText.setText_content("\"{\n" +
                "    &quot;braftEditorRaw&quot; : {\n" +
                "    &quot;entityMap&quot; : { },\n" +
                "    &quot;blocks&quot; : [ {\n" +
                "    &quot;key&quot; : &quot;dtj4a&quot;,\n" +
                "    &quot;text&quot; : &quot;&quot;,\n" +
                "    &quot;type&quot; : &quot;unstyled&quot;,\n" +
                "    &quot;depth&quot; : 0,\n" +
                "    &quot;inlineStyleRanges&quot; : [ ],\n" +
                "    &quot;entityRanges&quot; : [ ],\n" +
                "    &quot;data&quot; : { }\n" +
                "    } ]\n" +
                "    },\n" +
                "    &quot;previewText&quot; : &quot;Abbreviation of the essay 1 but42&quot;,\n" +
                "    &quot;resourceIdList&quot; : [ ]\n" +
                "    }\"");
        comRichText.setRela_type(1);
        comRichText.setRela_id(1);
        comRichText.setJson_content("\"{\n" +
                "    &quot;braftEditorRaw&quot; : {\n" +
                "    &quot;entityMap&quot; : { },\n" +
                "    &quot;blocks&quot; : [ {\n" +
                "    &quot;key&quot; : &quot;dtj4a&quot;,\n" +
                "    &quot;text&quot; : &quot;&quot;,\n" +
                "    &quot;type&quot; : &quot;unstyled&quot;,\n" +
                "    &quot;depth&quot; : 0,\n" +
                "    &quot;inlineStyleRanges&quot; : [ ],\n" +
                "    &quot;entityRanges&quot; : [ ],\n" +
                "    &quot;data&quot; : { }\n" +
                "    } ]\n" +
                "    },\n" +
                "    &quot;previewText&quot; : &quot;Abbreviation of the essay 1 but42&quot;,\n" +
                "    &quot;resourceIdList&quot; : [ ]\n" +
                "    }\"");
        comEssay.setEssayBody(comRichText);
        List<Integer> lab = new ArrayList<Integer>();
        int i = essayService.addEssay(comEssay,lab);
        System.out.println(i);
    }


    @Test
    public void testgetVideoRecommend(){
        List<Video> list = videoRecommendServiceImplement.getVideoForYou();
        System.out.println(list.size());
    }

    @Test
    public void testgetBriefReviewRecommend() throws NotFoundException {
        List<BriefReview> list = briefReviewRecommendServiceImplement.getBriefReviewForYou();
        System.out.println(list.size());
    }

}
