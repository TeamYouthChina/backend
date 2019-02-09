package com.youthchina.dto.community;

import com.youthchina.domain.jinhao.communityQA.AnswerInvitation;
import com.youthchina.domain.jinhao.communityQA.Question;
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
    private Integer isAnonymous;
    private Timestamp createAt;
    private List<SimpleAnswerDTO> answers;
    private AnswerInvitation answerInvitation;
    private List<Integer> lables;
    private Integer rela_type;
    private Integer rela_id;


    public QuestionDTO(Question question){
        this.id = question.getQues_id();
        this.creator= question.getQues_user();
        this.title = question.getQues_title();
        this.body =question.getQues_body();
        this.isAnonymous = question.getUser_anony();
        this.createAt = question.getQues_pub_time();
        for(int i =0; i < question.getQuestionAnswers().size(); i++){
            this.answers.add(new SimpleAnswerDTO(question.getQuestionAnswers().get(i)));
        }
        this.answerInvitation = question.getAnswerInvitation();
        if(question.getRela_type() == 2){
            this.rela_id = question.getCompany().getCompanyId();
        }else if (question.getRela_type() == 3) {
            this.rela_id = question.getJob().getJobId();
        }
        this.rela_type = question.getRela_type();
        this.lables = question.getLableIds();
    }

    public List<Integer> getLables(){ return lables; }

    public void setLables(List<Integer> lables){this.lables = lables;}

    public Integer getRele_type(){ return rela_type; }

    public void setRele_type(Integer rele_type){ this.rela_type = rele_type; }

    public Integer getRele_id(){return rela_id;}

    public void setRele_id(Integer rele_id){this.rela_id = rele_id;}

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

    public AnswerInvitation getAnswerInvitation() { return answerInvitation; }

    public void setJob(AnswerInvitation answerInvitation) { this.answerInvitation = answerInvitation; }
}
