package com.youthchina.dto.community.answer;

import com.youthchina.domain.jinhao.Answer;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.community.question.QuestionBasicDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.dto.util.RichTextResponseDTO;

public class SimpleAnswerResponseDTO implements ResponseDTO<Answer> {
    private RichTextResponseDTO body;
    private boolean is_anonymous;
    private UserDTO creator;
    private String modified_at;
    private String create_at;
    private QuestionBasicDTO question;
    private Integer id;
    private Integer upvoteCount;
    private Integer downvoteCount;
    private Integer attentionCount;
    private boolean isAttention;
    private Integer evaluateStatus;

    public SimpleAnswerResponseDTO(){}

    public SimpleAnswerResponseDTO(Answer answer){
        RichTextResponseDTO richt = new RichTextResponseDTO(answer.getBody());
        this.body = richt;
        this.id = answer.getId();
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
        if (answer.getIsAnony()==0)
            this.creator = new UserDTO(answer.getUser());
        else
            this.creator = null;
        this.modified_at = answer.getEditTime().toString();
        this.create_at = answer.getPubTime().toString();
        this.question = new QuestionBasicDTO(answer.getQuestion());
    }

    public RichTextResponseDTO getBody() {
        return body;
    }

    public void setBody(RichTextResponseDTO body) {
        this.body = body;
    }

    public boolean getIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public UserDTO getCreator() {
        return creator;
    }

    public void setCreator(UserDTO creator) {
        this.creator = creator;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public QuestionBasicDTO getQuestion() {
        return question;
    }

    public void setQuestion(QuestionBasicDTO question) {
        this.question = question;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public Integer getUpvoteCount() {
        return upvoteCount;
    }

    public void setUpvoteCount(Integer upvoteCount) {
        this.upvoteCount = upvoteCount;
    }

    public Integer getDownvoteCount() {
        return downvoteCount;
    }

    public void setDownvoteCount(Integer downvoteCount) {
        this.downvoteCount = downvoteCount;
    }

    public Integer getAttentionCount() {
        return attentionCount;
    }

    public void setAttentionCount(Integer attentionCount) {
        this.attentionCount = attentionCount;
    }

    public boolean isAttention() {
        return isAttention;
    }

    public void setAttention(boolean attention) {
        isAttention = attention;
    }

    public Integer getEvaluateStatus() {
        return evaluateStatus;
    }

    public void setEvaluateStatus(Integer evaluateStatus) {
        this.evaluateStatus = evaluateStatus;
    }

    @Override
    public void convertToDTO(Answer answer) {
        RichTextResponseDTO richt = new RichTextResponseDTO(answer.getBody());
        this.body = richt;
        this.id = answer.getId();
        this.is_anonymous = (answer.getIsAnony() == 0) ? false : true;
        this.creator = new UserDTO(answer.getUser());
        this.modified_at = answer.getEditTime().toString();
        this.create_at = answer.getPubTime().toString();
        this.question = new QuestionBasicDTO(answer.getQuestion());
    }
}
