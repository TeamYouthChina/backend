package com.youthchina;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Video;
import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.jinhao.communityQA.BriefReviewRecommendServiceImplement;
import com.youthchina.service.jinhao.communityQA.VideoRecommendServiceImplement;
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
@DatabaseSetup({"classpath:briefreview.xml", "classpath:comments.xml", "classpath:users.xml", "classpath:recombriefreview.xml"})
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
        comEssay.setEssay_title("title1");
        comEssay.setEssay_abbre("this essay describe ...");
        comEssay.setEssay_body("newbody");
        comEssay.setUser_anony(0);
        Timestamp time = new Timestamp(System.currentTimeMillis());
        comEssay.setEssay_pub_time(time);
        comEssay.setEssay_edit_time(time);
        comEssay.setIs_delete(0);
        comEssay.setUser_anony(0);
        List<Integer> lab = new ArrayList<Integer>();
        int i = essayService.addEssay(comEssay, lab, 1, 2, 5);
        System.out.println(i);
    }

    @Test
    public void testgetUserAllEssayAttention() {
        List<ComEssay> list = essayService.getAllEssayUserAttention(1);
        System.out.println(list.size());
    }

    @Test
    public void testgetVideoRecommend() {
        List<Video> list = videoRecommendServiceImplement.getVideoForYou();
        System.out.println(list.size());
    }

    @Test
    public void testgetBriefReviewRecommend() throws NotFoundException {
        List<BriefReview> list = briefReviewRecommendServiceImplement.getBriefReviewForYou();
        System.out.println(list.size());
    }

}
