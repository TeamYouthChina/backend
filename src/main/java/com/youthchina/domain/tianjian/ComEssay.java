package com.youthchina.domain.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.dto.community.EssayDTO;
import com.youthchina.dto.community.RequestEssayDTO;

import java.sql.Timestamp;

public class ComEssay {
    private Integer essay_id;
    private String essay_title;
    private String essay_abbre;
    private String essay_body;
    private Timestamp essay_pub_time;
    private Timestamp  essay_edit_time;
    private Integer is_delete;
    private Integer user_anony;

    public ComEssay(EssayDTO essayDTO){
        this.essay_id = essayDTO.getId();
        this.essay_title = essayDTO.getTitle();
        this.essay_pub_time = essayDTO.getCreat_at();
        this.essay_edit_time = essayDTO.getModified_at();
        this.user_anony =  (essayDTO.isIs_anonymous()) ? 1 : 0;
        this.essay_abbre = essayDTO.getRichTextDTO().getBraftEditorRaw();
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(essayDTO.getRichTextDTO());
            this.essay_body = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }

    }


    public ComEssay(RequestEssayDTO requestEssayDTO){
        this.essay_id = requestEssayDTO.getId();
        this.essay_title = requestEssayDTO.getTitle();
        this.essay_abbre = requestEssayDTO.getRichTextDTO().getBraftEditorRaw();
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(requestEssayDTO.getRichTextDTO());
            this.essay_body = requestJson;
        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    public ComEssay() {

    }

    public Integer getUser_anony() {
        return user_anony;
    }

    public void setUser_anony(Integer user_anony) {
        this.user_anony = user_anony;
    }

    public Integer getEssay_id() {
        return essay_id;
    }

    public void setEssay_id(Integer essay_id) {
        this.essay_id = essay_id;
    }

    public String getEssay_title() {
        return essay_title;
    }

    public void setEssay_title(String essay_title) {
        this.essay_title = essay_title;
    }

    public String getEssay_abbre() {
        return essay_abbre;
    }

    public void setEssay_abbre(String essay_abbre) {
        this.essay_abbre = essay_abbre;
    }

    public String getEssay_body() {
        return essay_body;
    }

    public void setEssay_body(String essay_body) {
        this.essay_body = essay_body;
    }

    public Timestamp getEssay_pub_time() {
        return essay_pub_time;
    }

    public void setEssay_pub_time(Timestamp essay_pub_time) {
        this.essay_pub_time = essay_pub_time;
    }

    public Timestamp getEssay_edit_time() {
        return essay_edit_time;
    }

    public void setEssay_edit_time(Timestamp essay_edit_time) {
        this.essay_edit_time = essay_edit_time;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }
}
