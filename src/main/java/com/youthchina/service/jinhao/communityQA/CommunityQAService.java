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

    Integer invitToAnswer(AnswerInvitation answerInvitation, Integer ques_id,
                          Integer invit_user_id, Integer invited_user_id) throws NotFoundException;
    AnswerInvitation getInvitation(Integer invit_id) throws  NotFoundException;
    Integer acceptOrRefuseInvitation(AnswerInvitation answerInvitation) throws NotFoundException;

    Integer addVideo(Video video, Integer user_id);
    Video getVideo(Integer video_id) throws NotFoundException;
    Integer deleteVideo(Video video) throws NotFoundException;

    Integer isVideoEverAttention(Integer video_id, Integer user_id);
    Integer isVideoAttention(Integer atten_id);
    Integer attentionVideo(VideoAttention videoAttention, Integer video_id) throws NotFoundException;
    VideoAttention getVideoAttetion(Integer atten_id) throws NotFoundException;
    Integer cancelAttenVideo(VideoAttention videoAttention) throws NotFoundException;

    Integer commentVideo(VideoComment videoComment, Integer video_id) throws NotFoundException;
    VideoComment getVideoComment(Integer comment_id) throws NotFoundException;
    Integer deleteVideoComment(VideoComment videoComment) throws NotFoundException;

    Integer isVideoEverEvaluate(Integer video_id, Integer user_id);
    Integer videoEvaluateStatus(Integer evaluate_id);
    Integer evaluateVideo(VideoEvaluate videoEvaluate, Integer video_id) throws NotFoundException;
    VideoEvaluate getVideoEvaluate(Integer evaluate_id) throws NotFoundException;

    Integer countVideoFollower(Integer video_id);
    Integer countVideoAgreement(Integer video_id);
    Integer countVideoDisAgreement(Integer video_id);
    Integer countVideoComments(Integer video_id);

    List<Question> listQuestion();
    StuInfo getStuInfo(Integer user_id);
    List<QuestionAndPopAnswer> listAllQuestionAndPopAnswer() throws NotFoundException;
}
