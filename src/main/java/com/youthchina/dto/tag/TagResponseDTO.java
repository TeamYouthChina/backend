package com.youthchina.dto.tag;

import com.youthchina.domain.jinhao.Label;

public class TagResponseDTO {
    private Integer label_id;
    private String label_code;
    private String label_chn;
    private String label_eng;

    public TagResponseDTO(){}
    public TagResponseDTO(Label label){
        this.label_id = label.getLabelId();
        this.label_code = label.getLabelCode();
        this.label_chn = label.getLabelChn();
        this.label_eng = label.getLabelEng();
    }

    public Integer getLabel_id() {
        return label_id;
    }

    public void setLabel_id(Integer label_id) {
        this.label_id = label_id;
    }

    public String getLabel_code() {
        return label_code;
    }

    public void setLabel_code(String label_code) {
        this.label_code = label_code;
    }

    public String getLabel_chn() {
        return label_chn;
    }

    public void setLabel_chn(String label_chn) {
        this.label_chn = label_chn;
    }

    public String getLabel_eng() {
        return label_eng;
    }

    public void setLabel_eng(String label_eng) {
        this.label_eng = label_eng;
    }
}
