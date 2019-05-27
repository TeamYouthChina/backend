package com.youthchina.dto.tag;

/**
 * Created by tianjian on 5/25/2019.
 */
public class TagRequestDTO {
   private Integer targetId;
   private String labelCode;
   private Integer targetType;

   public TagRequestDTO(){}

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

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
