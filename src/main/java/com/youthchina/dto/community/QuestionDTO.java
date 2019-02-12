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
    private String abbre;
    private Integer isAnonymous;
    private Timestamp createAt;
    private Timestamp editTime;
    private List<SimpleAnswerDTO> answers;
    private AnswerInvitation invitation;
    private List<Integer> labelIds;
    private Integer rela_type;
    private Integer rela_id;

    public QuestionDTO(Question question) {
        this.id = question.getQues_id();
        this.creator = question.getQues_user();
        this.title = question.getQues_title();
        this.body = question.getQues_body();
        this.abbre = question.getQues_abbre();
        this.invitation = question.getQues_invitation();
        this.isAnonymous =question.getUser_anony();
        this.createAt = question.getQues_pub_time();
        this.editTime = question.getQues_edit_time();
        this.labelIds = question.getLabelIds();
        this.rela_type = question.getRela_type();
        if(question.getQuestionAnswers() != null){
            for(QuestionAnswer questionAnswer : question.getQuestionAnswers()) {
                this.answers.add(new SimpleAnswerDTO(questionAnswer));
            }
        }
    }

    public QuestionDTO(){}

    public Timestamp getEditTime(){return editTime;}

    public void setEditTime(Timestamp editTime){this.editTime = editTime;}

    public String getAbbre(){return abbre;}

    public void setAbbre(String abbre){this.abbre = abbre;}

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

    public Integer getAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(Integer anonymous) {
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

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Integer> labels) {
        this.labelIds = labelIds;
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
