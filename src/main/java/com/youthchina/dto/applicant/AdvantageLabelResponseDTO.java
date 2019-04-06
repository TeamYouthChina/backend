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

    public AdvantageLabelResponseDTO(AdvantageLabel advantageLabel) {
        this.id = advantageLabel.getLabel_id();
    }

    public AdvantageLabelResponseDTO() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
