package com.youthchina.domain.Qinghong;

import com.youthchina.domain.qingyang.Company;

import java.util.Date;

/**
 * @program: youthchina
 * @description: 收藏公司实体
 * @author: Qinghong Wang
 * @create: 2018-11-29 16:55
 **/
public class CompCollect {
    private Integer collect_id;
    private Integer company_id;
    private Date company_coll_time;
    private Integer stu_id;
    private Boolean is_delete;
    private Date is_delete_time;
    private Company company;


    public Integer getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Integer collect_id) {
        this.collect_id = collect_id;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Date getCompany_coll_time() {
        return company_coll_time;
    }

    public void setCompany_coll_time(Date company_coll_time) {
        this.company_coll_time = company_coll_time;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Boolean getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Boolean is_delete) {
        this.is_delete = is_delete;
    }

    public Date getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Date is_delete_time) {
        this.is_delete_time = is_delete_time;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
