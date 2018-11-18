package com.youthchina.domain.jinhao;

import java.sql.Timestamp;

public class CompanyCollect {
    private Integer collect_id;
    private Integer company_id;
    private Timestamp company_coll_time;

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

    public Timestamp getCompany_coll_time() {
        return company_coll_time;
    }

    public void setCompany_coll_time(Timestamp company_coll_time) {
        this.company_coll_time = company_coll_time;
    }
}
