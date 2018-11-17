package com.youthchina.domain.qingyang;

import java.util.List;

public class JobOfCompany {
    /*
     * 企业ID
     */
    private String company_id;

//    /*
//     * 企业名称
//     */
//    private String company_name;
//    public String getCompany_name() {
//        return company_name;
//    }
//
//    public void setCompany_name(String company_name) {
//        this.company_name = company_name;
//    }

    /*
     * 该企业的职位集合
     */
    private List<Job> jobList;

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public List<Job> getJobList() {
        return jobList;
    }

    public void setJobList(List<Job> jobList) {
        this.jobList = jobList;
    }
}
