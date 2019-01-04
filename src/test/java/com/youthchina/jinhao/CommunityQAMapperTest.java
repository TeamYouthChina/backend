package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.*;
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
@DatabaseSetup({"classpath:questions.xml","classpath:answers.xml","classpath:comments.xml"})
public class CommunityQAMapperTest extends BaseTest{
    @Autowired
    CommunityQAMapper communityQAMapper;

    // 测试能不能拿到某个问题
    @Test
    public void getQuestion(){
        Question question = communityQAMapper.getQuestion(1);
        Assert.assertEquals("第一个问题", question.getQues_title());
    }

    //测试能不能添加问题
    @Test
    public void addQuestion(){
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
    public void testGetMyQuestionAndCreateMapBetweenUserAndQuestion(){
        communityQAMapper.createMapBetweenQuestionAndUser(3, 1);
        List<Question> questionList = communityQAMapper.getMyQuestions(1);
        Assert.assertEquals(3, questionList.size());
        for(Question question : questionList){
            if(question.getQues_id() != 1 && question.getQues_id() != 2 && question.getQues_id() != 3){
                Assert.fail();
            }
        }

    }

    //测试能不能更改问题
    @Test
    public void updateQuestion(){
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
    public void deleteQuestion(){
        Question question = communityQAMapper.getQuestion(1);
        question.setIs_delete(1);
        question.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.deleteQuestion(question);
        Question newquestion = communityQAMapper.getQuestion(1);
        Assert.assertNull(newquestion);
    }

    //测试能不能列出前十个问题
    @Test
    public void listQuestions(){
        List<Question> questionList = communityQAMapper.listQuestion();
        Assert.assertEquals(10, questionList.size());
        for(Question question : questionList){
            Assert.assertNotNull(question);
        }
    }

    //测试能不能拿到问题的标签
    @Test
    public void getQuestionLabels(){
        List<Label> labels = communityQAMapper.listAllQuesetionLabel(1);
        Assert.assertEquals(3, labels.size());
        for(Label label : labels){
            if(label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 5){
                Assert.fail();
            }
        }
    }

    //测试能不能给问题添加标签
    @Test
    public void addLabels(){
        List<Integer> label_nums = new LinkedList<>();
        label_nums.add(2);
        label_nums.add(1);
        label_nums.add(3);
        communityQAMapper.addLabels(label_nums, 2);
        List<Label> labels = communityQAMapper.listAllQuesetionLabel(2);
        Assert.assertEquals(3, labels.size());
        for(Label label : labels){
            if(label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 2){
                Assert.fail();
            }
        }
    }

    //测试能不能拿到某个问题关注
    @Test
    public void getQuestionAttention(){
        QuestionAttention questionAttention = communityQAMapper.getAttention(1);
        Integer integer = 1;
        Assert.assertEquals(integer, questionAttention.getUser_id());
    }

    //测试能不能成功判断用户是否关注这个问题
    @Test
    public void isEverAttentionQuestion(){
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(1,1);
        Assert.assertNotNull(questionAttention);
        QuestionAttention questionAttention1 = communityQAMapper.isQuestionAttention(1,2);
        Assert.assertNull(questionAttention1);
    }

    //测试能不能新添加关注
    @Test
    public void addAttentionToQuestion(){
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
    public void createQuestionAttenMapAndListUserAttenQues(){
        communityQAMapper.createMapBetweenAttentionAndQuestion(3,6);
        List<Question> questions = communityQAMapper.listMyAttenQuestion(1);
        Assert.assertEquals(3, questions.size());
        for(Question question : questions){
            if(question.getQues_id() != 1 && question.getQues_id() != 2 && question.getQues_id() != 3){
                Assert.fail();
            }
        }
    }

    //测试能不能重新关注
    @Test
    public void reAttentionQuestion(){
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(2,1);
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
    public void cancelAttenQuestion(){
        QuestionAttention questionAttention = communityQAMapper.isQuestionAttention(1,1);
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
    public void countQuestionFollower(){
        Integer count = communityQAMapper.countTheFollower(1);
        Integer expect = 3;
        Assert.assertEquals(expect, count);
    }

    //测试能不能列出问题的所有回答
    @Test
    public void listAllAnswer(){
        List<QuestionAnswer> questionAnswers = communityQAMapper.listAllQuestionAnswer(1);
        Assert.assertEquals(4, questionAnswers.size());
        for(QuestionAnswer questionAnswer : questionAnswers){
            if(questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2 && questionAnswer.getAnswer_id() != 3
            && questionAnswer.getAnswer_id() != 4){
                Assert.fail();
            }
        }
    }

    //测试能不能拿到某个回答
    @Test
    public void getAnswer(){
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        Assert.assertEquals("这是第一个回答", questionAnswer.getAnswer_content());
    }
    //测试能不能添加回答
    @Test
    public void addAnswer(){
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
    public void createQuesAnswerMap(){
        communityQAMapper.createMapBetweenQuestionAndAnswer(2,6, 2);
        List<QuestionAnswer> questionAnswers = communityQAMapper.listAllQuestionAnswer(2);
        Assert.assertEquals(2, questionAnswers.size());
        for(QuestionAnswer questionAnswer : questionAnswers){
            if(questionAnswer.getAnswer_id() != 5 && questionAnswer.getAnswer_id() != 6){
                Assert.fail();
            }
        }
    }
    //测试能不能修改回答
    @Test
    public void editAnswer(){
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        questionAnswer.setAnswer_content("修改回答");
        questionAnswer.setAnswer_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.editAnswer(questionAnswer);
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(1);
        Assert.assertEquals("修改回答", questionAnswer1.getAnswer_content());
    }

    //测试能不能删除回答
    @Test
    public void deleteAnswer(){
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        questionAnswer.setIs_delete(1);
        questionAnswer.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.deleteAnswer(questionAnswer);
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(1);
        Assert.assertNull(questionAnswer1);
    }

    //测试能不能列出用户做过的回答
    @Test
    public void listMyAnswer(){
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAnswer(1);
        Assert.assertEquals(2, questionAnswers.size());
        for(QuestionAnswer questionAnswer : questionAnswers){
            if(questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2){
                Assert.fail();
            }
        }
    }

    //测试能不能查看用户对回答的评价
    @Test
    public void evaluateStatus(){
        AnswerEvaluate answerEvaluate = communityQAMapper.evaluateStatus(1, 1);
        Integer evaluate_type = 1;
        Assert.assertEquals(evaluate_type, answerEvaluate.getEvaluate_type());
        AnswerEvaluate answerEvaluate1 = communityQAMapper.evaluateStatus(1, 5);
        Assert.assertNull(answerEvaluate1);
    }

    //测试能不能拿到某个回答评价
    @Test
    public void getAnswerEvaluate(){
        AnswerEvaluate answerEvaluate = communityQAMapper.getAnswerEvaluate(1);
        Assert.assertNotNull(answerEvaluate);
        Integer user_id = 1;
        Assert.assertEquals(user_id, answerEvaluate.getUser_id());
    }

    //测试能不能新添加评价
    @Test
    public void evaluateAnswer(){
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
    public void createAnswerEvaluateMap(){
        communityQAMapper.createMapBetweenAnswerAndEvaluate(7,3);
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAgreeAnswer(1);
        Assert.assertEquals(3, questionAnswers.size());
        for(QuestionAnswer questionAnswer : questionAnswers){
            if(questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 3 &&
            questionAnswer.getAnswer_id() != 4){
                Assert.fail();
            }
        }
    }
    //测试能不能重新评价回答
    @Test
    public void reEvaluateAnswer(){
        AnswerEvaluate answerEvaluate = communityQAMapper.getAnswerEvaluate(1);
        answerEvaluate.setEvaluate_type(2);
        communityQAMapper.reEvaluateAnswer(answerEvaluate);
        AnswerEvaluate answerEvaluate1 = communityQAMapper.getAnswerEvaluate(1);
        Integer evaluate_type = 2;
        Assert.assertEquals(evaluate_type, answerEvaluate1.getEvaluate_type());
    }

    //测试得到回答的赞同数
    @Test
    public void countAnswerAgreement(){
        Integer agreements = communityQAMapper.countAgreement(1);
        Integer expect = 2;
        Assert.assertEquals(expect,agreements);
    }

    //测试得到回答的不赞同数
    @Test
    public void countDisagreement(){
        Integer disAgreements = communityQAMapper.countDisagreement(2);
        Integer expect = 3;
        Assert.assertEquals(expect,disAgreements);
    }

    //测试能不能拿到评论
    @Test
    public void getComment(){
        AnswerComment answerComment = communityQAMapper.getComment(1);
        Assert.assertEquals("你这也回答的太好了吧！1", answerComment.getComment_content());
    }

    //测试能不能给回答添加评论
    @Test
    public void addComment(){
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
    public void getAllComment(){
        List<AnswerComment> answerComments = communityQAMapper.listAllAnswerComment(1);
        Assert.assertEquals(2, answerComments.size());
        for(AnswerComment answerComment : answerComments){
            if(answerComment.getComment_id() != 1 && answerComment.getComment_id() != 3){
                Assert.fail();
            }
        }
    }
    //测试能不能给评论和回答添加映射
    @Test
    public void createAnswerCommentMap(){
        communityQAMapper.createMapBetweenAnswerAndComment(2,6,3);
        List<AnswerComment> answerComments = communityQAMapper.listAllAnswerComment(2);
        Assert.assertEquals(3, answerComments.size());
        for(AnswerComment answerComment : answerComments){
            if(answerComment.getComment_id() != 4 && answerComment.getComment_id() != 5 && answerComment.getComment_id()
            != 6){
                Assert.fail();
            }
        }
    }
    //测试能不能删除评论
    @Test
    public void deleteComment(){
        AnswerComment answerComment = communityQAMapper.getComment(1);
        answerComment.setIs_delete(1);
        communityQAMapper.deleteComment(answerComment);
        AnswerComment answerComment1 = communityQAMapper.getComment(1);
        Assert.assertNull(answerComment1);
    }

    //测试能不能拿到用户对某评论的评价
    @Test
    public void commentEvaluateStatus(){
        CommentEvaluate commentEvaluate = communityQAMapper.commentEvaluateStatus(1,1);
        Assert.assertNotNull(commentEvaluate);
        Integer evaluate_type = 1;
        Assert.assertEquals(evaluate_type, commentEvaluate.getEvaluate_type());
        CommentEvaluate commentEvaluate1 = communityQAMapper.commentEvaluateStatus(1,2);
        Assert.assertNull(commentEvaluate1);
    }

    //测试能不能拿到某个评价
    @Test
    public void getEvaluateStatus(){
        CommentEvaluate commentEvaluate = communityQAMapper.getCommentEvaluate(1);
        Integer user_id = 1;
        Integer evaluate_type = 1;
        Assert.assertEquals(user_id, commentEvaluate.getUser_id());
        Assert.assertEquals(evaluate_type, commentEvaluate.getEvaluate_type());
    }

    //测试能不能添加评价
    @Test
    public void addCommentEvaluate(){
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
    public void countCommentAgreement(){
        Integer agreement = communityQAMapper.countCommentAgreement(1);
        Integer expect = 3;
        Assert.assertEquals(expect, agreement);
    }

    //测试能不能建立评价和评论的映射
    @Test
    public void createCommentEvaluateMap(){
        communityQAMapper.createMapBetweenCommentAndEvaluate(5,2);
        Integer agreement = communityQAMapper.countCommentAgreement(2);
        Integer expect = 1;
        Assert.assertEquals(expect,agreement);
    }

    //测试能不能重新评价评论  存疑  这里假如把修改时间的那句注释掉就错了，不知道为什么
    @Test
    public void reEvaluateComment(){
        CommentEvaluate commentEvaluate = communityQAMapper.getCommentEvaluate(1);
        commentEvaluate.setEvaluate_type(3);
        commentEvaluate.setEvaluate_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.reEvaluateComment(commentEvaluate);
        Integer agreement = communityQAMapper.countCommentAgreement(1);
        Integer expect = 2;
        Assert.assertEquals(expect, agreement);
    }

}
