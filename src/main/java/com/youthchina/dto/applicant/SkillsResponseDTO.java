package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.LabelInfo;
import com.youthchina.dto.ResponseDTO;

/**
 * @program: youthchina
 * @description: 技能返回DTO
 * @author: Qinghong Wang
 * @create: 2019-03-02 15:26
 **/
public class SkillsResponseDTO implements ResponseDTO<LabelInfo> {

    private String id;
    private String name;

    public SkillsResponseDTO(LabelInfo labelInfo) {
        this.id = labelInfo.getLabel_code();
        this.name = labelInfo.getLabel_chn();
    }

    public SkillsResponseDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public void convertToDTO(LabelInfo labelInfo) {
        this.id = labelInfo.getLabel_code();
        this.name = labelInfo.getLabel_chn();
    }
}
