package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.jinhao.communityQA.Question;

public interface CommunityQAService {

    //功能服务
//    Integer question(Question question, User user);
//    Integer answerQuestion(Question question, QuestionAnswer questionAnswer);
//    Integer attentionQuestion(Question question, QuestionAttention questionAttention);
//    Integer agreeAnswer(QuestionAnswer questionAnswer, AnswerAgree answerAgree);
//    Integer disagreeAnswer(QuestionAnswer questionAnswer, AnswerDisagree answerDisagree);
//    Integer replyAnswer(QuestionAnswer questionAnswer, AnswerComment answerReply);
//    Integer agreeReply(AnswerComment answerReply, CommentAgree replyAgree);
//    Integer replyReply(AnswerComment answerReply, AnswerComment answerReply1);
    Integer addQuestion(Question question);
}
