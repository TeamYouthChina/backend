package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.domain.zhongyang.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityQAMapper {
    //显示
    List<Question> listAllQuestion();

    List<Label> listAllQuesetionLabel(Question question);

    Integer countTheFollower(Question question);

    List<QuestionAnswer> listAllQuestionAnswer(Question question);

    Integer countAgreement(QuestionAnswer questionAnswer);

    List<AnswerComment> listAllAnswerComment(QuestionAnswer questionAnswer);

    List<CommentDiscuss> listAllCommentDiscuss(AnswerComment answerComment);
    // 操作
    Integer addQuestion(Question question);

    Integer createMapBetweenQuestionAndUser(@Param("question") Question question, @Param("user")User user);

    Integer addLabels(@Param("labels") List<Label> labels, @Param("question") Question question);

    Integer deleteQuestion(Question question);

    Integer editQuestion(Question question);

    QuestionAttention isAttention(@Param("user") User user, @Param("question") Question question);

    Integer addAttentionToQuestion(QuestionAttention questionAttention);

    Integer reAddAttentionToQuestion(QuestionAttention questionAttention);

    Integer cancelAttention(QuestionAttention questionAttention);

    Integer createMapBetweenAttentionAndQuestion(@Param("question") Question question,
                                             @Param("questionAttention") QuestionAttention questionAttention);

    Integer addAnswerToQuestion(QuestionAnswer questionAnswer);

    Integer createMapBetweenQuestionAndAnswer(@Param("question") Question question,
                                              @Param("questionAnswer") QuestionAnswer questionAnswer);

    Integer deleteAnswer(QuestionAnswer questionAnswer);

    Integer editAnswer(QuestionAnswer questionAnswer);

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
