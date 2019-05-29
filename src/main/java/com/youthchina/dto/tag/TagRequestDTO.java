package com.youthchina.dto.tag;

/**
 * Created by tianjian on 5/25/2019.
 */
public class TagRequestDTO {
   private Integer target_id;
   private String label_code;
   private Integer target_type;

   public TagRequestDTO(){}

    public Integer getTarget_id() {
        return target_id;
    }

    public void setTarget_id(Integer target_id) {
        this.target_id = target_id;
    }

    public String getLabel_code() {
        return label_code;
    }

    public void setLabel_code(String label_code) {
        this.label_code = label_code;
    }

    public Integer getTarget_type() {
        return target_type;
    }

    public void setTarget_type(Integer target_type) {
        this.target_type = target_type;
    }
}
