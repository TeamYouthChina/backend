package com.youthchina.domain.jinhao.communityQA;


import com.youthchina.domain.Qinghong.Student;
import com.youthchina.domain.tianjian.ComFriendRelation;
import com.youthchina.domain.tianjian.ComReplyEvaluate;

import java.util.List;

public class PersonInfluence {
    private Integer user_id;
    private Integer pers_profile;
    private Integer pers_ident_verify;
    private Integer pers_university;
    private Integer pers_work;
    private Integer pers_friend_count;
    private Integer pers_friend_quality;
    private Integer pers_interaction;
    private Integer pers_like_count;
    private Integer pers_posi_evaluate;
    private Student student;
    private List<ComFriendRelation> comFriendRelations;
    private List<PersonInfluence> personInfluences;
    private List<AnswerEvaluate> answerEvaluates;
    private List<CommentEvaluate> commentEvaluates;
    private List<DiscussEvaluate> discussEvaluates;
    private List<VideoEvaluate> videoEvaluates;
    private List<ComReplyEvaluate> comReplyEvaluates;


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getPers_profile() {
        return pers_profile;
    }

    public void setPers_profile(Integer pers_profile) {
        this.pers_profile = pers_profile;
    }

    public Integer getPers_ident_verify() {
        return pers_ident_verify;
    }

    public void setPers_ident_verify(Integer pers_ident_verify) {
        this.pers_ident_verify = pers_ident_verify;
    }

    public Integer getPers_university() {
        return pers_university;
    }

    public void setPers_university(Integer pers_university) {
        this.pers_university = pers_university;
    }

    public Integer getPers_work() {
        return pers_work;
    }

    public void setPers_work(Integer pers_work) {
        this.pers_work = pers_work;
    }

    public Integer getPers_friend_count() {
        return pers_friend_count;
    }

    public void setPers_friend_count(Integer pers_friend_count) {
        this.pers_friend_count = pers_friend_count;
    }

    public Integer getPers_friend_quality() {
        return pers_friend_quality;
    }

    public void setPers_friend_quality(Integer pers_friend_quality) {
        this.pers_friend_quality = pers_friend_quality;
    }

    public Integer getPers_interaction() {
        return pers_interaction;
    }

    public void setPers_interaction(Integer pers_interaction) {
        this.pers_interaction = pers_interaction;
    }

    public Integer getPers_like_count() {
        return pers_like_count;
    }

    public void setPers_like_count(Integer pers_like_count) {
        this.pers_like_count = pers_like_count;
    }

    public Integer getPers_posi_evaluate() {
        return pers_posi_evaluate;
    }

    public void setPers_posi_evaluate(Integer pers_posi_evaluate) {
        this.pers_posi_evaluate = pers_posi_evaluate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
