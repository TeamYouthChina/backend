package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.AdvantageLabel;

/**
 * @program: youthchina
 * @description: 学生优势标签dto
 * @author: Qinghong Wang
 * @create: 2019-04-03 13:37
 **/
public class AdvantageLabelResponseDTO {
    private Integer id;
    private String label_code;
    private String name;

    public AdvantageLabelResponseDTO(AdvantageLabel advantageLabel) {
        if(advantageLabel!=null){
            this.id=advantageLabel.getLabel_id();
            this.label_code=advantageLabel.getLabel_code();
            if(advantageLabel.getLabelInfo()!=null){
                this.name=advantageLabel.getLabelInfo().getLabel_chn();
            }

        }

    }

    public AdvantageLabelResponseDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel_code() {
        return label_code;
    }

    public void setLabel_code(String label_code) {
        this.label_code = label_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
