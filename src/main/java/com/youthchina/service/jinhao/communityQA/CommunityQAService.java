package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface CommunityQAService {

    Integer addQuestion(Question question, Integer user_id, List<Label> labels);
    Question getQuestion(Integer ques_id) throws NotFoundException;
    List<Label> getLabels(Integer ques_id) throws NotFoundException;
    Integer updateQuestion(Question question) throws NotFoundException;
    Integer deleteQuesiton(Question question) throws NotFoundException;

    List<QuestionAnswer> listAllAnswer(Integer ques_id) throws NotFoundException;

    Integer addAnswer(QuestionAnswer questionAnswer, Integer ques_id);
    QuestionAnswer getAnswer(Integer answer_id) throws NotFoundException;
    Integer editAnswer(QuestionAnswer questionAnswer) throws NotFoundException;
    Integer deleteAnswer(QuestionAnswer questionAnswer) throws NotFoundException;

    Integer isQuestionEverAttention(Integer user_id, Integer ques_id);
    Integer isQestionAttention(Integer atten_id);
    Integer attentionQuestion(Integer ques_id, QuestionAttention questionAttention) throws NotFoundException;
    Integer cancelAttentionQuestion(QuestionAttention questionAttention) throws NotFoundException;
    QuestionAttention getAttention(Integer atten_id) throws NotFoundException;
    Integer countFollwers(Integer ques_id);

    Integer countAgreement(Integer answer_id);
    Integer countDisagreement(Integer answer_id);
    Integer isEverEvaluate(Integer user_id, Integer answer_id);
    Integer evaluateStatus(Integer evaluate_id);
    Integer evaluateAnswer(Integer answer_id, AnswerEvaluate answerEvaluate) throws NotFoundException;

    List<AnswerComment> getAllAnswerComments(Integer answer_id) throws NotFoundException;
    AnswerComment getComment(Integer comment_id) throws NotFoundException;
    Integer addCommentToAnswer(Integer answer_id, AnswerComment answerComment) throws NotFoundException;
    Integer deleteComment(AnswerComment answerComment) throws NotFoundException;

    Integer countAgreementOfComment(Integer comment_id);
    Integer isEverEvaluateComment(Integer user_id, Integer comment_id);
    Integer commentEvaluateStatus(Integer evalaute_id);
    Integer evaluateComment(Integer comment_id, CommentEvaluate commentEvaluate) throws NotFoundException;

    List<CommentDiscuss> listAllCommentDiscuss(Integer comment_id) throws NotFoundException;
    CommentDiscuss getDiscuss(Integer discuss_id) throws NotFoundException;
    Integer addDiscuss(Integer comment_id, CommentDiscuss commentDiscuss) throws NotFoundException;
    Integer deleteDiscuss(CommentDiscuss commentDiscuss) throws NotFoundException;

    Integer countAgreementOfDiscuss(Integer discuss_id);
    Integer isEverEvaluateDiscuss(Integer user_id, Integer discuss_id);
    Integer discussEvaluateStatus(Integer evaluate_id);
    Integer evaluateDiscuss(Integer discuss_id, DiscussEvaluate discussEvaluate) throws NotFoundException;

    List<Question> listQuestion();
    StuInfo getStuInfo(Integer user_id);
    //功能服务
//    Integer question(Question question, User user);
//    Integer answerQuestion(Question question, QuestionAnswer questionAnswer);
//    Integer agreeAnswer(QuestionAnswer questionAnswer, AnswerAgree answerAgree);
//    Integer disagreeAnswer(QuestionAnswer questionAnswer, AnswerDisagree answerDisagree);
//    Integer replyAnswer(QuestionAnswer questionAnswer, AnswerComment answerReply);
//    Integer agreeReply(AnswerComment answerReply, CommentAgree replyAgree);
//    Integer replyReply(AnswerComment answerReply, AnswerComment answerReply1);
        List<QuestionAndPopAnswer> listAllQuestionAndPopAnswer() throws NotFoundException;
}
