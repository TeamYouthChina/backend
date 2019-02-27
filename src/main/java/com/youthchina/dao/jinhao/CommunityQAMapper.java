package com.youthchina.dao.jinhao;

import com.youthchina.domain.jinhao.communityQA.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CommunityQAMapper {
    void addQuestion(Question question);

    void createMapBetweenQuestionAndUser(@Param("ques_id") Integer ques_id, @Param("user_id")Integer user_id,
                                            @Param("rela_type") Integer rela_type, @Param("rela_id") Integer rela_id);

    Question getQuestion(Integer ques_id);
    void deleteQuestion(Integer ques_id);
    void deleteAllAttention(Integer ques_id);
    void deleteAllAnswers(Integer ques_id);
    void deleteAllAnswerEvaluation(Integer ques_id);
    void deleteAllAnswerInvitationMap(Integer ques_id);
    void deleteAllAnswerInvitation(Integer ques_id);
    void deleteAllComments(Integer ques_id);
    void deleteAllCommentEvaluation(Integer ques_id);
//    void deleteAllDiscusses(Integer ques_id);
//    void deleteAllDiscussEvaluation(Integer ques_id);

    void editQuestion(Question question);

    List<Question> getMyQuestions(Integer user_id); // 列出用户提出的问题

//    List<Label> listAllQuestionLabel(Integer ques_id);

//    Integer addLabels(@Param("labels") List<Integer> labels, @Param("ques_id") Integer ques_id);

    QuestionAttention getAttention(Integer atten_id);

    QuestionAttention isQuestionAttention(@Param("user_id") Integer user_id, @Param("ques_id") Integer ques_id);

    void addAttentionToQuestion(QuestionAttention questionAttention);

    Integer createMapBetweenAttentionAndQuestion(@Param("ques_id") Integer ques_id,
                                                 @Param("atten_id") Integer atten_id);

    List<Question> listMyAttenQuestion(Integer user_id);

    Integer reAddAttentionToQuestion(Integer atten_id);

    Integer cancelAttention(Integer atten_id);

    Integer countTheFollower(Integer ques_id);

    Integer addAnswerToQuestion(QuestionAnswer questionAnswer);


    QuestionAnswer getAnswer(Integer answer_id);

    Integer createMapBetweenQuestionAndAnswer(@Param("ques_id") Integer ques_id,
                                              @Param("answer_id") Integer answer_id,
                                              @Param("answer_level") Integer answer_level);

    void deleteAnswer(Integer answer_id);
    void deleteAllAnswerEvaluationByAnswerId(Integer answer_id);
    void deleteAllCommentsByAnswerId(Integer answer_id);
    void deleteAllCommentEvaluationByAnswerId(Integer answer_id);
    void deleteAllDiscussesByAnswerId(Integer answer_id);
    void deleteAllDiscussEvaluationByAnswerId(Integer answer_id);

    Integer editAnswer(QuestionAnswer questionAnswer);

    Integer countAnswer(Integer ques_id);

    List<QuestionAnswer> listMyAnswer(Integer user_id);

    Evaluate evaluateStatus(@Param("user_id") Integer user_id, @Param("answer_id") Integer answer_id);

    Evaluate getAnswerEvaluate(Integer evaluate_id);

    void addEvaluateToAnswer(Evaluate evaluate);

    void createMapBetweenAnswerAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                              @Param("answer_id") Integer answer_id);

    void reEvaluateAnswer(Integer evaluate_id);

    void deleteEvaluateAnswer(Integer evaluate_id);

    List<QuestionAnswer>  listMyAgreeAnswer(Integer user_id);

    Integer countAgreement(Integer answer_id);

    Integer countDisagreement(Integer answer_id);

    List<Comment> listAllAnswerComment(Integer answer_id);

    Comment getComment(Integer comment_id);

    Integer addCommentToAnswer(Comment comment);

    Integer createMapBetweenAnswerAndComment(@Param("answer_id") Integer answer_id,
                                             @Param("comment_id") Integer comment_id,
                                             @Param("comment_level") Integer comment_level);
    void deleteComment(Integer comment_id);
    void deleteAllCommentEvaluationByCommentId(Integer comment_id);
    void deleteAllDiscussByCommentId(Integer comment_id);
    void deleteAllDiscussEvaluateByCommentId(Integer comment_id);

    CommentEvaluate commentEvaluateStatus(@Param("user_id") Integer user_id, @Param("comment_id") Integer comment_id);

    Integer addEvaluateToComment(CommentEvaluate commentEvaluate);

    Integer createMapBetweenCommentAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("comment_id") Integer comment_id);

    Integer countCommentAgreement(Integer comment_id);

    CommentEvaluate getCommentEvaluate(Integer evaluate_id);

    void reEvaluateComment(Integer evaluate_id);

    void deleteEvaluateComment(Integer evaluate_id);

    List<Discuss> listAllCommentDiscuss(Integer comment_id);

    Discuss getDiscuss(Integer discuss_id);

    Integer addDiscuss(Discuss discuss);

    Integer createMapBetweenDiscussAndComment(@Param("discuss_id") Integer discuss_id,
                                              @Param("comment_id") Integer comment_id,
                                              @Param("discuss_level") Integer discuss_level);

    void deleteDiscuss(Integer discuss_Id);

    Integer countDiscussAgreement(Integer discuss_id);


    DiscussEvaluate discussEvaluateStatus(@Param("user_id") Integer user_id, @Param("discuss_id") Integer discuss_id);

    Integer addEvaluateToDiscuss(DiscussEvaluate discussEvaluate);

    Integer createMapBetweenDiscussAndEvaluate(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("discuss_id") Integer discuss_id);

    DiscussEvaluate getDiscussEvaluate(Integer evaluate_id);

    Integer reEvaluateDiscuss(DiscussEvaluate discussEvaluate);

    List<AnswerInvitation> listInvitationGot(Integer user_id);

    List<Integer> listUsersInvitedByMeToQuestion(@Param("user_id") Integer user_id, @Param("ques_id") Integer ques_id);

    Integer addInvitation(AnswerInvitation answerInvitation);

    Integer createMapBetweenInvitationAndQuestion(@Param("invit_id") Integer invit_id,
                                                  @Param("invited_user_id") Integer invited_user_id);
    Integer updateStatusOfInvitation(AnswerInvitation answerInvitation);

    AnswerInvitation getInvitation(Integer invit_id);

    Integer getInvitationMap(Integer invit_id);


    List<Video> listFirstTenVideos();

    List<Video> listAllMyVideos(Integer user_id);

    Integer addVideo(Video video);

    Integer createMapBetweenVideoAndUser(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id,
                                         @Param("rela_type") Integer rela_type, @Param("rela_id") Integer rela_id);

    Video getVideo(Integer video_id);

    void deleteVideo(Integer video_id);
    void deleteAllVideoAttention(Integer video_id);
    void deleteAllVideoEvaluate(Integer video_id);
    void deleteAllVideoComment(Integer video_id);

    VideoAttention videoAttentionStatus(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id);

    Integer reAddAttentionToVideo(Integer atten_id);

    Integer addAttentionToVideo(VideoAttention videoAttention);

    Integer createMapBetweenAttentionAndVideo(@Param("atten_id") Integer atten_id, @Param("video_id") Integer video_id);

    VideoAttention getVideoAttention(Integer atten_id);

    void cancelAttentionVideo(Integer atten_id);


    Integer addCommentToVideo(VideoComment videoComment);

    Integer createMapBetweenCommentAndVideo(@Param("comment_id") Integer comment_id, @Param("video_id") Integer video_id,
                                            @Param("comment_level") Integer comment_level);

    VideoComment getVideoComment(Integer comment_id);

    void deleteVideoComment(Integer comment_id);

    VideoEvaluate videoEvaluateStatus(@Param("video_id") Integer video_id, @Param("user_id") Integer user_id);

    Integer addEvaluationToVideo(VideoEvaluate videoEvaluate);

    Integer createMapBetweenEvaluationAndVideo(@Param("evaluate_id") Integer evaluate_id,
                                               @Param("video_id") Integer video_id);

    VideoEvaluate getVideoEvaluate(Integer evaluate_id);

    void reEvaluateVideo(Integer evaluate_id);

    void cancelEvaluateVideo(Integer evaluate_id);

    Integer countVideoFollwers(Integer video_id);

    Integer countVideoAgreement(Integer video_id);

    Integer countVideoDisagreement(Integer video_id);

    Integer countVideoComments(Integer video_id);

    Integer deleteQuestionLabel(Integer ques_id);


    Question getQuestionById(Integer ques_id);
    List<QuestionAnswer> getAnswersByQuestionId(Integer ques_id);
    List<Comment> getCommentsByAnswerId(Integer answer_id);
    List<Discuss> getDiscussesByCommentId(Integer comment_id);
    QuestionRelaTypeAndId getQuestionRelaTypeAndRelaId(Integer ques_id);
    QuestionAnswer getAnswerById(Integer answer_id);
    Comment getAnswerCommentById(Integer comment_id);
    Discuss getAnswerDiscussById(Integer discuss_id);
    Video getVideoById(Integer video_id);
    boolean isAnswerBelongToQuestion(@Param("answer_id") Integer answer_id, @Param("ques_id") Integer ques_id);
    List<Integer> getQuestionIdByTitleOrCompanyName(String searchContent);
    List<Integer> getVideoIdByTitleOrCompanyName(String searchContent);
    List<Video> getAllUserAttenVideos(Integer user_id);

}
