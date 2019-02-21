package com.youthchina.jinhao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.youthchina.dao.jinhao.CommunityQAMapper;
import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class, TransactionalTestExecutionListener.class})
@DatabaseSetup({"classpath:questions.xml", "classpath:answers.xml", "classpath:comments.xml", "classpath:discuss.xml", "classpath:videos.xml"})

public class CommunityQAMapperTest {
    @Autowired
    CommunityQAMapper communityQAMapper;

    // 测试能不能拿到某个问题
    @Test
    public void getQuestion() {
        Question question1 = communityQAMapper.getQuestion(1);
        Assert.assertEquals("第一个问题", question1.getQues_title());
        Question question = communityQAMapper.getQuestionById(1);
        Assert.assertNotNull(question);
        Assert.assertNotNull(question.getRela_id());
        User user = question.getQues_user();
        Assert.assertEquals(Integer.valueOf(1), user.getId());
        List<QuestionAnswer> questionAnswers = question.getQuestionAnswers();
        Assert.assertEquals(4, questionAnswers.size());
        for(QuestionAnswer questionAnswer : questionAnswers){
            if(questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2 &&
                    questionAnswer.getAnswer_id() != 3 && questionAnswer.getAnswer_id() != 4){
                Assert.fail();
            }
            Assert.assertNotNull(questionAnswer.getAnswer_user());
        }
    }


    //测试能不能添加问题
    @Test
    public void addQuestion() {
        Question question = new Question();
        question.setQues_title("被插入的问题");
        question.setQues_abbre("这问题是新插入的");
        question.setQues_body("这是一个新插入的问题，巴拉巴拉巴拉");
        question.setUser_anony(0);
        communityQAMapper.addQuestion(question);
        Assert.assertNotNull(question.getQues_id());
        Question createQuestion = communityQAMapper.getQuestion(question.getQues_id());
        Assert.assertNotNull(createQuestion);
        communityQAMapper.createMapBetweenQuestionAndUser(question.getQues_id(), 3, 2, 45);
        Question question1 = communityQAMapper.getQuestionById(question.getQues_id());
        Assert.assertEquals(Integer.valueOf(45), question1.getRela_id());
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
        communityQAMapper.deleteQuestion(1);
        Question newquestion = communityQAMapper.getQuestion(1);
        Assert.assertNull(newquestion);
    }


//    //测试能不能拿到问题的标签
//    @Test
//    public void getQuestionLabels() {
//        List<Label> labels = communityQAMapper.listAllQuesetionLabel(1);
//        Assert.assertEquals(3, labels.size());
//        for (Label label : labels) {
//            if (label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 5) {
//                Assert.fail();
//            }
//        }
//    }

//    //测试能不能给问题添加标签
//    @Test
//    public void addLabels() {
//        List<Integer> lab_nums = new LinkedList<>();
//        lab_nums.add(2);
//        lab_nums.add(1);
//        lab_nums.add(3);
//        communityQAMapper.addLabels(lab_nums, 2);
//        List<Label> labels = communityQAMapper.listAllQuesetionLabel(2);
//        Assert.assertEquals(3, labels.size());
//        for (Label label : labels) {
//            if (label.getLab_num() != 1 && label.getLab_num() != 3 && label.getLab_num() != 2) {
//                Assert.fail();
//            }
//        }
//    }

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
        communityQAMapper.addAttentionToQuestion(1);
        Assert.assertNotNull(questionAttention.getAtten_id());
        QuestionAttention questionAttention1 = communityQAMapper.getAttention(questionAttention.getAtten_id());
        Assert.assertNotNull(questionAttention1);
        communityQAMapper.createMapBetweenAttentionAndQuestion(4, questionAttention.getAtten_id());
        List<Question> questions = communityQAMapper.listMyAttenQuestion(1);
        Assert.assertEquals(3, questions.size());
        for (Question question : questions) {
            if (question.getQues_id() != 1 && question.getQues_id() != 2 && question.getQues_id() != 4) {
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
        communityQAMapper.reAddAttentionToQuestion(atten_id);
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

        communityQAMapper.cancelAttention(atten_id);
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


    //测试能不能拿到某个回答
    @Test
    public void getAnswer() {
        QuestionAnswer questionAnswer = communityQAMapper.getAnswer(1);
        Assert.assertEquals("这是第一个回答", questionAnswer.getAnswer_content());
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswerById(1);
        Assert.assertNotNull(questionAnswer1.getQuestion());
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
        Question question = communityQAMapper.getQuestionById(2);
        List<QuestionAnswer> questionAnswers = question.getQuestionAnswers();
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
        communityQAMapper.deleteAnswer(1);
        QuestionAnswer questionAnswer1 = communityQAMapper.getAnswer(1);
        Assert.assertNull(questionAnswer1);
    }

    //测试能不能列出用户做过的回答
    @Test
    public void listMyAnswer() {
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAnswer(1);
        Assert.assertEquals(3, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 2 && questionAnswer.getAnswer_id() != 7) {
                Assert.fail();
            }
        }
    }

    //测试能不能查看用户对回答的评价
    @Test
    public void evaluateStatus() {
        Evaluate evaluate = communityQAMapper.evaluateStatus(1, 1);
        Integer evaluate_type = 1;
        Assert.assertEquals(evaluate_type, evaluate.getEvaluate_type());
        Evaluate evaluate1 = communityQAMapper.evaluateStatus(1, 6);
        Assert.assertNull(evaluate1);
    }

    //测试能不能拿到某个回答评价
    @Test
    public void getAnswerEvaluate() {
        Evaluate evaluate = communityQAMapper.getAnswerEvaluate(1);
        Assert.assertNotNull(evaluate);
        Integer user_id = 1;
        Assert.assertEquals(user_id, evaluate.getUser_id());
    }

    //测试能不能新添加评价
    @Test
    public void evaluateAnswer() {
        Evaluate evaluate = new Evaluate();
        evaluate.setUser_id(1);
        evaluate.setEvaluate_type(1);
        evaluate.setEvaluate_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addEvaluateToAnswer(evaluate);
        Assert.assertNotNull(evaluate.getEvaluate_id());
        Evaluate evaluate1 = communityQAMapper.getAnswerEvaluate(evaluate.getEvaluate_id());
        Assert.assertNotNull(evaluate1);
    }

    //测试能不能建立回答和评价的映射以及能不能列出用户赞同过的回答
    @Test
    public void createAnswerEvaluateMap() {
        communityQAMapper.createMapBetweenAnswerAndEvaluate(10, 6);
        List<QuestionAnswer> questionAnswers = communityQAMapper.listMyAgreeAnswer(1);
        Assert.assertEquals(4, questionAnswers.size());
        for (QuestionAnswer questionAnswer : questionAnswers) {
            if (questionAnswer.getAnswer_id() != 1 && questionAnswer.getAnswer_id() != 3 &&
                    questionAnswer.getAnswer_id() != 5 && questionAnswer.getAnswer_id() != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能重新评价回答
    @Test
    public void reEvaluateAnswer() {
        Evaluate evaluate = communityQAMapper.getAnswerEvaluate(1);
        evaluate.setEvaluate_type(2);
        communityQAMapper.reEvaluateAnswer(evaluate.getEvaluate_id());
        Evaluate evaluate1 = communityQAMapper.getAnswerEvaluate(1);
        Integer evaluate_type = 2;
        Assert.assertEquals(evaluate_type, evaluate1.getEvaluate_type());
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
        Comment comment = communityQAMapper.getComment(1);
        Assert.assertEquals("你这也回答的太好了吧！1", comment.getComment_content());
    }

    //测试能不能给回答添加评论
    @Test
    public void addComment() {
        Comment comment = new Comment();
        comment.setComment_content("加一个");
        comment.setUser_id(1);
        comment.setUser_anony(1);
        comment.setComment_pub_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        comment.setComment_edit_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        comment.setIs_delete(0);
        comment.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
        communityQAMapper.addCommentToAnswer(comment);
        Assert.assertNotNull(comment.getComment_id());
        Comment comment1 = communityQAMapper.getComment(comment.getComment_id());
        Assert.assertNotNull(comment1);
    }

    //测试拿到某个回答的所有评论
    @Test
    public void getAllComment() {
        List<Comment> comments = communityQAMapper.listAllAnswerComment(1);
        Assert.assertEquals(3, comments.size());
        for (Comment comment : comments) {
            if (comment.getComment_id() != 1 && comment.getComment_id() != 3 && comment.getComment_id() != 5) {
                Assert.fail();
            }
        }
    }

    //测试能不能给评论和回答添加映射
    @Test
    public void createAnswerCommentMap() {
        communityQAMapper.createMapBetweenAnswerAndComment(2, 6, 3);
        List<Comment> comments = communityQAMapper.listAllAnswerComment(2);
        Assert.assertEquals(3, comments.size());
        for (Comment comment : comments) {
            if (comment.getComment_id() != 4 && comment.getComment_id() != 2 && comment.getComment_id()
                    != 6) {
                Assert.fail();
            }
        }
    }

    //测试能不能删除评论
    @Test
    public void deleteComment() {
        communityQAMapper.deleteComment(1);
        Comment comment1 = communityQAMapper.getComment(1);
        Assert.assertNull(comment1);
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

//    //测试能不能列出某个评论的所有讨论
//    @Test
//    public void listAllCommentDiscuss() {
//        List<Discuss> discusses = communityQAMapper.listAllCommentDiscuss(1);
//        Assert.assertEquals(3, discusses.size());
//        for (Discuss discuss : discusses) {
//            if (discuss.getDiscuss_id() != 1 && discuss.getDiscuss_id() != 3 && discuss.getDiscuss_id() != 5) {
//                Assert.fail();
//            }
//        }
//    }
//
//    //得到某个讨论
//    @Test
//    public void getDiscuss() {
//        Discuss discuss = communityQAMapper.getDiscuss(1);
//        Assert.assertEquals("SDGAGERGRGRGAGgegege1", discuss.getDiscuss_content());
//    }
//
//    //添加讨论
//    @Test
//    public void addDiscuss() {
//        Discuss discuss = new Discuss();
//        discuss.setDiscuss_content("新的讨论");
//        discuss.setDiscuss_target_id(1);
//        discuss.setDiscuss_pub_time(Timestamp.valueOf("2012-12-12 12:12:12"));
//        discuss.setUser_id(1);
//        discuss.setUser_anony(0);
//        discuss.setIs_delete(0);
//        discuss.setIs_delete_time(Timestamp.valueOf("2012-12-12 12:12:12"));
//        communityQAMapper.addDiscuss(discuss);
//        Assert.assertNotNull(discuss.getDiscuss_id());
//        Discuss discuss1 = communityQAMapper.getDiscuss(discuss.getDiscuss_id());
//        Assert.assertNotNull(discuss1);
//    }
//
//    //测试能不能建立映射
//    @Test
//    public void createDiscussCommentMap() {
//        communityQAMapper.createMapBetweenDiscussAndComment(6, 2, 3);
//        List<Discuss> discusses = communityQAMapper.listAllCommentDiscuss(2);
//        Assert.assertEquals(3, discusses.size());
//        for (Discuss discuss : discusses) {
//            if (discuss.getDiscuss_id() != 2 && discuss.getDiscuss_id() != 4 && discuss.getDiscuss_id() != 6) {
//                Assert.fail();
//            }
//        }
//    }
//
//    //测试能不能删除讨论
//    @Test
//    public void deleteDiscuss() {
//        communityQAMapper.deleteDiscuss(1);
//        Discuss discuss1 = communityQAMapper.getDiscuss(1);
//        Assert.assertNull(discuss1);
//
//    }
//
//    //测试能不能得到讨论的评价
//    @Test
//    public void countDiscussAgreement() {
//        Integer expect = 6;
//        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(1));
//    }
//
//    //测试能不能检察用户对讨论的评价状态
//    @Test
//    public void discussEvaluateStatus() {
//        DiscussEvaluate discussEvaluate = communityQAMapper.discussEvaluateStatus(3, 1);
//        Assert.assertNotNull(discussEvaluate);
//        Integer evaluate_id = 5;
//        Assert.assertEquals(evaluate_id, discussEvaluate.getEvaluate_id());
//        DiscussEvaluate discussEvaluate1 = communityQAMapper.discussEvaluateStatus(3, 2);
//        Assert.assertNull(discussEvaluate1);
//    }
//
//    //测试能不能得到一个讨论评价
//    @Test
//    public void getDiscussEvaluate() {
//        DiscussEvaluate discussEvaluate = communityQAMapper.getDiscussEvaluate(1);
//        Assert.assertEquals(Timestamp.valueOf("2018-12-06 14:36:40"), discussEvaluate.getEvaluate_time());
//    }
//
//    //测试能不能添加讨论评价
//    @Test
//    public void addDiscussEvaluate() {
//        DiscussEvaluate discussEvaluate = new DiscussEvaluate();
//        discussEvaluate.setUser_id(1);
//        discussEvaluate.setEvaluate_type(3);
//        discussEvaluate.setEvaluate_time(Timestamp.valueOf("2018-12-06 14:36:40"));
//        communityQAMapper.addEvaluateToDiscuss(discussEvaluate);
//        Assert.assertNotNull(discussEvaluate.getEvaluate_id());
//        DiscussEvaluate discussEvaluate1 = communityQAMapper.getDiscussEvaluate(discussEvaluate.getEvaluate_id());
//        Assert.assertNotNull(discussEvaluate1);
//    }
//
//    //测试能不能添加映射
//    @Test
//    public void createDiscussEvaluateMap() {
//        communityQAMapper.createMapBetweenDiscussAndEvaluate(10, 2);
//        Integer expect = 3;
//        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(2));
//    }
//
//    //测试能不能重新价
//    @Test
//    public void deleteEvaluate() {
//        DiscussEvaluate discussEvaluate = communityQAMapper.getDiscussEvaluate(1);
//        discussEvaluate.setEvaluate_type(3);
//        discussEvaluate.setEvaluate_time(Timestamp.valueOf("2018-12-06 14:36:46"));
//        communityQAMapper.reEvaluateDiscuss(discussEvaluate);
//        Integer expect = 5;
//        Assert.assertEquals(expect, communityQAMapper.countDiscussAgreement(1));
//    }


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
        video.setVideo_name("important");
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
        communityQAMapper.createMapBetweenVideoAndUser(2, 1, 1,2);
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
        communityQAMapper.deleteVideo(1);
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
    public void getVideoById() {
        Video video = communityQAMapper.getVideoById(1);
        User user = video.getUser();
        Assert.assertEquals(Integer.valueOf(2), user.getId());
        List<VideoAttention> videoAttentions = video.getVideoAttentions();
        Assert.assertEquals(3, videoAttentions.size());
        for(VideoAttention videoAttention : videoAttentions){
            if(videoAttention.getAtten_id() != 1 && videoAttention.getAtten_id() != 2 && videoAttention.getAtten_id() != 3){
                Assert.fail();
            }
        }
        List<VideoEvaluate> videoEvaluates = video.getVideoEvaluates();
        Assert.assertEquals(3, videoEvaluates.size());
        for(VideoEvaluate videoEvaluate : videoEvaluates){
            if(videoEvaluate.getEvaluate_id() != 1 && videoEvaluate.getEvaluate_id() != 3 && videoEvaluate.getEvaluate_id() != 4){
                Assert.fail();
            }
        }
        List<VideoComment> videoComments = video.getVideoComments();
        Assert.assertEquals(2, videoComments.size());
        for(VideoComment videoComment : videoComments){
            if(videoComment.getComment_id() != 1 && videoComment.getComment_id() != 3){
                Assert.fail();
            }
        }
    }

    @Test
    public void getQuestionRelaTypeAndRelaId() {
        QuestionRelaTypeAndId questionRelaTypeAndId = communityQAMapper.getQuestionRelaTypeAndRelaId(1);
        Assert.assertEquals(Integer.valueOf(1), questionRelaTypeAndId.getRela_type());
        Assert.assertEquals(Integer.valueOf(667), questionRelaTypeAndId.getRela_id());
    }

    @Test
    public void isAnswerBelongToQuestion(){
        Boolean b = communityQAMapper.isAnswerBelongToQuestion(1,1);
        Assert.assertTrue(b);
    }

    @Test
    public void getQuestionIdByTitleOrCompanyName(){
        List<Integer> quesids = communityQAMapper.getQuestionIdByTitleOrCompanyName("第二个");
        Assert.assertEquals(1, quesids.size());
        for(Integer ques_id : quesids){
            if(ques_id != 2){
                Assert.fail();
            }
        }
        List<Integer> c_quesids = communityQAMapper.getQuestionIdByTitleOrCompanyName("百度");
        Assert.assertEquals(2, c_quesids.size());
        for(Integer ques_id : c_quesids){
            if(ques_id != 4 && ques_id != 10){
                Assert.fail();
            }
        }
        List<Integer> j_quesids = communityQAMapper.getQuestionIdByTitleOrCompanyName("front");
        Assert.assertEquals(1, j_quesids.size());
        for(Integer ques_id : j_quesids){
            if(ques_id != 2){
                Assert.fail();
            }
        }
    }

    @Test
    public void getVideoIdByTitleOrCompanyName(){
        List<Integer> videoids = communityQAMapper.getVideoIdByTitleOrCompanyName("2");
        Assert.assertEquals(1, videoids.size());
        for(Integer video_id : videoids){
            if(video_id != 2){
                Assert.fail();
            }
        }
        List<Integer> c_videoids = communityQAMapper.getVideoIdByTitleOrCompanyName("大疆");
        Assert.assertEquals(1, c_videoids.size());
        for(Integer video_id : c_videoids){
            if(video_id != 1){
                Assert.fail();
            }
        }
        List<Integer> noids = communityQAMapper.getVideoIdByTitleOrCompanyName("郭德纲");
        Assert.assertEquals(0, noids.size());
    }

    @Test
    public void deleteAllAnswerOfQuestion(){
        communityQAMapper.deleteAllAnswers(1);
        Question question1 = communityQAMapper.getQuestionById(1);
        List<QuestionAnswer> questionAnswers = question1.getQuestionAnswers();
        Assert.assertEquals(0, questionAnswers.size());
    }

    @Test
    public void deleteAllCommentOfQuestion(){
        communityQAMapper.deleteAllAnswerEvaluation(1);
        communityQAMapper.deleteAllComments(1);
        Question question1 = communityQAMapper.getQuestionById(1);
        List<QuestionAnswer> questionAnswers = question1.getQuestionAnswers();
        for(QuestionAnswer questionAnswer : questionAnswers){
            List<Comment> comments = questionAnswer.getComments();
            Assert.assertEquals(0, comments.size());
            List<Evaluate> evaluates = questionAnswer.getEvaluates();
            Assert.assertEquals(0, evaluates.size());
        }
    }

    // 测试删除所有问题下面的CommentDiscuss, CommentEvaluate
    @Test
    public void deleteAllDiscussOfQuestion(){

        communityQAMapper.deleteAllCommentEvaluation(1);
        Question question1 = communityQAMapper.getQuestionById(1);
        List<QuestionAnswer> questionAnswers = question1.getQuestionAnswers();
        for(QuestionAnswer questionAnswer : questionAnswers){
            List<Comment> comments = questionAnswer.getComments();
            for(Comment comment : comments){
                List<Discuss> discusses = comment.getDiscusses();
                List<CommentEvaluate> commentEvaluates = comment.getCommentEvaluates();
                Assert.assertEquals(0, commentEvaluates.size());
                Assert.assertEquals(0, discusses.size());
            }
        }
    }

    //测试删除某个问题下讨论的所有评价
    @Test
    public void deleteAllDiscussEvaluation(){

        Question question1 = communityQAMapper.getQuestionById(1);
        List<QuestionAnswer> questionAnswers = question1.getQuestionAnswers();
        for(QuestionAnswer questionAnswer : questionAnswers){
            List<Comment> comments = questionAnswer.getComments();
            for(Comment comment : comments){
                List<Discuss> discusses = comment.getDiscusses();
                for(Discuss discuss : discusses){
                    List<DiscussEvaluate> discussEvaluates = discuss.getDiscussEvaluateList();
                    Assert.assertEquals(0, discussEvaluates.size());
                }

            }
        }
    }

    //测试删除某个问题的所有关注
    @Test
    public void deleteAllAttention(){
        communityQAMapper.deleteAllAttention(1);
        Question question = communityQAMapper.getQuestionById(1);
        List<QuestionAttention> questionAttentions = question.getQuestionAttentions();
        Assert.assertEquals(0, questionAttentions.size());
    }

    //测试删除邀请至某个问题回答的记录和相关映射
    @Test
    public void deleteAllAnswerInvitation(){
        communityQAMapper.deleteAllAnswerInvitationMap(1);
        communityQAMapper.deleteAllAnswerInvitation(1);
        AnswerInvitation answerInvitation1 = communityQAMapper.getInvitation(1);
        Assert.assertNull(answerInvitation1);
        AnswerInvitation answerInvitation2 = communityQAMapper.getInvitation(2);
        Assert.assertNull(answerInvitation2);
        AnswerInvitation answerInvitation3 = communityQAMapper.getInvitation(3);
        Assert.assertNull(answerInvitation3);
        AnswerInvitation answerInvitation5 = communityQAMapper.getInvitation(5);
        Assert.assertNull(answerInvitation5);
        Integer id1 = communityQAMapper.getInvitationMap(1);
        Assert.assertNull(id1);
        Integer id2 = communityQAMapper.getInvitationMap(2);
        Assert.assertNull(id2);
        Integer id3 = communityQAMapper.getInvitationMap(3);
        Assert.assertNull(id3);
        Integer id5 = communityQAMapper.getInvitationMap(5);
        Assert.assertNull(id5);
    }

    @Test
    public void deleteAllAnswerEvaluationByAnswerId(){
        communityQAMapper.deleteAllAnswerEvaluationByAnswerId(1);
        communityQAMapper.deleteAllCommentsByAnswerId(1);
        QuestionAnswer questionAnswer = communityQAMapper.getAnswerById(1);
        List<Evaluate> evaluates = questionAnswer.getEvaluates();
        List<Comment> comments = questionAnswer.getComments();
        Assert.assertEquals(0, evaluates.size());
        Assert.assertEquals(0, comments.size());
    }

    @Test
    public void deleteAllCommentEvaluationByAnswerId(){
        communityQAMapper.deleteAllCommentEvaluationByAnswerId(1);
        communityQAMapper.deleteAllDiscussesByAnswerId(1);
        QuestionAnswer questionAnswer = communityQAMapper.getAnswerById(1);
        List<Comment> comments = questionAnswer.getComments();
        for(Comment comment : comments){
            List<CommentEvaluate> commentEvaluates = comment.getCommentEvaluates();
            Assert.assertEquals(0, commentEvaluates.size());
            List<Discuss> discusses = comment.getDiscusses();
            Assert.assertEquals(0, discusses.size());
        }
    }

    @Test
    public void deleteAllDiscussEvaluationByAnswerId(){
        communityQAMapper.deleteAllDiscussEvaluationByAnswerId(1);
        QuestionAnswer questionAnswer = communityQAMapper.getAnswerById(1);
        List<Comment> comments = questionAnswer.getComments();
        for(Comment comment : comments){
            List<Discuss> discusses = comment.getDiscusses();
            for(Discuss discuss : discusses){
                List<DiscussEvaluate> discussEvaluates = discuss.getDiscussEvaluateList();
                Assert.assertEquals(0, discussEvaluates.size());
            }
        }
    }
}

