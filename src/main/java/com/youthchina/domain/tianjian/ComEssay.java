package com.youthchina.domain.tianjian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.community.article.EssayResponseDTO;
import com.youthchina.dto.community.article.EssayRequestDTO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ComEssay {
    private Integer essayId;
    private String essayTitle;
    private String essayAbbre;
    private ComRichText essayBody;
    private Timestamp essayPubTime;
    private Timestamp  essayEditTime;
    private Integer isDelete;
    private Integer userAnony;
    private Integer userId;
    private Integer relaType;
    private Integer relaId;
    private List<Comment> essayComment = new ArrayList<>();

    public ComEssay(EssayResponseDTO essayResponseDTO){
        this.essayBody = new ComRichText();
        this.essayId = essayResponseDTO.getId();
        this.essayTitle = essayResponseDTO.getTitle();
        this.essayPubTime = essayResponseDTO.getCreate_at();
        this.essayEditTime = essayResponseDTO.getModified_at();
        this.userAnony =  (essayResponseDTO.isIs_anonymous()) ? 1 : 0;
        this.essayAbbre = essayResponseDTO.getBody().getPreviewText();
        this.essayBody.setRela_type(1);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(essayResponseDTO.getBody());
            this.essayBody.setJson_content(requestJson);
            this.essayBody.setText_content(requestJson);
        }catch (Exception e){
            System.out.println("Exception");
        }

    }


    public ComEssay(EssayRequestDTO essayRequestDTO){
        this.essayBody = new ComRichText();
        this.essayId = essayRequestDTO.getId();
        this.essayTitle = essayRequestDTO.getTitle();
        this.essayAbbre = essayRequestDTO.getBody().getPreviewText();
        this.essayBody.setRela_type(1);
        this.essayBody.setCompile_type(1);
        this.essayBody.setRela_id(essayRequestDTO.getCompany_id());
        try{
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            java.lang.String requestJson = ow.writeValueAsString(essayRequestDTO.getBody());
            this.essayBody.setJson_content(requestJson);
            this.essayBody.setText_content(requestJson);
        }catch (Exception e){
            System.out.println("Exception");
        }
        this.userAnony = (essayRequestDTO.isIs_anonymous())? 1:0;
    }

    public ComEssay() {

    }

    public Integer getEssayId() {
        return essayId;
    }

    public void setEssayId(Integer essayId) {
        this.essayId = essayId;
    }

    public String getEssayTitle() {
        return essayTitle;
    }

    public void setEssayTitle(String essayTitle) {
        this.essayTitle = essayTitle;
    }

    public String getEssayAbbre() {
        return essayAbbre;
    }

    public void setEssayAbbre(String essayAbbre) {
        this.essayAbbre = essayAbbre;
    }

    public ComRichText getEssayBody() {
        return essayBody;
    }

    public void setEssayBody(ComRichText essayBody) {
        this.essayBody = essayBody;
    }

    public Timestamp getEssayPubTime() {
        return essayPubTime;
    }

    public void setEssayPubTime(Timestamp essayPubTime) {
        this.essayPubTime = essayPubTime;
    }

    public Timestamp getEssayEditTime() {
        return essayEditTime;
    }

    public void setEssayEditTime(Timestamp essayEditTime) {
        this.essayEditTime = essayEditTime;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getUserAnony() {
        return userAnony;
    }

    public void setUserAnony(Integer userAnony) {
        this.userAnony = userAnony;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRelaType() {
        return relaType;
    }

    public void setRelaType(Integer relaType) {
        this.relaType = relaType;
    }

    public Integer getRelaId() {
        return relaId;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }


}
