package com.youthchina.domain.qingyang;

import java.sql.Date;

public class Job_qingyang {
    /*主键,职位ID (JOB_INFO)*/
    private Integer jobId;

    /*企业ID (job_id -> COMPANY_JOB_MAP)*/
    private Integer companyId;

    /*企业名称 (company_id -> COMPANY_INFO)*/
    private String companyName;

    /*发布HR ID (HR_JOB_MAP: job_id -> hr_id)*/
    private Integer hrId;

/*                                                      允许空值
职位ID唯一标识符		JOB_ID	        INTEGER		        否
职位名称				JOB_NAME	    VARCHAR(200)		否
职位类别编号			JOB_PROF_NUM	INTEGER		        否
职位起始时间			JOB_START_TIME	DATE		        否
职位截止时间			JOB_END_TIME	DATE		        否
是否全职				JOB_TIME	    INTEGER		        否	0-否，1-是
职位描述				JOB_DESCRIPTION	VARCHAR(200)		否
职责描述（非必填）   	JOB_DUTY	    VARCHAR(200)		是
学历要求（非必填）		JOB_REQ	        VARCHAR(200)		是
工作地点				JOB_LOCATION	VARCHAR(200)		否
职位亮点（非必填）   	JOB_HIGHLIGHT	VARCHAR(200)		是
职位薪资（非必填）		JOB_SALARY	    VARCHAR(200)		是
简历接收邮箱			CV_RECEI_MAIL	VARCHAR(200)		否
简历命名规则（非必填）	CV_NAME_RULE	VARCHAR(200)		是
职位状态				JOB_ACTIVE	    INTEGER		        否	1，2，3,4,5
*/
    private String  jobName;
    private Integer jobProfNum;
    private Date    jobStartTime;
    private Date    jobEndTime;
    private Integer jobTime;
    private String  jobDescription;
    private String  jobDuty;
    private String  jobReq;
    private String  jobLocation;
    private String  jobHighlight;
    private String  jobSalary;
    private String  cvReceiMail;
    private String  cvNameRule;
    private Integer jobActive;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getJobProfNum() {
        return jobProfNum;
    }

    public void setJobProfNum(Integer jobProfNum) {
        this.jobProfNum = jobProfNum;
    }

    public Date getJobStartTime() {
        return jobStartTime;
    }

    public void setJobStartTime(Date jobStartTime) {
        this.jobStartTime = jobStartTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public Integer getJobTime() {
        return jobTime;
    }

    public void setJobTime(Integer jobTime) {
        this.jobTime = jobTime;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDuty() {
        return jobDuty;
    }

    public void setJobDuty(String jobDuty) {
        this.jobDuty = jobDuty;
    }

    public String getJobReq() {
        return jobReq;
    }

    public void setJobReq(String jobReq) {
        this.jobReq = jobReq;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobHighlight() {
        return jobHighlight;
    }

    public void setJobHighlight(String jobHighlight) {
        this.jobHighlight = jobHighlight;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getCvReceiMail() {
        return cvReceiMail;
    }

    public void setCvReceiMail(String cvReceiMail) {
        this.cvReceiMail = cvReceiMail;
    }

    public String getCvNameRule() {
        return cvNameRule;
    }

    public void setCvNameRule(String cvNameRule) {
        this.cvNameRule = cvNameRule;
    }

    public Integer getJobActive() {
        return jobActive;
    }

    public void setJobActive(Integer jobActive) {
        this.jobActive = jobActive;
    }
}
