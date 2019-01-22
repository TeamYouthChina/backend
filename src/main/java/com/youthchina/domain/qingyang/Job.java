package com.youthchina.domain.qingyang;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Job {
    /*主键, 职位ID (JOB_INFO)*/
    private Integer jobId;

    private String  jobName;
    private Integer jobProfNum;
    private Date    jobStartTime;
    private Date    jobEndTime;

/*                                                      允许空值
职位ID		主键		JOB_ID	INTEGER	是	否
职位名称				JOB_NAME	VARCHAR(200)	否	否
职位类别编号			JOB_PROF_NUM	INTEGER	否	否
职位起始日期			JOB_START_TIME	DATE	否	否
职位截止日期			JOB_END_TIME	DATE	否	否
职位性质				JOB_TIME	INTEGER	否	否	1-实习，2-兼职，3-全职
职位描述				JOB_DESCRIPTION	VARCHAR(200)	否	否
职责描述				JOB_DUTY	VARCHAR(200)	否	是
职位亮点				JOB_HIGHLIGHT	VARCHAR(200)	否	是
职位薪资下限			JOB_SALARY_FLOOR	INTEGER	否	是
职位薪资上限			JOB_SALARY_CAP	INTEGER	否	是
职位链接				JOB_LINK	VARCHAR(500)	否	是
简历接收邮箱			CV_RECEI_MAIL	VARCHAR(200)	否	否
简历命名规则			CV_NAME_RULE	VARCHAR(200)	否	是
职位状态				JOB_ACTIVE	INTEGER	否	否	1，2，3,4,5
问答ID		外键		QUES_ID	INTEGER	否	否
招聘者ID		外键		HR_ID	INTEGER	否	否
企业ID		外键		COMPANY_ID	INTEGER	否	否
是否删除		0	    IS_DELETE	INTEGER	否	否	0-默认不删除
删除时间		NULL	IS_DELETE_TIME	TIMESTAMP	否	是
*/

    private Integer jobTime;
    private String  jobDescription;
    private String  jobDuty;
    private String  jobHighlight;
    private Integer jobSalaryFloor;
    private Integer jobSalaryCap;
    private String  cvReceiMail;
    private String  cvNameRule;
    private Integer jobActive;
    private List<JobLocation> jobLocationList;
    private List<Integer> jobReqList;
    private List<Industry> industries;



    private Integer isDelete;
    private Timestamp isDeleteTime;

    private Company company;
    private Hr hr;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Hr getHr() {
        return hr;
    }

    public void setHr(Hr hr) {
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

    public List<Integer> getJobReqList() {
        return jobReqList;
    }

    public void setJobReqList(List<Integer> jobReqList) {
        this.jobReqList = jobReqList;
    }

    public List<JobLocation> getJobLocationList() {
        return jobLocationList;
    }

    public void setJobLocationList(List<JobLocation> jobLocationList) {
        this.jobLocationList = jobLocationList;
    }

    public String getJobHighlight() {
        return jobHighlight;
    }

    public void setJobHighlight(String jobHighlight) {
        this.jobHighlight = jobHighlight;
    }

    public Integer getJobSalaryFloor() {
        return jobSalaryFloor;
    }

    public void setJobSalaryFloor(Integer jobSalaryFloor) {
        this.jobSalaryFloor = jobSalaryFloor;
    }

    public Integer getJobSalaryCap() {
        return jobSalaryCap;
    }

    public void setJobSalaryCap(Integer jobSalaryCap) {
        this.jobSalaryCap = jobSalaryCap;
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

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
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
