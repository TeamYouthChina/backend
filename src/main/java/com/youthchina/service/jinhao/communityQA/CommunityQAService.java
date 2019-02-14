package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CommunityQAService extends DomainCRUDService<Question, Integer> {

    QuestionAttention attentionQuestion(Integer ques_id, Integer user_id) throws NotFoundException;
    void cancelAttentionQuestion(Integer ques_id, Integer user_id) throws NotFoundException;
    List<Question> listMyAttenQuestion(Integer user_id) throws NotFoundException;
    List<Question> listMyQuestions(Integer user_id) throws NotFoundException;


    List<AnswerInvitation> listInvitationGot(Integer user_id) throws NotFoundException;
    List<Integer> listUsersInvitedByMeToQuestion(Integer user_id, Integer ques_id) throws NotFoundException;
    Integer invitUserToAnswer(Integer invit_user_id, Integer ques_id, Integer invited_user_ids) throws NotFoundException;
    Integer invitUsersToAnswer(Integer invit_user_id, Integer ques_id,
                          List<Integer> invited_user_ids) throws NotFoundException;
    AnswerInvitation getInvitation(Integer invit_id) throws  NotFoundException;
    Integer acceptOrRefuseInvitation(AnswerInvitation answerInvitation) throws NotFoundException;

    QuestionAnswer addAnswer(QuestionAnswer questionAnswer, Integer ques_id, Integer answer_level);
    QuestionAnswer getAnswer(Integer answer_id) throws NotFoundException;
    QuestionAnswer editAnswer(QuestionAnswer questionAnswer) throws NotFoundException;
    void deleteAnswer(Integer answer_id) throws NotFoundException;
    List<QuestionAnswer> listMyAnswers(Integer user_id) throws NotFoundException;

    Integer countAgreement(Integer answer_id);
    Integer countDisagreement(Integer answer_id);
    AnswerEvaluate evaluateStatus(Integer user_id, Integer answer_id) throws NotFoundException;
    AnswerEvaluate getAnswerEvaluate(Integer evaluate_id) throws NotFoundException;
    Integer evaluateAnswer(Integer answer_id, AnswerEvaluate answerEvaluate) throws NotFoundException;
    List<QuestionAnswer> listMyAgreeAnswer(Integer user_id) throws NotFoundException;

    List<AnswerComment> getAllAnswerComments(Integer answer_id) throws NotFoundException;
    AnswerComment getComment(Integer comment_id) throws NotFoundException;
    Integer addCommentToAnswer(Integer answer_id, AnswerComment answerComment, Integer comment_level)
            throws NotFoundException;
    void deleteComment(Integer comment_id) throws NotFoundException;

    Integer countAgreementOfComment(Integer comment_id);
    CommentEvaluate commentEvaluateStatus(Integer user_id, Integer comment_id) throws NotFoundException;
    Integer evaluateComment(Integer comment_id, CommentEvaluate commentEvaluate) throws NotFoundException;

    List<CommentDiscuss> listAllCommentDiscuss(Integer comment_id) throws NotFoundException;
    CommentDiscuss getDiscuss(Integer discuss_id) throws NotFoundException;
    Integer addDiscuss(Integer comment_id, CommentDiscuss commentDiscuss, Integer discuss_level) throws NotFoundException;
    void deleteDiscuss(Integer discuss_id) throws NotFoundException;

    Integer countAgreementOfDiscuss(Integer discuss_id);
    DiscussEvaluate discussEvaluateStatus(Integer user_id, Integer discuss_id) throws NotFoundException;
    Integer evaluateDiscuss(Integer discuss_id, DiscussEvaluate discussEvaluate) throws NotFoundException;



    Integer addVideo(Video video, Integer user_id, Integer rela_type, Integer rela_id);
    Video getVideo(Integer video_id) throws NotFoundException;
    void deleteVideo(Integer video_id) throws NotFoundException;
    List<Video> listFirstTenVideos() throws NotFoundException;
    List<Video> listAllMyVideos(Integer user_id) throws NotFoundException;

    VideoAttention videoAttentionStatus(Integer video_id, Integer user_id) throws NotFoundException;
    Integer attentionVideo(VideoAttention videoAttention, Integer video_id) throws NotFoundException;
    VideoAttention getVideoAttetion(Integer atten_id) throws NotFoundException;
    Integer cancelAttenVideo(VideoAttention videoAttention) throws NotFoundException;

    Integer commentVideo(VideoComment videoComment, Integer video_id, Integer comment_level) throws NotFoundException;
    VideoComment getVideoComment(Integer comment_id) throws NotFoundException;
    Integer deleteVideoComment(VideoComment videoComment) throws NotFoundException;

    VideoEvaluate videoEvaluateStatus(Integer video_id, Integer user_id) throws NotFoundException;
    Integer evaluateVideo(VideoEvaluate videoEvaluate, Integer video_id) throws NotFoundException;
    VideoEvaluate getVideoEvaluate(Integer evaluate_id) throws NotFoundException;

    Integer countVideoFollower(Integer video_id);
    Integer countVideoAgreement(Integer video_id);
    Integer countVideoDisAgreement(Integer video_id);
    Integer countVideoComments(Integer video_id);


    boolean isAnswerBelongToQuestion(Integer answer_id, Integer ques_id);
    List<Question> searchQuestionByTitleOrCompanyName(String searchContent) throws NotFoundException;

    List<Video> searchVideoByTitleOrCompanyName(String searchContent) throws NotFoundException;

}
