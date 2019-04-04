package com.youthchina.domain.Qinghong;

/**
 * @program: youthchina
 * @description: 应聘者意向工作类型
 * @author: Qinghong Wang
 * @create: 2019-03-23 14:05
 **/
public class PreferJobType {
    private Integer pre_type_id;
    private Integer job_type;
    private Integer pre_prof_id;
    private Integer stu_id;
    private Integer is_delete;
    private Integer is_delete_time;


    public Integer getPre_type_id() {
        return pre_type_id;
    }

    public void setPre_type_id(Integer pre_type_id) {
        this.pre_type_id = pre_type_id;
    }

    public Integer getJob_type() {
        return job_type;
    }

    public void setJob_type(Integer job_type) {
        this.job_type = job_type;
    }

    public Integer getPre_prof_id() {
        return pre_prof_id;
    }

    public void setPre_prof_id(Integer pre_prof_id) {
        this.pre_prof_id = pre_prof_id;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Integer is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
