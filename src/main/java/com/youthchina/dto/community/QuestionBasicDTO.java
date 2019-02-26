package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.jinhao.communityQA.AnswerInvitation;
import com.youthchina.domain.jinhao.communityQA.Question;
import com.youthchina.domain.jinhao.communityQA.QuestionAnswer;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RichTextDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoyiwang on 2/24/19.
 */

public class QuestionBasicDTO {
    private Integer id;
    private User creator;
    private String title;
    private boolean is_anonymous;
    private Timestamp create_at;
    private Timestamp modified_at;
    private Integer rela_type;
    private Integer rela_id;
    private RichTextDTO body;

    public QuestionBasicDTO(Question question) {
        this.id = question.getQues_id();
        this.creator = question.getQues_user();
        this.title = question.getQues_title();
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(question.getQues_body(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }

        //this.invitation = question.getQues_invitation();
        this.is_anonymous = (question.getUser_anony()==1 ? true : false);
        this.create_at = question.getQues_pub_time();
        this.modified_at = question.getQues_edit_time();
        this.rela_type = question.getRela_type();
        this.rela_id = question.getRela_id();

    }

    public QuestionBasicDTO(){}

    public RichTextDTO getBody(){return body;}

    public void setBody(RichTextDTO body){this.body = body;}

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

    public boolean getAnonymous() {
        return is_anonymous;
    }

    public void setAnonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    /*public AnswerInvitation getInvitation() {
        return invitation;
    }

    public void setInvitation(AnswerInvitation invitation) {
        this.invitation = invitation;
    }*/

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id){
        this.rela_id = rela_id;
    }

    public Timestamp getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Timestamp create_at) {
        this.create_at = create_at;
    }

    public Timestamp getModified_at() {
        return modified_at;
    }

    public void setModified_at(Timestamp modified_at) {
        this.modified_at = modified_at;
    }
}