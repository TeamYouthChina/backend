package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml", "classpath:answers.xml", "classpath:comments.xml", "classpath:discuss.xml", "classpath:videos.xml"})

public class CommunityQAMapperTest extends BaseTest {
    @Autowired
    CommunityQAMapper communityQAMapper;

    @After
    public void delete() {

    }

    // 测试能不能拿到某个问题
    @Test
    public void getQuestion() {
        Question question = communityQAMapper.getQuestion(1);
        Assert.assertEquals("第一个问题", question.getQues_title());
    }

    //测试能不能添加问题
    @Test
    public void addQuestion() {
        Question question = new Question();
        question.setQues_title("被插入的问题");
        question.setQues_abbre("这问题是新插入的");
        question.setQues_body("这是一个新插入的问题，巴拉巴拉巴拉");
        question.setQues_pub_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        question.setQues_edit_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        question.setIs_delete(0);
        question.setIs_delete_time(Timestamp.valueOf("2018-10-11 11:11:11"));
        communityQAMapper.addQuestion(question);
        Assert.assertNotNull(question.getQues_id());
        Question createQuestion = communityQAMapper.getQuestion(question.getQues_id());
        Assert.assertNotNull(createQuestion);
    }

    //测试能不能成功建立映射以及能不能通过映射拿到某个用户提出的所有问题
    @Test
    public void testGetMyQuestionAndCreateMapBetweenUserAndQuestion() {
        communityQAMapper.createMapBetweenQuestionAndUser(3, 1, 2, 3);
        List<Question> questionList = communityQAMapper.getMyQuestions(1);
        Assert.assertEquals(3, questionList.size());
        for (Question question : questionList) {
            if (question.getQues_id() != 1 && question.getQues_id() != 2 && question.getQues_id() != 3) {
                Assert.fail();
            }
        }

    }

    //测试能不能更改问题
    @Test
    public void updateQuestion() {
        Question question = communityQAMapper.getQuestion(1);
        question.setQues_title("修改问题标题");
        question.setQues_abbre("修改问题简介");
        question.setQues_body("修改问题主题");
        question.setQues_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.editQuestion(question);
        Question newQuestion = communityQAMapper.getQuestion(1);
        Assert.assertEquals("修改问题标题", newQuestion.getQues_title());
        Assert.assertEquals("修改问题简介", newQuestion.getQues_abbre());
        Assert.assertEquals("修改问题主题", newQuestion.getQues_body());
    }

    //测试删除问题
    @Test
    public void deleteQuestion() {
        Question question = communityQAMapper.getQuestion(1);
        question.setIs_delete(1);
        question.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.deleteQuestion(question);
        Question newquestion = communityQAMapper.getQuestion(1);
        Assert.assertNull(newquestion);
    }

    //测试能不能列出前十个问题
    @Test
    public void listQuestions() {
        List<Question> questionList = communityQAMapper.listQuestion();
        Assert.assertEquals(10, questionList.size());
        for (Question question : questionList) {
            Assert.assertNotNull(question);
        }
    }

    //测试能不能拿到问题的标签
    @Test
    public void getQuestionLabels() {
        List<Label> labels = communityQAMapper.listAllQuesetionLabel(1);
        Assert.assertEquals(3, labels.size());
        for (Label label : labels) {
            if (label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 5) {
                Assert.fail();
            }
        }
    }

    //测试能不能给问题添加标签
    @Test
    public void addLabels() {
        List<Integer> lab_nums = new LinkedList<>();
        lab_nums.add(2);
        lab_nums.add(1);
        lab_nums.add(3);
        communityQAMapper.addLabels(lab_nums, 2);
        List<Label> labels = communityQAMapper.listAllQuesetionLabel(2);
        Assert.assertEquals(3, labels.size());
        for (Label label : labels) {
            if (label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 2) {
                Assert.fail();
            }
        }
    }

    //测试能不能拿到某个问题关注
    @Test
    public void getQuestionAttention() {
        QuestionAttention questionAttention = communityQAMapper.getAttention(1);
        Integer integer = 1;
        Assert.assertEquals(integer, questionAttention.getUser_id());
    }

    //测试能不能成功判断用户是否关注这个问题
    @Test
    public void isEverAttentionQuestion() {
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(1, 1);
        Assert.assertNotNull(questionAttention);
        QuestionAttention questionAttention1 = communityQAMapper.isQuestionAttention(1, 3);
        Assert.assertNull(questionAttention1);
    }

    //测试能不能新添加关注
    @Test
    public void addAttentionToQuestion() {
        QuestionAttention questionAttention = new QuestionAttention();
        questionAttention.setUser_id(1);
        questionAttention.setAtten_cancel(0);
        questionAttention.setAtten_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        questionAttention.setAtten_cancel_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addAttentionToQuestion(questionAttention);
        Assert.assertNotNull(questionAttention.getAtten_id());
        QuestionAttention questionAttention1 = communityQAMapper.getAttention(questionAttention.getAtten_id());
        Assert.assertNotNull(questionAttention1);
    }

    //测试能不能给问题和关注建立映射以及能不能列出用户所关注的问题
    @Test
    public void createQuestionAttenMapAndListUserAttenQues() {
        communityQAMapper.createMapBetweenAttentionAndQuestion(3, 6);
        List<Question> questions = communityQAMapper.listMyAttenQuestion(1);
        Assert.assertEquals(3, questions.size());
        for (Question question : questions) {
            if (question.getQues_id() != 1 && question.getQues_id() != 2 && question.getQues_id() != 3) {
                Assert.fail();
            }
        }
    }

    //测试能不能重新关注
    @Test
    public void reAttentionQuestion() {
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(2, 1);
        Assert.assertNotNull(questionAttention);
        Integer atten_id = questionAttention.getAtten_id();
        questionAttention.setAtten_cancel(0);
        communityQAMapper.reAddAttentionToQuestion(questionAttention);
        QuestionAttention questionAttention1 = communityQAMapper.getAttention(atten_id);
        Integer atten_cancel = 0;
        Assert.assertEquals(atten_cancel, questionAttention1.getAtten_cancel());
    }

    //测试能不能取消关注
    @Test
    public void cancelAttenQuestion() {
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(1, 1);
        Assert.assertNotNull(questionAttention);
        Integer atten_id = questionAttention.getAtten_id();
        questionAttention.setAtten_cancel(1);
        questionAttention.setAtten_cancel_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.cancelAttention(questionAttention);
        QuestionAttention questionAttention1 = communityQAMapper.getAttention(atten_id);
        Integer atten_cancel = 1;
        Assert.assertEquals(atten_cancel, questionAttention1.getAtten_cancel());
    }

    //测试能不能拿到这个问题的关注人数
    @Test
    public void countQuestionFollower() {
        Integer count = communityQAMapper.countTheFollower(1);
        Integer expect = 3;
        Assert.assertEquals(expect, count);
    }

    //测试能不能列出问题的所有回答
    @Test
    public void listAllAnswer() {
        List<QuestionAnswer> questionAnswers = communityQAMapper.listAllQuestionAnswer(1);
        Assert.assertEquals(4, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2 && questionAnswer.getAnswer_id() != 3
                    && questionAnswer.getAnswer_id() != 4) {
                Assert.fail();
            }
        }
    }

    //测试能不能拿到某个回答
    @Test
    public void getAnswer() {
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        Assert.assertEquals("这是第一个回答", questionAnswer.getAnswer_content());
    }

    //测试能不能添加回答
    @Test
    public void addAnswer() {
        QuestionAnswer questionAnswer = new QuestionAnswer();
        questionAnswer.setAnswer_content("添加回答");
        questionAnswer.setUser_id(1);
        questionAnswer.setUser_anony(0);
        questionAnswer.setAnswer_pub_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        questionAnswer.setAnswer_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        questionAnswer.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        questionAnswer.setIs_delete(0);
        communityQAMapper.addAnswerToQuestion(questionAnswer);
        Assert.assertNotNull(questionAnswer.getAnswer_id());
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(questionAnswer.getAnswer_id());
        Assert.assertNotNull(questionAnswer1);
    }

    //测试能不能建立问题和回答的映射
    @Test
    public void createQuesAnswerMap() {
        communityQAMapper.createMapBetweenQuestionAndAnswer(2, 6, 2);
        List<QuestionAnswer> questionAnswers = communityQAMapper.listAllQuestionAnswer(2);
        Assert.assertEquals(2, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 5 && questionAnswer.getAnswer_id() != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能修改回答
    @Test
    public void editAnswer() {
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        questionAnswer.setAnswer_content("修改回答");
        questionAnswer.setAnswer_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.editAnswer(questionAnswer);
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(1);
        Assert.assertEquals("修改回答", questionAnswer1.getAnswer_content());
    }

    //测试能不能删除回答
    @Test
    public void deleteAnswer() {
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        questionAnswer.setIs_delete(1);
        questionAnswer.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.deleteAnswer(questionAnswer);
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(1);
        Assert.assertNull(questionAnswer1);
    }

    //测试能不能列出用户做过的回答
    @Test
    public void listMyAnswer() {
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAnswer(1);
        Assert.assertEquals(2, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2) {
                Assert.fail();
            }
        }
    }

    //测试能不能查看用户对回答的评价
    @Test
    public void evaluateStatus() {
        AnswerEvaluate answerEvaluate = communityQAMapper.evaluateStatus(1, 1);
        Integer evaluate_type = 1;
        Assert.assertEquals(evaluate_type, answerEvaluate.getEvaluate_type());
        AnswerEvaluate answerEvaluate1 = communityQAMapper.evaluateStatus(1, 6);
        Assert.assertNull(answerEvaluate1);
    }

    //测试能不能拿到某个回答评价
    @Test
    public void getAnswerEvaluate() {
        AnswerEvaluate answerEvaluate = communityQAMapper.getAnswerEvaluate(1);
        Assert.assertNotNull(answerEvaluate);
        Integer user_id = 1;
        Assert.assertEquals(user_id, answerEvaluate.getUser_id());
    }

    //测试能不能新添加评价
    @Test
    public void evaluateAnswer() {
        AnswerEvaluate answerEvaluate = new AnswerEvaluate();
        answerEvaluate.setUser_id(1);
        answerEvaluate.setEvaluate_type(1);
        answerEvaluate.setEvaluate_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addEvaluateToAnswer(answerEvaluate);
        Assert.assertNotNull(answerEvaluate.getEvaluate_id());
        AnswerEvaluate answerEvaluate1 = communityQAMapper.getAnswerEvaluate(answerEvaluate.getEvaluate_id());
        Assert.assertNotNull(answerEvaluate1);
    }

    //测试能不能建立回答和评价的映射以及能不能列出用户赞同过的回答
    @Test
    public void createAnswerEvaluateMap() {
        communityQAMapper.createMapBetweenAnswerAndEvaluate(10, 6);
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAgreeAnswer(1);
        Assert.assertEquals(4, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 5 &&
                    questionAnswer.getAnswer_id() != 4 && questionAnswer.getAnswer_id() != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能重新评价回答
    @Test
    public void reEvaluateAnswer() {
        AnswerEvaluate answerEvaluate = communityQAMapper.getAnswerEvaluate(1);
        answerEvaluate.setEvaluate_type(2);
        communityQAMapper.reEvaluateAnswer(answerEvaluate);
        AnswerEvaluate answerEvaluate1 = communityQAMapper.getAnswerEvaluate(1);
        Integer evaluate_type = 2;
        Assert.assertEquals(evaluate_type, answerEvaluate1.getEvaluate_type());
    }

    //测试得到回答的赞同数
    @Test
    public void countAnswerAgreement() {
        Integer agreements = communityQAMapper.countAgreement(1);
        Integer expect = 2;
        Assert.assertEquals(expect, agreements);
    }

    //测试得到回答的不赞同数
    @Test
    public void countDisagreement() {
        Integer disAgreements = communityQAMapper.countDisagreement(2);
        Integer expect = 3;
        Assert.assertEquals(expect, disAgreements);
    }

    //测试能不能拿到评论
    @Test
    public void getComment() {
        AnswerComment answerComment = communityQAMapper.getComment(1);
        Assert.assertEquals("你这也回答的太好了吧！1", answerComment.getComment_content());
    }

    //测试能不能给回答添加评论
    @Test
    public void addComment() {
        AnswerComment answerComment = new AnswerComment();
        answerComment.setComment_content("加一个");
        answerComment.setUser_id(1);
        answerComment.setUser_anony(1);
        answerComment.setComment_pub_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        answerComment.setComment_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        answerComment.setIs_delete(0);
        answerComment.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addCommentToAnswer(answerComment);
        Assert.assertNotNull(answerComment.getComment_id());
        AnswerComment answerComment1 = communityQAMapper.getComment(answerComment.getComment_id());
        Assert.assertNotNull(answerComment1);
    }

    //测试拿到某个回答的所有评论
    @Test
    public void getAllComment() {
        List<AnswerComment> answerComments = communityQAMapper.listAllAnswerComment(1);
        Assert.assertEquals(3, answerComments.size());
        for (AnswerComment answerComment : answerComments) {
            if (answerComment.getComment_id() != 1 && answerComment.getComment_id() != 3 && answerComment.getComment_id() != 5) {
                Assert.fail();
            }
        }
    }

    //测试能不能给评论和回答添加映射
    @Test
    public void createAnswerCommentMap() {
        communityQAMapper.createMapBetweenAnswerAndComment(2, 6, 3);
        List<AnswerComment> answerComments = communityQAMapper.listAllAnswerComment(2);
        Assert.assertEquals(3, answerComments.size());
        for (AnswerComment answerComment : answerComments) {
            if (answerComment.getComment_id() != 4 && answerComment.getComment_id() != 2 && answerComment.getComment_id()
                    != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能删除评论
    @Test
    public void deleteComment() {
        AnswerComment answerComment = communityQAMapper.getComment(1);
        answerComment.setIs_delete(1);
        communityQAMapper.deleteComment(answerComment);
        AnswerComment answerComment1 = communityQAMapper.getComment(1);
        Assert.assertNull(answerComment1);
    }

    //测试能不能拿到用户对某评论的评价
    @Test
    public void commentEvaluateStatus() {
        CommentEvaluate commentEvaluate = communityQAMapper.commentEvaluateStatus(1, 1);
        Assert.assertNotNull(commentEvaluate);
        Integer evaluate_type = 1;
        Assert.assertEquals(evaluate_type, commentEvaluate.getEvaluate_type());
        CommentEvaluate commentEvaluate1 = communityQAMapper.commentEvaluateStatus(1, 2);
        Assert.assertNull(commentEvaluate1);
    }

    //测试能不能拿到某个评价
    @Test
    public void getEvaluateStatus() {
        CommentEvaluate commentEvaluate = communityQAMapper.getCommentEvaluate(1);
        Integer user_id = 1;
        Integer evaluate_type = 1;
        Assert.assertEquals(user_id, commentEvaluate.getUser_id());
        Assert.assertEquals(evaluate_type, commentEvaluate.getEvaluate_type());
    }

    //测试能不能添加评价
    @Test
    public void addCommentEvaluate() {
        CommentEvaluate commentEvaluate = new CommentEvaluate();
        commentEvaluate.setUser_id(1);
        commentEvaluate.setEvaluate_type(1);
        commentEvaluate.setEvaluate_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addEvaluateToComment(commentEvaluate);
        Assert.assertNotNull(commentEvaluate.getEvaluate_id());
        CommentEvaluate commentEvaluate1 = communityQAMapper.getCommentEvaluate(commentEvaluate.getEvaluate_id());
        Assert.assertNotNull(commentEvaluate1);
    }

    //测试能不能拿到某评论的点赞数
    @Test
    public void countCommentAgreement() {
        Integer agreement = communityQAMapper.countCommentAgreement(1);
        Integer expect = 3;
        Assert.assertEquals(expect, agreement);
    }

    //测试能不能建立评价和评论的映射
    @Test
    public void createCommentEvaluateMap() {
        communityQAMapper.createMapBetweenCommentAndEvaluate(5, 2);
        Integer agreement = communityQAMapper.countCommentAgreement(2);
        Integer expect = 1;
        Assert.assertEquals(expect, agreement);
    }

    //测试能不能重新评价评论  存疑  这里假如把修改时间的那句注释掉就错了，不知道为什么
    @Test
    public void reEvaluateComment() {
        CommentEvaluate commentEvaluate = communityQAMapper.getCommentEvaluate(1);
        commentEvaluate.setEvaluate_type(3);
        commentEvaluate.setEvaluate_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.reEvaluateComment(commentEvaluate);
        Integer agreement = communityQAMapper.countCommentAgreement(1);
        Integer expect = 2;
        Assert.assertEquals(expect, agreement);
    }

    //测试能不能列出某个评论的所有讨论
    @Test
    public void listAllCommentDiscuss() {
        List<CommentDiscuss> commentDiscusses = communityQAMapper.listAllCommentDiscuss(1);
        Assert.assertEquals(3, commentDiscusses.size());
        for (CommentDiscuss commentDiscuss : commentDiscusses) {
            if (commentDiscuss.getDiscuss_id() != 1 && commentDiscuss.getDiscuss_id() != 3 && commentDiscuss.getDiscuss_id() != 5) {
                Assert.fail();
            }
        }
    }

    //得到某个讨论
    @Test
    public void getDiscuss() {
        CommentDiscuss commentDiscuss = communityQAMapper.getDiscuss(1);
        Assert.assertEquals("SDGAGERGRGRGAGgegege1", commentDiscuss.getDiscuss_content());
    }

    //添加讨论
    @Test
    public void addDiscuss() {
        CommentDiscuss commentDiscuss = new CommentDiscuss();
        commentDiscuss.setDiscuss_content("新的讨论");
        commentDiscuss.setDiscuss_target_id(1);
        commentDiscuss.setDiscuss_pub_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        commentDiscuss.setUser_id(1);
        commentDiscuss.setUser_anony(0);
        commentDiscuss.setIs_delete(0);
        commentDiscuss.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addDiscuss(commentDiscuss);
        Assert.assertNotNull(commentDiscuss.getDiscuss_id());
        CommentDiscuss commentDiscuss1 = communityQAMapper.getDiscuss(commentDiscuss.getDiscuss_id());
        Assert.assertNotNull(commentDiscuss1);
    }

    //测试能不能建立映射
    @Test
    public void createDiscussCommentMap() {
        communityQAMapper.createMapBetweenDiscussAndComment(6, 2, 3);
        List<CommentDiscuss> commentDiscusses = communityQAMapper.listAllCommentDiscuss(2);
        Assert.assertEquals(3, commentDiscusses.size());
        for (CommentDiscuss commentDiscuss : commentDiscusses) {
            if (commentDiscuss.getDiscuss_id() != 2 && commentDiscuss.getDiscuss_id() != 4 && commentDiscuss.getDiscuss_id() != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能删除讨论
    @Test
    public void deleteDiscuss() {
        CommentDiscuss commentDiscuss = communityQAMapper.getDiscuss(1);
        commentDiscuss.setIs_delete(1);
        //commentDiscuss.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.deleteDiscuss(commentDiscuss);
        CommentDiscuss commentDiscuss1 = communityQAMapper.getDiscuss(1);
        Assert.assertNull(commentDiscuss1);

    }

    //测试能不能得到讨论的评价
    @Test
    public void countDiscussAgreement() {
        Integer expect = 6;
        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(1));
    }

    //测试能不能检察用户对讨论的评价状态
    @Test
    public void discussEvaluateStatus() {
        DiscussEvaluate discussEvaluate = communityQAMapper.discussEvaluateStatus(3, 1);
        Assert.assertNotNull(discussEvaluate);
        Integer evaluate_id = 5;
        Assert.assertEquals(evaluate_id, discussEvaluate.getEvaluate_id());
        DiscussEvaluate discussEvaluate1 = communityQAMapper.discussEvaluateStatus(3, 2);
        Assert.assertNull(discussEvaluate1);
    }

    //测试能不能得到一个讨论评价
    @Test
    public void getDiscussEvaluate() {
        DiscussEvaluate discussEvaluate = communityQAMapper.getDiscussEvaluate(1);
        Assert.assertEquals(Timestamp.valueOf("2018-12-06 14:36:40"), discussEvaluate.getEvaluate_time());
    }

    //测试能不能添加讨论评价
    @Test
    public void addDiscussEvaluate() {
        DiscussEvaluate discussEvaluate = new DiscussEvaluate();
        discussEvaluate.setUser_id(1);
        discussEvaluate.setEvaluate_type(3);
        discussEvaluate.setEvaluate_time(Timestamp.valueOf("2018-12-06 14:36:40"));
        communityQAMapper.addEvaluateToDiscuss(discussEvaluate);
        Assert.assertNotNull(discussEvaluate.getEvaluate_id());
        DiscussEvaluate discussEvaluate1 = communityQAMapper.getDiscussEvaluate(discussEvaluate.getEvaluate_id());
        Assert.assertNotNull(discussEvaluate1);
    }

    //测试能不能添加映射
    @Test
    public void createDiscussEvaluateMap() {
        communityQAMapper.createMapBetweenDiscussAndEvaluate(10, 2);
        Integer expect = 3;
        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(2));
    }

    //测试能不能重新价
    @Test
    public void deleteEvaluate() {
        DiscussEvaluate discussEvaluate = communityQAMapper.getDiscussEvaluate(1);
        discussEvaluate.setEvaluate_type(3);
        discussEvaluate.setEvaluate_time(Timestamp.valueOf("2018-12-06 14:36:46"));
        communityQAMapper.reEvaluateDiscuss(discussEvaluate);
        Integer expect = 5;
        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(1));
    }


    //测试能不能列出用户收到的所有邀请
    @Test
    public void listInvitationGot() {
//        Question question = communityQAMapper.getQuestion(1);
//        question.setIs_delete(1);
//        question.setIs_delete_time(Timestamp.valueOf("2018-12-06 14:36:46"));
//        communityQAMapper.deleteQuestion(question);
        List<AnswerInvitation> answerInvitations = communityQAMapper.listInvitationGot(1);
        Assert.assertEquals(3, answerInvitations.size());
        for (AnswerInvitation answerInvitation : answerInvitations) {
            if (answerInvitation.getInvit_id() != 1 && answerInvitation.getInvit_id() != 2 &&
                    answerInvitation.getInvit_id() != 3) {
                Assert.fail();
            }
        }
    }

    //测试能不能列出用户邀请过哪些用户回答某个问题
    @Test
    public void listUsersInvitedByMeToQuestion() {
        List<Integer> users_id = communityQAMapper.listUsersInvitedByMeToQuestion(2, 1);
        Assert.assertEquals(2, users_id.size());
        for (Integer user_id : users_id) {
            if (user_id != 1 && user_id != 3) {
                Assert.fail();
            }
        }
    }

    //测试能不能得到某个邀请
    @Test
    public void getInvitation() {
        AnswerInvitation answerInvitation = communityQAMapper.getInvitation(1);
        Integer ques_id = 1;
        Integer invit_user_id = 2;
        Assert.assertEquals(ques_id, answerInvitation.getInvit_ques_id());
        Assert.assertEquals(invit_user_id, answerInvitation.getInvit_user_id());
    }

    //测试能不能添加邀请
    @Test
    public void addInvitation() {
        AnswerInvitation answerInvitation = new AnswerInvitation();
        answerInvitation.setInvit_ques_id(1);
        answerInvitation.setInvit_user_id(5);
        answerInvitation.setInvit_accept(0);
        answerInvitation.setInvit_time(Timestamp.valueOf("2018-12-06 14:36:46"));
        communityQAMapper.addInvitation(answerInvitation);
        Assert.assertNotNull(answerInvitation.getInvit_id());
        AnswerInvitation answerInvitation1 = communityQAMapper.getInvitation(answerInvitation.getInvit_id());
        Assert.assertNotNull(answerInvitation1);
    }

    //测试能不能建立映射
    @Test
    public void createInvitationUserMao() {
        communityQAMapper.createMapBetweenInvitationAndQuestion(6, 1);
        List<AnswerInvitation> answerInvitations = communityQAMapper.listInvitationGot(1);
        Assert.assertEquals(4, answerInvitations.size());
        for (AnswerInvitation answerInvitation : answerInvitations) {
            if (answerInvitation.getInvit_id() != 1 && answerInvitation.getInvit_id() != 2 &&
                    answerInvitation.getInvit_id() != 3 && answerInvitation.getInvit_id() != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能接受或者拒绝邀请
    @Test
    public void updateStatusOfInvitation() {
        AnswerInvitation answerInvitation = communityQAMapper.getInvitation(1);
        answerInvitation.setInvit_accept(1);
        communityQAMapper.updateStatusOfInvitation(answerInvitation);
        List<AnswerInvitation> answerInvitations = communityQAMapper.listInvitationGot(1);
        Assert.assertEquals(2, answerInvitations.size());
        for (AnswerInvitation answerInvitation1 : answerInvitations) {
            if (answerInvitation1.getInvit_id() != 2 && answerInvitation1.getInvit_id() != 3) {
                Assert.fail();
            }
        }
    }

    //测试能不能列出前十个视频  简单地根据时间
    @Test
    public void listFirstTenVideos() {
        List<Video> videos = communityQAMapper.listFirstTenVideos();
        Assert.assertEquals(10, videos.size());
        for (Video video : videos) {
            if (video.getVideo_id() < 1 || video.getVideo_id() > 11 || video.getVideo_id() == 4) {
                Assert.fail();
            }
        }
    }

    //测试能不能拿到某用户发布的视频
    @Test
    public void getUserAllVideo() {
        List<Video> videos = communityQAMapper.listAllMyVideos(1);
        Assert.assertEquals(3, videos.size());
        for (Video video : videos) {
            if (video.getVideo_id() != 7 && video.getVideo_id() != 9
                    && video.getVideo_id() != 11) {
                Assert.fail();
            }
        }
    }

    //测试能不能得到某个视频
    @Test
    public void getVideo() {
        Video video = communityQAMapper.getVideo(1);
        Assert.assertEquals(Timestamp.valueOf("2018-11-11 11:11:22"), video.getVideo_upload_time());
    }

    //测试能不能添加视频
    @Test
    public void addVideo() {
        Video video = new Video();
        video.setIs_delete(0);
        video.setIs_delete_time(Timestamp.valueOf("2018-11-11 11:11:22"));
        video.setVideo_path("important");
        video.setVideo_title("first");
        video.setVideo_upload_time(Timestamp.valueOf("2018-11-11 11:11:22"));
        communityQAMapper.addVideo(video);
        Assert.assertNotNull(video);
        Video video1 = communityQAMapper.getVideo(video.getVideo_id());
        Assert.assertNotNull(video1);
    }

    //测试能不能建立映射
    @Test
    public void createUserVideoMap() {
        communityQAMapper.createMapBetweenVideoAndUser(2, 1);
        List<Video> videos = communityQAMapper.listAllMyVideos(1);
        Assert.assertEquals(4, videos.size());
        for (Video video : videos) {
            if (video.getVideo_id() != 2 && video.getVideo_id() != 7 && video.getVideo_id() != 9
                    && video.getVideo_id() != 11) {
                Assert.fail();
            }
        }
    }

    //测试能不能删除视频
    @Test
    public void deleteVideo() {
        Video video = communityQAMapper.getVideo(1);
        video.setIs_delete(1);
        video.setIs_delete_time(Timestamp.valueOf("2018-12-11 11:11:23"));
        communityQAMapper.deleteVideo(video);
        Video video1 = communityQAMapper.getVideo(1);
        Assert.assertNull(video1);
    }


    //测试能不能列出视频的关注数
    @Test
    public void countVideoAttention() {
        Integer atten = communityQAMapper.countVideoFollwers(1);
        Integer expect = 3;
        Assert.assertEquals(expect, atten);
    }

    //测试能不能拿到用户对视频的关注状态
    @Test
    public void videoAttentionStatus() {
        VideoAttention videoAttention = communityQAMapper.videoAttentionStatus(1, 1);
        Assert.assertNotNull(videoAttention);
        VideoAttention videoAttention1 = communityQAMapper.videoAttentionStatus(2, 1);
        Assert.assertNotNull(videoAttention1);
    }

    //测试能不能拿到某个关注对象
    @Test
    public void getVideoAttention() {
        VideoAttention videoAttention = communityQAMapper.getVideoAttention(1);
        Integer user_id = 1;
        Assert.assertEquals(user_id, videoAttention.getUser_id());
        Assert.assertEquals(Timestamp.valueOf("2018-1-16 11:11:22"), videoAttention.getAtten_time());
    }

    //测试能不能添加关注
    @Test
    public void addAttention() {
        VideoAttention videoAttention = new VideoAttention();
        videoAttention.setUser_id(1);
        videoAttention.setAtten_time(Timestamp.valueOf("2018-1-16 11:11:22"));
        videoAttention.setAtten_cancel(0);
        videoAttention.setAtten_cancel_time(Timestamp.valueOf("2018-1-16 11:11:22"));
        communityQAMapper.addAttentionToVideo(videoAttention);
        Assert.assertNotNull(videoAttention.getAtten_id());
        VideoAttention videoAttention1 = communityQAMapper.getVideoAttention(videoAttention.getAtten_id());
        Assert.assertNotNull(videoAttention1);
    }

    //测试能不能建立映射
    @Test
    public void createAttentionVideoMap() {
        communityQAMapper.createMapBetweenAttentionAndVideo(6, 1);
        Integer atten = communityQAMapper.countVideoFollwers(1);
        Integer expect = 4;
        Assert.assertEquals(expect, atten);
    }

    //测试能不能重新关注视频
    @Test
    public void reAttentionVideo() {
        VideoAttention videoAttention = communityQAMapper.getVideoAttention(5);
        videoAttention.setAtten_cancel(0);
        communityQAMapper.reAddAttentionToVideo(videoAttention);
        Integer atten = communityQAMapper.countVideoFollwers(1);
        Integer expect = 4;
        Assert.assertEquals(expect, atten);
    }

    //测试能不能取消关注视频
    @Test
    public void cancelAttention() {
        VideoAttention videoAttention = communityQAMapper.getVideoAttention(1);
        videoAttention.setAtten_cancel(1);
        communityQAMapper.cancelAttentionVideo(videoAttention);
        Integer atten = communityQAMapper.countVideoFollwers(1);
        Integer expect = 2;
        Assert.assertEquals(expect, atten);
    }

    //测试能不能拿到视频的评论数
    @Test
    public void countVideoComments() {
        Integer expect = 2;
        Assert.assertEquals(expect, communityQAMapper.countVideoComments(1));
    }

    //测试能不能拿到评论
    @Test
    public void getVideoComment() {
        VideoComment videoComment = communityQAMapper.getVideoComment(1);
        Assert.assertEquals("HAHA1", videoComment.getComment_content());
    }

    //测试能不能添加评论
    @Test
    public void addVideoComment() {
        VideoComment videoComment = new VideoComment();
        videoComment.setUser_id(1);
        videoComment.setComment_content("xixix1");
        videoComment.setComment_pub_time(Timestamp.valueOf("2018-1-16 11:11:22"));
        videoComment.setUser_anony(0);
        videoComment.setComment_edit_time(Timestamp.valueOf("2018-1-16 11:11:22"));
        videoComment.setIs_delete(0);
        videoComment.setIs_delete_time(Timestamp.valueOf("2018-1-16 11:11:22"));
        communityQAMapper.addCommentToVideo(videoComment);
        Assert.assertNotNull(videoComment.getComment_id());
        VideoComment videoComment1 = communityQAMapper.getVideoComment(videoComment.getComment_id());
        Assert.assertNotNull(videoComment1);
    }

    //测试能不能建立映射
    @Test
    public void createVideoCommentMap() {
        communityQAMapper.createMapBetweenCommentAndVideo(4, 1, 4);
        Integer expect = 3;
        Assert.assertEquals(expect, communityQAMapper.countVideoComments(1));
    }

    //测试能不能删除评论
    @Test
    public void deleteVideoComment() {
        VideoComment videoComment = communityQAMapper.getVideoComment(1);
        videoComment.setIs_delete(1);
        communityQAMapper.deleteVideoComment(videoComment);
        VideoComment videoComment1 = communityQAMapper.getVideoComment(1);
        Assert.assertNull(videoComment1);
    }

    //测试能不能得到视频的点赞数
    @Test
    public void countVideoAgreement() {
        Integer expect = 2;
        Assert.assertEquals(expect, communityQAMapper.countVideoAgreement(1));
    }

    //测试能不能拿到视频的不赞同数
    @Test
    public void countVideoDisagreement() {
        Integer expect = 1;
        Assert.assertEquals(expect, communityQAMapper.countVideoDisagreement(1));
    }

    //测试能不能拿到用户对该视频的评价状态
    @Test
    public void videoEvaluateStatus() {
        VideoEvaluate videoEvaluate = communityQAMapper.videoEvaluateStatus(1, 1);
        Integer e = 1;
        Integer id = 1;
        Assert.assertEquals(e, videoEvaluate.getEvaluate_type());
        Assert.assertEquals(id, videoEvaluate.getEvaluate_id());
        VideoEvaluate videoEvaluate1 = communityQAMapper.videoEvaluateStatus(3, 1);
        Assert.assertNull(videoEvaluate1);
    }

    //测试能不能拿到视频评价
    @Test
    public void getVideoEvaluate() {
        VideoEvaluate videoEvaluate = communityQAMapper.getVideoEvaluate(1);
        Assert.assertEquals(Timestamp.valueOf("2019-1-12 11:11:22"), videoEvaluate.getEvaluate_time());
    }

    //测试能不能添加视频评价
    @Test
    public void addVideoEvaluate() {
        VideoEvaluate videoEvaluate = new VideoEvaluate();
        videoEvaluate.setUser_id(1);
        videoEvaluate.setEvaluate_type(1);
        videoEvaluate.setEvaluate_time(Timestamp.valueOf("2019-1-12 11:11:22"));
        communityQAMapper.addEvaluationToVideo(videoEvaluate);
        Assert.assertNotNull(videoEvaluate);
        VideoEvaluate videoEvaluate1 = communityQAMapper.getVideoEvaluate(videoEvaluate.getEvaluate_id());
        Assert.assertNotNull(videoEvaluate1);
    }

    //测试能不能建立映射
    @Test
    public void createVideoEvaluateMap() {
        communityQAMapper.createMapBetweenEvaluationAndVideo(7, 1);
        Integer expect = 3;
        Assert.assertEquals(expect, communityQAMapper.countVideoAgreement(1));
    }

    //测试能不能重新评价
    @Test
    public void reEvalauteVideo() {
        VideoEvaluate videoEvaluate = communityQAMapper.getVideoEvaluate(1);
        videoEvaluate.setEvaluate_type(2);
        communityQAMapper.reEvaluateVideo(videoEvaluate);
        Integer agreement = 1;
        Integer disagreement = 2;
        Assert.assertEquals(agreement, communityQAMapper.countVideoAgreement(1));
        Assert.assertEquals(disagreement, communityQAMapper.countVideoDisagreement(1));
    }


    @Test
    public void testGetQuesitionById() {
        Question question = communityQAMapper.getQuestionById(1);
        Assert.assertNotNull(question);
        User user = question.getQues_user();
        System.out.println(user.getUsername());
        List<QuestionAttention> questionAttentions = question.getQuestionAttentions();
        System.out.println(questionAttentions.size());
        System.out.println(questionAttentions.get(0).getUser_id());
        List<Label> labels = question.getLabels();
        for (Label label : labels) {
            System.out.print(label.getLab_chn() + "   ");
        }
        System.out.println();
        List<QuestionAnswer> questionAnswers = question.getQuestionAnswers();
        System.out.println(questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            System.out.print(questionAnswer.getAnswer_id() + "  ");
            System.out.print(questionAnswer.getAnswer_content() + "   ");
            System.out.print(questionAnswer.getAnswer_user().getUsername() + "  ");
            System.out.print(questionAnswer.getAnswerEvaluates().size() + "  ");
            List<AnswerComment> answerComments = questionAnswer.getAnswerComments();
            for (AnswerComment answerComment : answerComments) {
                System.out.print(answerComment.getComment_content() + "  ");
                System.out.print(answerComment.getUser().getUsername() + "  ");
                List<CommentEvaluate> commentEvaluates = answerComment.getCommentEvaluates();
                for (CommentEvaluate commentEvaluate : commentEvaluates) {
//                    if(commentEvaluate != null) count++;
                    System.out.print(commentEvaluate.getEvaluate_id() + " ");
                }

                System.out.print("接下来是discuss" + " ");
                List<CommentDiscuss> commentDiscusses = answerComment.getCommentDiscusses();
                for (CommentDiscuss commentDiscuss : commentDiscusses) {
                    System.out.print(commentDiscuss.getDiscuss_content() + "  ");
                    System.out.print(commentDiscuss.getUser().getUsername() + "  ");
                    List<DiscussEvaluate> discussEvaluates = commentDiscuss.getDiscussEvaluateList();
                    for (DiscussEvaluate discussEvaluate : discussEvaluates) {
                        System.out.print(discussEvaluate.getEvaluate_id() + " ");
                    }
                }
                System.out.println("结束");
            }
            System.out.println();
        }
        System.out.println();

    }

    @Test
    public void getAnswerById() {
        QuestionAnswer questionAnswer = communityQAMapper.getAnswerById(1);
        User user = questionAnswer.getAnswer_user();
        System.out.println(user.getUsername());
        List<AnswerEvaluate> answerEvaluates = questionAnswer.getAnswerEvaluates();
        System.out.println(answerEvaluates.size());
        for (AnswerEvaluate answerEvaluate : answerEvaluates) {
            System.out.print(answerEvaluate.getEvaluate_id() + "   ");
        }
        System.out.println();
        List<AnswerComment> answerComments = questionAnswer.getAnswerComments();
        System.out.println(answerComments.size());
        for (AnswerComment answerComment : answerComments) {
            System.out.print(answerComment.getComment_id());
        }
        System.out.println();
        System.out.println(questionAnswer.getAnswerEvaluates().size());
    }

    @Test
    public void getCommentById() {
        AnswerComment answerComment = communityQAMapper.getAnswerCommentById(2);
        User user = answerComment.getUser();
        System.out.println(user.getUsername());
        List<CommentEvaluate> commentEvaluates = answerComment.getCommentEvaluates();
        System.out.println(commentEvaluates.size());
        for (CommentEvaluate commentEvaluate : commentEvaluates) {
            System.out.print(commentEvaluate.getEvaluate_id() + " ");
        }
        System.out.println();
        List<CommentDiscuss> commentDiscusses = answerComment.getCommentDiscusses();
        System.out.println(commentDiscusses.size());
        for (CommentDiscuss commentDiscuss : commentDiscusses) {
            System.out.print(commentDiscuss.getDiscuss_id());
        }
        System.out.println();
    }

    @Test
    public void getDiscussById() {
        CommentDiscuss commentDiscuss = communityQAMapper.getAnswerDiscussById(1);
    }

    @Test
    public void getVideoById() {
        Video video = communityQAMapper.getVideoById(1);
        User user = video.getUser();
        System.out.println(user.getId());
        List<VideoAttention> videoAttentions = video.getVideoAttentions();
        List<VideoEvaluate> videoEvaluates = video.getVideoEvaluates();
        List<VideoComment> videoComments = video.getVideoComments();
        for (VideoAttention videoAttention : videoAttentions) {
            System.out.print(videoAttention.getAtten_id());
        }
        System.out.println();
        for (VideoEvaluate videoEvaluate : videoEvaluates) {
            System.out.print(videoEvaluate.getEvaluate_id());
        }
        System.out.println();
        for (VideoComment videoComment : videoComments) {
            System.out.print(videoComment.getComment_id());
        }
    }

    @Test
    public void getQuestionReleTypeAndReleId() {
        QuestionReleTypeAndId questionReleTypeAndId = communityQAMapper.getQuestionReleTypeAndReleId(1);
        System.out.println(questionReleTypeAndId.getRele_id() + " " + questionReleTypeAndId.getRele_type());
    }

    @Test
    public void isAnswerBelongToQuestion(){
        Boolean b1 = communityQAMapper.isAnswerBelongToQuestion(1,1);
        System.out.println(b1);
        System.out.println(communityQAMapper.isAnswerBelongToQuestion(1,2));
        System.out.println(communityQAMapper.isAnswerBelongToQuestion(5,2));
    }
}

