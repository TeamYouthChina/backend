package com.youthchina.domain.qingyang;

import java.sql.Date;
import java.sql.Timestamp;

public class Job_qingyang {
    /*主键, 职位ID (JOB_INFO)*/
    private Integer jobId;

    private String  jobName;
    private Integer jobProfNum;
    private Date    jobStartTime;
    private Date    jobEndTime;

/*                                                      允许空值
职位ID 主键       JOB_ID	        INTEGER		        否
职位名称        JOB_NAME	        VARCHAR(200)		否
职位类别编号        JOB_PROF_NUM	INTEGER		        否
职位起始时间       JOB_START_TIME	DATE		        否
职位截止时间		   JOB_END_TIME	DATE		        否
是否全职			  JOB_TIME	    INTEGER		        否	0-否，1-是
职位描述		    JOB_DESCRIPTION	VARCHAR(200)		否
职责描述(可空)    JOB_DUTY	    VARCHAR(200)		是
学历要求(可空)    JOB_REQ	        VARCHAR(200)		是
工作地点         JOB_LOCATION	VARCHAR(200)		否
职位亮点(可空)    JOB_HIGHLIGHT	VARCHAR(200)		是
职位薪资(可空)	JOB_SALARY	    VARCHAR(200)		是
简历接收邮箱      CV_RECEI_MAIL	VARCHAR(200)		否
简历命名规则(可空)  CV_NAME_RULE	VARCHAR(200)		是
职位状态			JOB_ACTIVE	    INTEGER		        否	1，2，3,4,5
招聘者ID		外键		HR_ID	    INTEGER	        	否
企业ID		外键		COMPANY_ID	INTEGER	            否
是否删除				IS_DELETE	INTEGER	            否	0-默认不删除
删除时间				IS_DELETE_TIME	TIMESTAMP	    是
*/

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

    private Integer companyId;
    private Integer hrId;
    private Integer isDelete;
    private Timestamp isDeleteTime;

    private Company_qingyang company;
    private Hr_qingyang hr;

    public Company_qingyang getCompany() {
        return company;
    }

    public void setCompany(Company_qingyang company) {
        this.company = company;
    }

    public Hr_qingyang getHr() {
        return hr;
    }

    public void setHr(Hr_qingyang hr) {
        this.hr = hr;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getHrId() {
        return hrId;
    }

    public void setHrId(Integer hrId) {
        this.hrId = hrId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Timestamp getIsDeleteTime() {
        return isDeleteTime;
    }

    public void setIsDeleteTime(Timestamp isDeleteTime) {
        this.isDeleteTime = isDeleteTime;
    }
}
