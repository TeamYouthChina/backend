package com.youthchina.dto.tag;

/**
 * Created by tianjian on 5/25/2019.
 */
public class TagRequestDTO {
   private Integer id;
   private Integer labelCode;
   private Integer targetType;

   public TagRequestDTO(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(Integer labelCode) {
        this.labelCode = labelCode;
    }

    public Integer getTargetType() {
        return targetType;
    }

    public void setTargetType(Integer targetType) {
        this.targetType = targetType;
    }
}
