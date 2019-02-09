package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.AnswerInvitation;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 1/2/19.
 */
public class QuestionDTO {
    private Integer id;
    private User creator;
    private String title;
    private String body;
    private Boolean isAnonymous;
    private Timestamp createAt;
    private List<SimpleAnswerDTO> answers;
    private AnswerInvitation invitation;
    private List<Integer> labels;
    private Integer rela_type;
    private Integer rela_id;

    public QuestionDTO(Question question) {
        this.id = question.getQues_id();
        this.creator = question.getQues_user();
        this.title = question.getQues_title();
        this.body = question.getQues_body();
        this.invitation = question.getQues_invitation();
        //isAnonymous
        this.createAt = question.getQues_pub_time();
        for(QuestionAnswer questionAnswer : question.getQuestionAnswers()) {
            this.answers.add(new SimpleAnswerDTO(questionAnswer));
        }
    }

    public QuestionDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public List<SimpleAnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<SimpleAnswerDTO> answers) {
        this.answers = answers;
    }

    public AnswerInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(AnswerInvitation invitation) {
        this.invitation = invitation;
    }

    public List<Integer> getLabels() {
        return labels;
    }

    public void setLabels(List<Integer> labels) {
        this.labels = labels;
    }

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(){
        this.rela_id = rela_id;
    }
}
