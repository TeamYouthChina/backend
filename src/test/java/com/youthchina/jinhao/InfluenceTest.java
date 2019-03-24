package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.InfluenceMapper;
import com.youthchina.domain.Qinghong.EducationInfo;
import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.Qinghong.Work;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComReplyEvaluate;
import com.youthchina.domain.tianjian.PersonInfluence;
import com.youthchina.service.tianjian.CaculatePersonInfluencePoint;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml", "classpath:answers.xml", "classpath:comments.xml", "classpath:discuss.xml", "classpath:videos.xml",
        "classpath:rank.xml"})
public class InfluenceTest {
    @Autowired
    InfluenceMapper influenceMapper;

    @Autowired
    CaculatePersonInfluencePoint caculatePersonInfluencePoint;

    @Test
    public void getInfluenceById() {
        Influence influence = influenceMapper.getInfluenceByUserId(1);
        Assert.assertNotNull(influence);
        Student student = influence.getStudent();
        Assert.assertNotNull(student);
        List<ComFriendRelation> comFriendRelations = influence.getComFriendRelations();
        Assert.assertEquals(1, comFriendRelations.size());
        List<Evaluate> evaluates = influence.getEvaluates();
        Assert.assertEquals(2, evaluates.size());
        for (Evaluate evaluate : evaluates) {
            if (evaluate.getEvaluate_id() != 1 && evaluate.getEvaluate_id() != 3) {
                Assert.fail();
            }
        }
        List<CommentEvaluate> commentEvaluates = influence.getCommentEvaluates();
        Assert.assertEquals(3, commentEvaluates.size());
        for (CommentEvaluate commentEvaluate : commentEvaluates) {
            if (commentEvaluate.getEvaluate_id() != 1 && commentEvaluate.getEvaluate_id() != 2 &&
                    commentEvaluate.getEvaluate_id() != 4) {
                Assert.fail();
            }
        }
        List<DiscussEvaluate> discussEvaluates = influence.getDiscussEvaluates();
        Assert.assertEquals(8, discussEvaluates.size());
        List<VideoEvaluate> videoEvaluates = influence.getVideoEvaluates();
        Assert.assertEquals(0, videoEvaluates.size());
        List<ComReplyEvaluate> comReplyEvaluates = influence.getComReplyEvaluates();
        Assert.assertEquals(0, comReplyEvaluates.size());
    }

    @Test
    public void getInteraction() {
        Influence influence = influenceMapper.getInteraction(1, 2);
        List<Evaluate> evaluates = influence.getEvaluates();
        Assert.assertEquals(1, evaluates.size());
        for (Evaluate evaluate : evaluates) {
            if (evaluate.getEvaluate_id() != 5) {
                Assert.fail();
            }
        }
        List<CommentEvaluate> commentEvaluates = influence.getCommentEvaluates();
        Assert.assertEquals(0, commentEvaluates.size());
        List<DiscussEvaluate> discussEvaluates = influence.getDiscussEvaluates();
        Assert.assertEquals(1, discussEvaluates.size());
        for (DiscussEvaluate discussEvaluate : discussEvaluates) {
            if (discussEvaluate.getEvaluate_id() != 11) {
                Assert.fail();
            }
        }
        List<VideoEvaluate> videoEvaluates = influence.getVideoEvaluates();
        Assert.assertEquals(1, videoEvaluates.size());
        for (VideoEvaluate videoEvaluate : videoEvaluates) {
            if (videoEvaluate.getEvaluate_id() != 1) {
                Assert.fail();
            }
        }
        List<ComReplyEvaluate> comReplyEvaluates = influence.getComReplyEvaluates();
        Assert.assertEquals(1, comReplyEvaluates.size());
        for (ComReplyEvaluate comReplyEvaluate : comReplyEvaluates) {
            if (comReplyEvaluate.getEvaluate_id() != 1) {
                Assert.fail();
            }
        }
        List<QuestionAnswer> questionAnswers = influence.getQuestionAnswers();
        Assert.assertEquals(0, questionAnswers.size());
        List<Comment> comments = influence.getComments();
        Assert.assertEquals(0, comments.size());
        List<VideoComment> videoComments = influence.getVideoComments();
        Assert.assertEquals(2, videoComments.size());
        for (VideoComment videoComment : videoComments) {
            if (videoComment.getComment_id() != 1 && videoComment.getComment_id() != 3) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getBestEducation() {
        EducationInfo educationInfo1 = new EducationInfo();
        EducationInfo educationInfo2 = new EducationInfo();
        EducationInfo educationInfo3 = new EducationInfo();
        EducationInfo educationInfo4 = new EducationInfo();
        EducationInfo educationInfo5 = new EducationInfo();
        EducationInfo educationInfo6 = new EducationInfo();
        educationInfo1.setEdu_school("清华大学");
        educationInfo2.setEdu_school("北京大学");
        educationInfo3.setEdu_school("alili");
        educationInfo4.setEdu_school("弟弟");
        educationInfo5.setEdu_school("gege");
        educationInfo6.setEdu_school("激光管");
        List<EducationInfo> educationInfos = new LinkedList<>();
        educationInfos.add(educationInfo1);
        educationInfos.add(educationInfo2);
        educationInfos.add(educationInfo3);
        Integer rank = influenceMapper.getBestEducation(educationInfos);
        Assert.assertEquals(rank, Integer.valueOf(1));
        List<EducationInfo> new_educationInfos = new LinkedList<>();
        new_educationInfos.add(educationInfo3);
        new_educationInfos.add(educationInfo4);
        new_educationInfos.add(educationInfo5);
        new_educationInfos.add(educationInfo6);
        Integer newrank = influenceMapper.getBestEducation(new_educationInfos);
        Assert.assertEquals(Integer.valueOf(3), newrank);
        Influence influence = influenceMapper.getInfluenceByUserId(1);
        List<EducationInfo> educationInfos1 = influence.getStudent().getEducationInfos();
        Integer universityRank = influenceMapper.getBestEducation(educationInfos1);
        System.out.println(universityRank);
    }

    @Test
    public void getBestWork() {
        Work work1 = new Work();
        Work work2 = new Work();
        Work work3 = new Work();
        Work work4 = new Work();
        work1.setWork_company("Google");
        work2.setWork_company("Facebook");
        work3.setWork_company("腾讯");
        work4.setWork_company("华为");
        List<Work> works = new LinkedList<>();
        works.add(work3);
        works.add(work4);
        works.add(work1);
        works.add(work2);
        Integer bestRank = influenceMapper.getBestWork(works);
        Assert.assertEquals(Integer.valueOf(1), bestRank);
    }

    @Test
    public void calculatePoints() {
        Influence influence = influenceMapper.getInfluenceByUserId(1);
        Student student = influence.getStudent();
        PersonInfluence personInfluence = caculatePersonInfluencePoint.caculatePersonInfluencePoint(1);
        System.out.println(personInfluence.getPers_friend_count());
        System.out.println(personInfluence.getPers_friend_quality());
        System.out.println(personInfluence.getPers_ident_verify());
        System.out.println(personInfluence.getPers_interaction());
        System.out.println(personInfluence.getPers_like_count());
        System.out.println(personInfluence.getPers_posi_evaluate());
        System.out.println(personInfluence.getPers_profile());
        System.out.println(personInfluence.getPers_university());
        System.out.println(personInfluence.getPers_work());
        System.out.println(personInfluence.getPers_total());
    }
}
