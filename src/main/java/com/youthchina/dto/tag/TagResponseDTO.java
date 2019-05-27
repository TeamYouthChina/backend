package com.youthchina.dto.tag;

import com.youthchina.domain.jinhao.Label;

public class TagResponseDTO {
    private Integer labelId;
    private String labelCode;
    private String labelChn;
    private String labelEng;

    public TagResponseDTO(){}
    public TagResponseDTO(Label label){
        this.labelId = label.getLabelId();
        this.labelCode = label.getLabelCode();
        this.labelChn = label.getLabelChn();
        this.labelEng = label.getLabelEng();
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelChn() {
        return labelChn;
    }

    public void setLabelChn(String labelChn) {
        this.labelChn = labelChn;
    }

    public String getLabelEng() {
        return labelEng;
    }

    public void setLabelEng(String labelEng) {
        this.labelEng = labelEng;
    }
}
