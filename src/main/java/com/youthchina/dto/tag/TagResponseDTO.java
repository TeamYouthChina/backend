package com.youthchina.dto.tag;

import com.youthchina.domain.jinhao.Label;

public class TagResponseDTO {
    private Integer targetId;
    private String labelCode;
    private String labelChn;
    private String labelEng;

    public TagResponseDTO(){}
    public Integer getTargetId() {
        return targetId;
    }

    public void setTargetId(Integer targetId) {
        this.targetId = targetId;
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
