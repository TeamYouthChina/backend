package com.youthchina.domain.jinhao;


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
    private List<Evaluate> evaluates;
    private List<ComReplyEvaluate> comReplyEvaluates;
    private List<Answer> answers;
    private List<Comment> comments;
    private List<ComEssayReply> comEssayReplies;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
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

    public List<ComReplyEvaluate> getComReplyEvaluates() {
        return comReplyEvaluates;
    }

    public void setComReplyEvaluates(List<ComReplyEvaluate> comReplyEvaluates) {
        this.comReplyEvaluates = comReplyEvaluates;
    }
}
