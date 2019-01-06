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

    CommentEvaluate getCommentEvaluate(Integer evaluate_id);

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

    DiscussEvaluate getDiscussEvaluate(Integer evaluate_id);

    Integer reEvaluateDiscuss(DiscussEvaluate discussEvaluate);

    Integer addInvitation(AnswerInvitation answerInvitation);

    Integer createMapBetweenInvitationAndQuestion(@Param("invit_id") Integer invit_id, @Param("ques_id") Integer ques_id,
                                                  @Param("invit_user_id") Integer invit_user_id,
                                                  @Param("invited_user_id") Integer invited_user_id);
    Integer updateStatusOfInvitation(AnswerInvitation answerInvitation);

    AnswerInvitation getInvitation(Integer invit_id);

    Integer addVideo(Video video);

    Integer createMapBetweenVedioAndUser(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id);

    Video getVideo(Integer video_id);

    Integer deleteVideo(Video video);

    Integer isEverAttentionVideo(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id);

    Integer videoAttentionStatus(Integer atten_id);

    Integer reAddAttentionToVideo(VideoAttention videoAttention);

    Integer addAttentionToVideo(VideoAttention videoAttention);

    Integer createMapBetweenAttentionAndVideo(@Param("atten_id") Integer atten_id, @Param("video_id") Integer video_id);

    VideoAttention getVideoAttention(Integer atten_id);

    Integer cancelAttentionVideo(VideoAttention videoAttention);

    Integer addCommentToVideo(VideoComment videoComment);

    Integer createMapBetweenCommentAndVideo(@Param("comment_id") Integer comment_id, @Param("video_id") Integer video_id);

    VideoComment getVideoComment(Integer comment_id);

    Integer deleteVideoComment(VideoComment videoComment);

    Integer isEverEvaluateVideo(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id);

    Integer videoEvaluateStatus(Integer evaluate_id);

    Integer addEvaluationToVideo(VideoEvaluate videoEvaluate);

    Integer createMapBetweenEvaluationAndVideo(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("video_id") Integer video_id);

    VideoEvaluate getVideoEvaluate(Integer evaluate_id);

    Integer reEvaluateVideo(VideoEvaluate videoEvaluate);

    Integer countVideoFollwers(Integer video_id);

    Integer countVideoAgreement(Integer video_id);

    Integer countVideoDisagreement(Integer video_id);

    Integer countVideoComments(Integer video_id);
}
