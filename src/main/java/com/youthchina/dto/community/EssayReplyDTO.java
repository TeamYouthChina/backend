package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.dto.RichTextDTO;


public class EssayReplyDTO {
    private RichTextDTO body;
    private boolean isAnonymous;

    public EssayReplyDTO(ComEssayReply comEssayReply){
        this.isAnonymous = (comEssayReply.getUser_anony()==0)? false:true;
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssayReply.getReply_content(), RichTextDTO.class);
            this.body = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    public EssayReplyDTO(){}

    public RichTextDTO getBody(){return body;}

    public void setBody(RichTextDTO body){this.body = body;}

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

}
