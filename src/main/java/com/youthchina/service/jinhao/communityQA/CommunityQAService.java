package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.jinhao.communityQA.*;

import java.util.List;

public interface CommunityQAService {

    List<QuestionAndPopAnswer> listAllQuestionAndPopAnswer();

    Integer addQuestion(Question question, Integer user_id, List<Label> labels);
    Question getQuestion(Integer ques_id);
    List<Label> getLabels(Integer ques_id);
    Integer updateQuestion(Question question);
    Integer deleteQuesiton(Question question);

    Integer addAnswer(QuestionAnswer questionAnswer, Integer ques_id);
    QuestionAnswer getAnswer(Integer answer_id);
    Integer editAnswer(QuestionAnswer questionAnswer);
    Integer deleteAnswer(QuestionAnswer questionAnswer);

    QuestionAttention isQuestionAttention(Integer user_id, Integer ques_id);
    Integer attentionQuestion(Integer ques_id, QuestionAttention questionAttention);

    List<Question> listQuestion();
    List<QuestionAnswer> listAllAnswer(Integer ques_id);
    Integer countAgreement(Integer answer_id);
    StuInfo getStuInfo(Integer user_id);
    //功能服务
//    Integer question(Question question, User user);
//    Integer answerQuestion(Question question, QuestionAnswer questionAnswer);
//    Integer agreeAnswer(QuestionAnswer questionAnswer, AnswerAgree answerAgree);
//    Integer disagreeAnswer(QuestionAnswer questionAnswer, AnswerDisagree answerDisagree);
//    Integer replyAnswer(QuestionAnswer questionAnswer, AnswerComment answerReply);
//    Integer agreeReply(AnswerComment answerReply, CommentAgree replyAgree);
//    Integer replyReply(AnswerComment answerReply, AnswerComment answerReply1);
}
