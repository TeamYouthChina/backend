package com.youthchina.dto.community.article;

import com.youthchina.domain.tianjian.ComEssay;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.RichTextRequestDTO;

public class EssayRequestDTO implements RequestDTO<ComEssay> {
    private Integer id;
    private String title;
    private Integer rela_id;
    private Integer rela_type;
    private boolean is_anonymous;
    private RichTextRequestDTO body;

    public EssayRequestDTO(ComEssay comEssay) {
        this.id = comEssay.getId();
        this.title = comEssay.getTitle();
        this.is_anonymous = (comEssay.getIsAnony()==1)? true:false;
        this.body = new RichTextRequestDTO(comEssay.getBody());
        this.rela_id = comEssay.getRelaId();
        this.rela_type = comEssay.getRelaType();
    }

    public EssayRequestDTO() {
    }

    public RichTextRequestDTO getBody() {
        return body;
    }

    public void setBody(RichTextRequestDTO body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRela_id() {
        return rela_id;
    }

    public void setRela_id(Integer rela_id) {
        this.rela_id = rela_id;
    }

    public Integer getRela_type() {
        return rela_type;
    }

    public void setRela_type(Integer rela_type) {
        this.rela_type = rela_type;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    @Override
    public ComEssay convertToDomain() {
        return new ComEssay(this);
    }
}
