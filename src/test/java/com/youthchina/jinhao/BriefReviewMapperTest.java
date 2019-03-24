package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.BriefReviewMapper;
import com.youthchina.domain.jinhao.communityQA.BriefReview;
import com.youthchina.domain.jinhao.communityQA.Comment;
import com.youthchina.domain.jinhao.communityQA.Evaluate;
import com.youthchina.domain.zhongyang.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

/*



@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:review.xml"})
public class BriefReviewMapperTest {
    @Resource
    BriefReviewMapper briefReviewMapper;

    @Test
    public void getUsersReview() {
        List<BriefReview> briefReviews = briefReviewMapper.getUsersReview(1);
        Assert.assertEquals(1, briefReviews.size());
        for (BriefReview briefReview : briefReviews) {
            if (briefReview.getReview_id() != 1) {
                Assert.fail();
            }
        }
    }

    @Test
    public void add() {
        BriefReview briefReview = new BriefReview();
        briefReview.setRela_id(1);
        briefReview.setRela_type(2);
        briefReview.setReview_content("{\"braftEditorRaw\": {\"blocks\": [{\"key\": \"dtj4a\", \"text\": \"dsfgdfgdfg\", \"type\": \"unstyled\", \"depth\": 0, \"inlineStyleRanges\": [], \"entityRanges\": [], \"data\": {}}], \"entityMap\": {}},\"previewText\": \"sdfgdfgdfg\", \"resourceList\": []}");
        briefReview.setReview_time(new Timestamp(System.currentTimeMillis()));
        briefReviewMapper.add(briefReview);
        BriefReview briefReview1 = briefReviewMapper.simplyGetReview(briefReview.getReview_id());
        Assert.assertNotNull(briefReview1);
        Assert.assertEquals(briefReview.getReview_id(), briefReview1.getReview_id());
        briefReviewMapper.createReviewMap(briefReview.getReview_id(), 1, 2, 1);
        List<BriefReview> briefReviewList = briefReviewMapper.getUsersReview(1);
        Assert.assertEquals(2, briefReviewList.size());
        for (BriefReview briefReview2 : briefReviewList) {
            if (briefReview2.getReview_id() != 1 && briefReview2.getReview_id() != briefReview.getReview_id()) {
                Assert.fail();
            }
        }
    }

    @Test
    public void get() {
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        Assert.assertEquals(Integer.valueOf(1), briefReview.getReview_id());
        BriefReview briefReview1 = briefReviewMapper.get(1);
        Assert.assertEquals(3, briefReview1.getComments().size());
        List<Integer> id = new LinkedList<>();
        id.add(1);
        id.add(2);
        id.add(3);
        id.add(4);
        List<BriefReview> briefReviewList = briefReviewMapper.getList(id);
        Assert.assertEquals(3, briefReviewList.size());
    }

    @Test
    public void update() {
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        briefReview.setReview_content("asd");
        briefReviewMapper.update(briefReview);
        BriefReview briefReview1 = briefReviewMapper.simplyGetReview(1);
        Assert.assertEquals("asd", briefReview1.getReview_content());
    }

    @Test
    public void delete() {
        briefReviewMapper.delete(1);
        BriefReview briefReview = briefReviewMapper.simplyGetReview(1);
        Assert.assertNull(briefReview);
    }

    @Test
    public void countReviewAgreement() {
        int count = briefReviewMapper.countReviewAgreement(1);
        Assert.assertEquals(2, count);
    }

    @Test
    public void getUserUpvoteReview() {
        List<BriefReview> briefReviews = briefReviewMapper.getUserUpvoteReview(1);
        Assert.assertEquals(1, briefReviews.size());
        for (BriefReview briefReview : briefReviews) {
            if (briefReview.getReview_id() != 1) {
                Assert.fail();
            }
        }
    }

    @Test
    public void getReviewEvaluation() {
        Evaluate evaluate = briefReviewMapper.getEvaluation(3);
        Assert.assertEquals(Integer.valueOf(2), evaluate.getEvaluate_type());
    }

    @Test
    public void addReviewEvaluation() {
        Evaluate evaluate = new Evaluate();
        evaluate.setEvaluate_type(1);
        evaluate.setUser_id(1);
        briefReviewMapper.addReviewEvaluation(evaluate);
        Evaluate evaluate1 = briefReviewMapper.getEvaluation(evaluate.getEvaluate_id());
        Assert.assertEquals(evaluate.getEvaluate_id(), evaluate1.getEvaluate_id());
        Evaluate evaluate2 = briefReviewMapper.checkEvaluateStatus(1, 2);
        Assert.assertNull(evaluate2);
        briefReviewMapper.createEvaluationReviewMap(evaluate.getEvaluate_id(), 2);
        List<BriefReview> briefReviews = briefReviewMapper.getUserUpvoteReview(1);
        Assert.assertEquals(2, briefReviews.size());
        for (BriefReview briefReview : briefReviews) {
            if (briefReview.getReview_id() != 1 && briefReview.getReview_id() != 2) {
                Assert.fail();
            }
        }
        Evaluate evaluate3 = briefReviewMapper.checkEvaluateStatus(1, 2);
        Assert.assertNotNull(evaluate3);
    }

    @Test
    public void updateEvaluation() {
        Evaluate evaluate = briefReviewMapper.getEvaluation(1);
        evaluate.setEvaluate_type(2);
        briefReviewMapper.updateEvaluation(evaluate);
        evaluate = briefReviewMapper.getEvaluation(1);
        Assert.assertEquals(Integer.valueOf(2), evaluate.getEvaluate_type());
    }

    @Test
    public void getComment() {
        Comment comment = briefReviewMapper.simplyGetComment(1);
        Assert.assertEquals("B", comment.getComment_content());
        Comment comment1 = briefReviewMapper.getComment(1);
        User user = comment1.getUser();
        Assert.assertEquals(Integer.valueOf(1), user.getId());
        List<Comment> comments = briefReviewMapper.getCommentsByReviewId(1);
        Assert.assertEquals(3, comments.size());
    }

    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setComment_content("aaaa");
        comment.setUser_id(1);
        briefReviewMapper.addComment(comment);
        Comment comment1 = briefReviewMapper.getComment(comment.getComment_id());
        Assert.assertEquals(comment.getComment_id(), comment1.getComment_id());
        briefReviewMapper.createCommentReviewMap(comment.getComment_id(), 1, 2);
        BriefReview briefReview = briefReviewMapper.get(2);
        List<Comment> comments = briefReview.getComments();
        Assert.assertEquals(1, comments.size());
    }

    @Test
    public void countCommentAgreement() {
        Integer count = briefReviewMapper.countCommentAgreement(1);
        Assert.assertEquals(Integer.valueOf(2), count);
    }

    @Test
    public void deleteComment() {
        briefReviewMapper.deleteAllCommentEvaluationByCommentId(1);
        Assert.assertEquals(Integer.valueOf(0), briefReviewMapper.countCommentAgreement(1));
        briefReviewMapper.deleteComment(1);
        Comment comment = briefReviewMapper.getComment(1);
        Assert.assertNull(comment);
    }

    @Test
    public void updateComment() {
        Comment comment = briefReviewMapper.getComment(1);
        comment.setComment_content("12345");
        briefReviewMapper.updateComment(comment);
        comment = briefReviewMapper.getComment(1);
        Assert.assertEquals("12345", comment.getComment_content());
    }

    @Test
    public void getCommentEvaluation() {
        Evaluate evaluate = briefReviewMapper.getCommentEvaluation(1);
        Assert.assertEquals(Integer.valueOf(1), evaluate.getEvaluate_id());
    }

    @Test
    public void addCommentEvaluation() {
        Evaluate evaluate = new Evaluate();
        evaluate.setEvaluate_type(1);
        evaluate.setUser_id(4);
        briefReviewMapper.addCommentEvaluation(evaluate);
        Evaluate evaluate1 = briefReviewMapper.getCommentEvaluation(evaluate.getEvaluate_id());
        Assert.assertEquals(evaluate1.getEvaluate_id(), evaluate.getEvaluate_id());
        Integer o = briefReviewMapper.countCommentAgreement(1);
        Assert.assertNull(briefReviewMapper.checkCommentEvaluationStatus(4, 1));
        briefReviewMapper.createEvaluationCommentMap(evaluate.getEvaluate_id(), 1);
        Assert.assertNotNull(briefReviewMapper.checkCommentEvaluationStatus(4, 1));
        Assert.assertEquals(Integer.valueOf(o + 1), briefReviewMapper.countCommentAgreement(1));
    }

    @Test
    public void updateCommentEvaluation() {
        Evaluate evaluate = briefReviewMapper.getCommentEvaluation(1);
        evaluate.setEvaluate_type(2);
        briefReviewMapper.updateCommentEvaluation(evaluate);
        Assert.assertEquals(Integer.valueOf(1), briefReviewMapper.countCommentAgreement(1));
    }
}
 */