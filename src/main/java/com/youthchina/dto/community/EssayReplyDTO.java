package com.youthchina.dto.community;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.dto.RichTextDTO;


public class EssayReplyDTO {
    private RichTextDTO richTextDTO;
    private boolean isAnonymous;

    public EssayReplyDTO(ComEssayReply comEssayReply){
        this.isAnonymous = (comEssayReply.getUser_anony()==0)? false:true;
        try{
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTO richt = mapper.readValue(comEssayReply.getReply_content(), RichTextDTO.class);
            this.richTextDTO = richt;
        }catch (Exception e){
            System.out.println("Exception");
        }
    }

    public EssayReplyDTO(){}

    public RichTextDTO getRichTextDTO(){return richTextDTO;}

    public void setRichTextDTO(RichTextDTO richTextDTO){this.richTextDTO = richTextDTO;}

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

}
