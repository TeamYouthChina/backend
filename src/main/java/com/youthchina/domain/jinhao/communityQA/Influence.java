package com.youthchina.domain.jinhao.communityQA;


import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComReplyEvaluate;
import com.youthchina.domain.tianjian.PersonInfluence;

import java.util.List;

public class Influence {
    private Integer user_id;
    private Student student;
    private List<ComFriendRelation> comFriendRelations;
    private List<PersonInfluence> personInfluences;
    private List<AnswerEvaluate> answerEvaluates;
    private List<CommentEvaluate> commentEvaluates;
    private List<DiscussEvaluate> discussEvaluates;
    private List<VideoEvaluate> videoEvaluates;
    private List<ComReplyEvaluate> comReplyEvaluates;
    private List<QuestionAnswer> questionAnswers;
    private List<AnswerComment> answerComments;
    private List<VideoComment> videoComments;
    private List<ComEssayReply> comEssayReplies;

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public List<AnswerComment> getAnswerComments() {
        return answerComments;
    }

    public void setAnswerComments(List<AnswerComment> answerComments) {
        this.answerComments = answerComments;
    }

    public List<VideoComment> getVideoComments() {
        return videoComments;
    }

    public void setVideoComments(List<VideoComment> videoComments) {
        this.videoComments = videoComments;
    }

    public List<ComEssayReply> getComEssayReplies() {
        return comEssayReplies;
    }

    public void setComEssayReplies(List<ComEssayReply> comEssayReplies) {
        this.comEssayReplies = comEssayReplies;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }


    public List<ComFriendRelation> getComFriendRelations() {
        return comFriendRelations;
    }

    public void setComFriendRelations(List<ComFriendRelation> comFriendRelations) {
        this.comFriendRelations = comFriendRelations;
    }

    public List<PersonInfluence> getPersonInfluences() {
        return personInfluences;
    }

    public void setPersonInfluences(List<PersonInfluence> personInfluences) {
        this.personInfluences = personInfluences;
    }

    public List<AnswerEvaluate> getAnswerEvaluates() {
        return answerEvaluates;
    }

    public void setAnswerEvaluates(List<AnswerEvaluate> answerEvaluates) {
        this.answerEvaluates = answerEvaluates;
    }

    public List<CommentEvaluate> getCommentEvaluates() {
        return commentEvaluates;
    }

    public void setCommentEvaluates(List<CommentEvaluate> commentEvaluates) {
        this.commentEvaluates = commentEvaluates;
    }

    public List<DiscussEvaluate> getDiscussEvaluates() {
        return discussEvaluates;
    }

    public void setDiscussEvaluates(List<DiscussEvaluate> discussEvaluates) {
        this.discussEvaluates = discussEvaluates;
    }

    public List<VideoEvaluate> getVideoEvaluates() {
        return videoEvaluates;
    }

    public void setVideoEvaluates(List<VideoEvaluate> videoEvaluates) {
        this.videoEvaluates = videoEvaluates;
    }

    public List<ComReplyEvaluate> getComReplyEvaluates() {
        return comReplyEvaluates;
    }

    public void setComReplyEvaluates(List<ComReplyEvaluate> comReplyEvaluates) {
        this.comReplyEvaluates = comReplyEvaluates;
    }
}
