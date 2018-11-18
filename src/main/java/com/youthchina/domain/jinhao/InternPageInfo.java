package com.youthchina.domain.jinhao;

public class InternPageInfo {
    private Company company;
    private Job job;
    private HR hr;
    private boolean isJobCollected;
    private boolean isCompanyCollected;

    public InternPageInfo(Company company, Job job, HR hr, boolean isJobCollected, boolean isCompanyCollected){
        this.company = company;
        this.job = job;
        this.hr = hr;
        this.isJobCollected = isJobCollected;
        this.isCompanyCollected = isCompanyCollected;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public HR getHr() {
        return hr;
    }

    public void setHr(HR hr) {
        this.hr = hr;
    }
}
