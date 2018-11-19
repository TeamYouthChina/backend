package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityQAMapper {
    //显示
    List<Question> listQuestion();


    Integer countTheFollower(Question question);

    List<QuestionAnswer> listAllQuestionAnswer(Integer ques_id);

    QuestionAnswer getAnswer(Integer answer_id);

    Integer countAgreement(Integer answer_id);

    List<AnswerComment> listAllAnswerComment(QuestionAnswer questionAnswer);

    List<CommentDiscuss> listAllCommentDiscuss(AnswerComment answerComment);
    // 操作
    StuInfo getStuInfo(Integer user_id);

    //已写
    Integer addQuestion(Question question);

    Integer createMapBetweenQuestionAndUser(@Param("ques_id") Integer ques_id, @Param("user_id")Integer user_id);

    Question getQuestion(Integer ques_id);

    Integer deleteQuestion(Question question);

    Integer editQuestion(Question question);

    Integer addLabels(@Param("labels") List<Label> labels, @Param("ques_id") Integer ques_id);

    List<Label> listAllQuesetionLabel(Integer ques_id);

    Integer deleteQuestionLabel(Integer ques_id);

    Integer addAnswerToQuestion(QuestionAnswer questionAnswer);

    Integer createMapBetweenQuestionAndAnswer(@Param("ques_id") Integer ques_id,
                                              @Param("answer_id") Integer answer_id);

    Integer deleteAnswer(QuestionAnswer questionAnswer);

    Integer editAnswer(QuestionAnswer questionAnswer);



    QuestionAttention isQuestionAttention(@Param("user_id") Integer usr_id, @Param("ques_id") Integer ques_id);

    Integer addAttentionToQuestion(QuestionAttention questionAttention);

    Integer reAddAttentionToQuestion(QuestionAttention questionAttention);

    Integer cancelAttention(QuestionAttention questionAttention);

    Integer createMapBetweenAttentionAndQuestion(@Param("ques_id") Integer ques_id,
                                             @Param("questionAttention") QuestionAttention questionAttention);

    AnswerAgree isAgreed(@Param("user") User user, @Param("questionAnswer") QuestionAnswer questionAnswer);

    Integer addAgreeToAnswer(AnswerAgree answerAgree);

    Integer createMapBetweenAnswerAndAgree(@Param("answerAgree") AnswerAgree answerAgree,
                                           @Param("questionAnswer") QuestionAnswer questionAnswer);

    Integer reAgreeAnswer(AnswerAgree answerAgree);

    Integer cancelAgreeAnswer(AnswerAgree answerAgree);

    AnswerDisagree isDisAgreed(@Param("user") User user, @Param("questionAnswer") QuestionAnswer questionAnswer);

    Integer addDisagreeToAnswer(AnswerDisagree answerDisagree);

    Integer createMapBetweenAnswerAndDisagree(@Param("answerDisagree") AnswerDisagree answerDisagree,
                                                @Param("questionAnswer") QuestionAnswer questionAnswer);

    Integer reDisagreeAnswer(AnswerDisagree answerDisagree);

    Integer cancelDisagreeAnswer(AnswerDisagree answerDisagree);

    Integer addCommentToAnswer(AnswerComment answerComment);

    Integer createMapBetweenAnswerAndComment(@Param("questionAnswer") QuestionAnswer questionAnswer,
                                           @Param("answerComment") AnswerComment answerComment);

    Integer deleteComment(AnswerComment answerComment);

//    Integer addAgreeToComment(CommentAgree commentAgree);
//
//    Integer createMapBetweenCommentAndAgree(@Param("answerComment") AnswerComment answerComment,
//                                            @Param("commentAgree") CommentAgree commentAgree);

    Integer addDiscussToComment(CommentDiscuss commentDiscuss);

    Integer createMapBetweenDiscussAndComment(@Param("commentDiscuss") CommentDiscuss commentDiscuss,
                                              @Param("answerComment") AnswerComment answerComment,
                                              @Param("targetDiscuss") CommentDiscuss targetDiscuss);

    Integer deleteDiscuss(CommentDiscuss commentDiscuss);
//    Integer addAgreeToDiscuss(DiscussAgree discussAgree);
//
//    Integer createMapBetweenDiscussAndAgree(@Param("discussAgree") DiscussAgree discussAgree,
//                                            @Param("commentDiscuss") CommentDiscuss commentDiscuss);

}
