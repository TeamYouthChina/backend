package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommunityQAMapper {
    //显示
    List<Question> listQuestion();


    Integer countTheFollower(Integer ques_id);

    List<QuestionAnswer> listAllQuestionAnswer(Integer ques_id);

    QuestionAnswer getAnswer(Integer answer_id);

    Integer countAgreement(Integer answer_id);

    Integer countDisagreement(Integer answer_id);

    List<AnswerComment> listAllAnswerComment(Integer answer_id);

    AnswerComment getComment(Integer comment_id);

    List<CommentDiscuss> listAllCommentDiscuss(Integer comment_id);
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



    Integer isQuestionEverAttention(@Param("user_id") Integer usr_id, @Param("ques_id") Integer ques_id);

    Integer isQuestionAttention(Integer atten_id);

    Integer addAttentionToQuestion(QuestionAttention questionAttention);

    Integer reAddAttentionToQuestion(QuestionAttention questionAttention);

    Integer cancelAttention(QuestionAttention questionAttention);

    Integer createMapBetweenAttentionAndQuestion(@Param("ques_id") Integer ques_id,
                                             @Param("questionAttention") QuestionAttention questionAttention);
    QuestionAttention getAttention(Integer atten_id);

    Integer isEverEvaluate(@Param("user_id") Integer user_id, @Param("answer_id") Integer answer_id);

    Integer evaluateStatus(Integer evaluate_id);

    Integer addEvaluateToAnswer(AnswerEvaluate answerEvaluate);

    Integer createMapBetweenAnswerAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                           @Param("answer_id") Integer answer_id);

    Integer reEvaluateAnswer(AnswerEvaluate answerEvaluate);



    Integer addCommentToAnswer(AnswerComment answerComment);

    Integer createMapBetweenAnswerAndComment(@Param("answer_id") Integer answer_id,
                                           @Param("comment_id") Integer comment_id);

    Integer deleteComment(AnswerComment answerComment);

    Integer countCommentAgreement(Integer comment_id);

    Integer isEverEvaluateComment(@Param("user_id") Integer user_id, @Param("comment_id") Integer comment_id);

    Integer commentEvaluateStatus(Integer evaluate_id);

    Integer addEvaluateToComment(CommentEvaluate commentEvaluate);

    Integer createMapBetweenCommentAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("comment_id") Integer comment_id);

    Integer reEvaluateComment(CommentEvaluate commentEvaluate);


    CommentDiscuss getDiscuss(Integer discuss_id);

    Integer addDiscuss(CommentDiscuss commentDiscuss);

    Integer createMapBetweenDiscussAndComment(@Param("discuss_id") Integer discuss_id,
                                              @Param("comment_id") Integer comment_id);

    Integer deleteDiscuss(CommentDiscuss commentDiscuss);

    Integer countDiscussAgreement(Integer discuss_id);

    Integer isEverEvaluateDiscuss(@Param("user_id") Integer user_id, @Param("discuss_id") Integer discuss_id);

    Integer discussEvaluateStatus(Integer evaluate_id);

    Integer addEvaluateToDiscuss(DiscussEvaluate discussEvaluate);

    Integer createMapBetweenDiscussAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("discuss_id") Integer discuss_id);

    Integer reEvaluateDiscuss(DiscussEvaluate discussEvaluate);

//    Integer addAgreeToComment(CommentAgree commentAgree);
//
//    Integer createMapBetweenCommentAndAgree(@Param("answerComment") AnswerComment answerComment,
//                                            @Param("commentAgree") CommentAgree commentAgree);
//    Integer addAgreeToDiscuss(DiscussAgree discussAgree);
//
//    Integer createMapBetweenDiscussAndAgree(@Param("discussAgree") DiscussAgree discussAgree,
//                                            @Param("commentDiscuss") CommentDiscuss commentDiscuss);

}
