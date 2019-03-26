package com.youthchina.dto.community.article;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youthchina.domain.tianjian.ComEssayReply;
import com.youthchina.dto.util.RichTextDTOResponse;


public class EssayReplyRequestDTO {
    private RichTextDTOResponse body;
    private boolean isAnonymous;

    public EssayReplyRequestDTO(ComEssayReply comEssayReply) {
        this.isAnonymous = (comEssayReply.getUser_anony() == 0) ? false : true;
        try {
            ObjectMapper mapper = new ObjectMapper();
            RichTextDTOResponse richt = mapper.readValue(comEssayReply.getReply_content(), RichTextDTOResponse.class);
            this.body = richt;
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }

    public EssayReplyRequestDTO() {
    }

    public RichTextDTOResponse getBody() {
        return body;
    }

    public void setBody(RichTextDTOResponse body) {
        this.body = body;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

}
