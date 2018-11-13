package com.youthchina.domain.jinhao;

public class CompanyAndJob {
    private Company company;
    private Job job;
    public CompanyAndJob(Company company, Job job){
        this.company = company;
        this.job = job;
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
}
