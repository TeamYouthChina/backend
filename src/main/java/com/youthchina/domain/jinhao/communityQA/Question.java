package com.youthchina.domain.jinhao.communityQA;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.community.question.QuestionResponseDTO;
import com.youthchina.dto.community.question.QuestionRequestDTO;
import com.youthchina.util.zhongyang.HasId;

import java.sql.Timestamp;
import java.util.List;

public class Question implements HasId<Integer> {
    private Integer ques_id;
    private String ques_title;
    private String ques_abbre;
    private String ques_body;
    private Timestamp ques_pub_time;
    private Timestamp ques_edit_time;
    private Integer is_delete;
    private Timestamp is_delete_time;
    private Integer user_anony;
    private User ques_user;
    private List<QuestionAttention> questionAttentions;
    private List<QuestionAnswer> questionAnswers;
    private List<Label> labels;
    private AnswerInvitation ques_invitation;
    private List<Integer> labelIds;
    private Integer rela_type;
    private Integer rela_id;

    public Question(QuestionResponseDTO questionResponseDTO){
        this.ques_id = questionResponseDTO.getId();
        this.ques_user = new User(questionResponseDTO.getCreator());
        this.ques_title = questionResponseDTO.getTitle();
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(questionResponseDTO.getBody());
            this.ques_body = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }

        this.user_anony = (questionResponseDTO.getIs_anonymous() ? 1 : 0);
        this.ques_pub_time = questionResponseDTO.getCreate_at();
        this.ques_edit_time = questionResponseDTO.getModified_at();
        this.rela_type = questionResponseDTO.getRela_type();
        this.rela_id = questionResponseDTO.getRela_id();
        this.ques_abbre = questionResponseDTO.getBody().getPreviewText();
        //if(questionResponseDTO.getAnswers() != null) {
         //   for(RequestSimpleAnswerDTO simpleAnswerDTO : questionResponseDTO.getAnswers()) {
         //       this.questionAnswers.add(new QuestionAnswer(simpleAnswerDTO));
         //   }
        //}
    }

    public Question(QuestionRequestDTO questionRequestDTO) {
        this.ques_title = questionRequestDTO.getTitle();
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(questionRequestDTO.getBody());
            this.ques_body = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.user_anony = (questionRequestDTO.getIs_anonymous() ? 1 : 0);
        this.rela_type = questionRequestDTO.getRela_type();
        this.rela_id = questionRequestDTO.getRela_id();
        this.ques_abbre = questionRequestDTO.getBody().getPreviewText();
    }

    public Question(){}

    public Integer getId() {
        return ques_id;
    }

    public void setId(Integer ques_id) {
        this.ques_id = ques_id;
    }

    public Integer getQues_id() {
        return ques_id;
    }

    public void setQues_id(Integer ques_id) {
        this.ques_id = ques_id;
    }

    public String getQues_title() {
        return ques_title;
    }

    public void setQues_title(String ques_title) {
        this.ques_title = ques_title;
    }

    public String getQues_abbre() {
        return ques_abbre;
    }

    public void setQues_abbre(String ques_abbre) {
        this.ques_abbre = ques_abbre;
    }

    public String getQues_body() {
        return ques_body;
    }

    public void setQues_body(String ques_body) {
        this.ques_body = ques_body;
    }

    public Timestamp getQues_pub_time() {
        return ques_pub_time;
    }

    public void setQues_pub_time(Timestamp ques_pub_time) {
        this.ques_pub_time = ques_pub_time;
    }

    public Timestamp getQues_edit_time() {
        return ques_edit_time;
    }

    public void setQues_edit_time(Timestamp ques_edit_time) {
        this.ques_edit_time = ques_edit_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }

    public Integer getUser_anony() {
        return user_anony;
    }

    public void setUser_anony(Integer user_anony) {
        this.user_anony = user_anony;
    }

    public User getQues_user() {
        return ques_user;
    }

    public void setQues_user(User ques_user) {
        this.ques_user = ques_user;
    }

    public List<QuestionAttention> getQuestionAttentions() {
        return questionAttentions;
    }

    public void setQuestionAttentions(List<QuestionAttention> questionAttentions) {
        this.questionAttentions = questionAttentions;
    }

    public List<QuestionAnswer> getQuestionAnswers() {
        return questionAnswers;
    }

    public void setQuestionAnswers(List<QuestionAnswer> questionAnswers) {
        this.questionAnswers = questionAnswers;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public AnswerInvitation getQues_invitation() {
        return ques_invitation;
    }

    public void setQues_invitation(AnswerInvitation ques_invitation) {
        this.ques_invitation = ques_invitation;
    }

    public List<Integer> getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(List<Integer> labelIds) {
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

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }
}
