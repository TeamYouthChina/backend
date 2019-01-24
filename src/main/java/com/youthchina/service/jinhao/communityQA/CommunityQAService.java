package com.youthchina.service.jinhao.communityQA;


import com.youthchina.domain.jinhao.communityQA.*;
import com.youthchina.exception.zhongyang.NotFoundException;

import java.util.List;

public interface CommunityQAService {

    Question getQuestionInfoById(Integer ques_id) throws NotFoundException;
    Integer addQuestion(Question question, Integer user_id, List<Integer> labels, Integer rele_type, Integer rele_id);
    Question getQuestion(Integer ques_id) throws NotFoundException;
    List<Label> getLabels(Integer ques_id) throws NotFoundException;
    Integer updateQuestion(Question question) throws NotFoundException;
    Integer deleteQuesiton(Question question) throws NotFoundException;
    List<Question> listMyQuestions(Integer user_id) throws NotFoundException;

    List<QuestionAnswer> listAllAnswer(Integer ques_id) throws NotFoundException;

    Integer addAnswer(QuestionAnswer questionAnswer, Integer ques_id, Integer answer_level);
    QuestionAnswer getAnswer(Integer answer_id) throws NotFoundException;
    Integer editAnswer(QuestionAnswer questionAnswer) throws NotFoundException;
    Integer deleteAnswer(QuestionAnswer questionAnswer) throws NotFoundException;
    Integer countAnswer(Integer ques_id);
    List<QuestionAnswer> listMyAnswers(Integer user_id) throws NotFoundException;

    QuestionAttention isQuestionAttention(Integer user_id, Integer ques_id) throws NotFoundException;
    Integer attentionQuestion(Integer ques_id, QuestionAttention questionAttention) throws NotFoundException;
    Integer cancelAttentionQuestion(QuestionAttention questionAttention) throws NotFoundException;
    QuestionAttention getAttention(Integer atten_id) throws NotFoundException;
    List<Question> listMyAttenQuestion(Integer user_id) throws NotFoundException;
    Integer countFollwers(Integer ques_id);

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
    Integer deleteComment(AnswerComment answerComment) throws NotFoundException;

    Integer countAgreementOfComment(Integer comment_id);
    CommentEvaluate commentEvaluateStatus(Integer user_id, Integer comment_id) throws NotFoundException;
    Integer evaluateComment(Integer comment_id, CommentEvaluate commentEvaluate) throws NotFoundException;

    List<CommentDiscuss> listAllCommentDiscuss(Integer comment_id) throws NotFoundException;
    CommentDiscuss getDiscuss(Integer discuss_id) throws NotFoundException;
    Integer addDiscuss(Integer comment_id, CommentDiscuss commentDiscuss, Integer discuss_level) throws NotFoundException;
    Integer deleteDiscuss(CommentDiscuss commentDiscuss) throws NotFoundException;

    Integer countAgreementOfDiscuss(Integer discuss_id);
    DiscussEvaluate discussEvaluateStatus(Integer user_id, Integer discuss_id) throws NotFoundException;
    Integer evaluateDiscuss(Integer discuss_id, DiscussEvaluate discussEvaluate) throws NotFoundException;

    List<AnswerInvitation> listInvitationGot(Integer user_id) throws NotFoundException;
    List<Integer> listUsersInvitedByMeToQuestion(Integer user_id, Integer ques_id) throws NotFoundException;
    Integer invitToAnswer(AnswerInvitation answerInvitation, Integer ques_id,
                          Integer invited_user_id) throws NotFoundException;
    AnswerInvitation getInvitation(Integer invit_id) throws  NotFoundException;
    Integer acceptOrRefuseInvitation(AnswerInvitation answerInvitation) throws NotFoundException;

    Integer addVideo(Video video, Integer user_id);
    Video getVideo(Integer video_id) throws NotFoundException;
    Integer deleteVideo(Video video) throws NotFoundException;
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

    List<Question> listQuestion() throws NotFoundException;
    List<Question> listAllQuestionAndPopAnswer() throws NotFoundException;
    //Question getIntoQuestion(Integer ques_id) throws NotFoundException;
}
