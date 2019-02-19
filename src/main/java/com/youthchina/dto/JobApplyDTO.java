package com.youthchina.dto;

import com.youthchina.domain.Qinghong.JobApply;

/**
 * @program: youthchina
 * @description: 职位申请DTO
 * @author: Qinghong Wang
 * @create: 2019-02-17 15:37
 **/
public class JobApplyDTO {
    private Integer id;
    private SimpleJobDTO position;

    public JobApplyDTO(JobApply jobApply) {
        this.id=jobApply.getApply_id();
        this.position=new SimpleJobDTO(jobApply.getJob());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SimpleJobDTO getPosition() {
        return position;
    }

    public void setPosition(SimpleJobDTO position) {
        this.position = position;
    }
}
