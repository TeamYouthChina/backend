package com.youthchina.domain.qingyang;

import com.youthchina.domain.Qinghong.Location;
import com.youthchina.dto.applicant.OrganizationDTO;
import com.youthchina.dto.company.CompanyRequestDTO;
import com.youthchina.util.zhongyang.HasId;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhong on 2018/12/27.
 */
public class Company implements HasId<Integer> {
    private Integer companyId;
    private String companyName;
    private String companyCode;
    private String companyIntroduc;
    private CompanyNature companyNature;
    private CompanyScale companyScale;
    private Location location;
    private Country country;
    /*
    企业ID			COMPANY_ID	        INTEGER		否
    企业名称			COMPANY_NAME	    VARCHAR(200)否
    企业三证号码		COMPANY_CODE	    VARCHAR(200)否
    企业国别			COMPANY_COUNTRY	    VARCHAR(10) 否
    企业简介(可空)	COMPANY_INTRODUC    VARCHAR(200)是
    企业规模			COMPANY_SCALE_NUM	INTEGER		否
    企业性质			COMPANY_NATURE	    VARCHAR(200)否
    企业所在地		COMPANY_LOCATION	VARCHAR(200)否
    企业邮箱(可空)	COMPANY_MAIL	    VARCHAR(200)是
    企业官网(可空)	COMPANY_WEBSITE	    VARCHAR(200)是
    成立日期(可空)	COMPANY_START_DATE	DATE		是
    企业LOGO	(可空)	COMPANY_LOGO	    VARCHAR(200)是	暂定为存储路径
    企业认证(可空)	COMPANY_VERIFY	    INTEGER		是	0-1
    录入ID	!外键!	USER_ID	            INTEGER		否
    是否删除			IS_DELETE	        INTEGER		否	0-默认不删除
    删除时间(可空)	IS_DELETE_TIME	    TIMESTAMP	是	*/
    private String companyMail;
    private String companyWebsite;
    private Date companyStartDate;
    //private String companyLogo;

    private Integer companyVerify;
    private Integer userId;
    private Integer isDelete;
    private Timestamp isDeleteTime;
    private Timestamp addTime;
    private List<Job> jobs; //TODO: Need Delete?
    private List<Logo> logoList;
    private List<CompanyPhoto> photoList;
    private Integer jobCount;
    private Boolean isCollected;


    /*行业信息*/
    private List<Industry> indList;

    /*认证信息*/
    private List<CompanyVerification> verificationList;

    private Integer collectNum;

    public Integer getCollectNum() {
        return collectNum;
    }

    public void setCollectNum(Integer collectNum) {
        this.collectNum = collectNum;
    }

    public Company(OrganizationDTO organizationDTO) {
        this.companyId = organizationDTO.getId();
        this.companyName = organizationDTO.getName();
    }

    public Company() {
    }


    public Company(CompanyRequestDTO companyRequestDTO) {
        this.companyId = companyRequestDTO.getId();
        this.companyName = companyRequestDTO.getName();
        this.location = new Location(companyRequestDTO.getLocation());
        this.companyWebsite = companyRequestDTO.getWebsite();
        this.country = new Country(companyRequestDTO.getNation());
        this.companyIntroduc = companyRequestDTO.getNote();
        this.userId = companyRequestDTO.getUserId();

        String avatarUrl = companyRequestDTO.getAvatarUrl();
        if(avatarUrl != null){
            this.logoList = new ArrayList<>();
            Logo logo = new Logo();
            logo.setDocuLocalId(avatarUrl);
            this.logoList.add(logo);
        }

        List<String> photoIdList = companyRequestDTO.getPhotoIdList();

        if(photoIdList != null && photoIdList.size() > 0){
            this.photoList = new ArrayList<>();
            for(String photoId : photoIdList){
                this.photoList.add(new CompanyPhoto(photoId));
            }
        }


        //TODO : API need add more params as shown below
        this.companyCode = "TODO"; //企业三证号码

        CompanyScale scale = new CompanyScale();
        scale.setScaleNum(1);
        this.companyScale = scale;

        CompanyNature nature = new CompanyNature();
        nature.setNatureNum(1);
        this.companyNature = nature;

        this.companyMail = "TODO@TODO.TODO";

        this.companyVerify = 0;


    }

    public Timestamp getAddTime() {
        return addTime;
    }

    public void setAddTime(Timestamp addTime) {
        this.addTime = addTime;
    }

    public List<Industry> getIndList() {
        return indList;
    }

    public void setIndList(List<Industry> indList) {
        this.indList = indList;
    }

    public List<CompanyVerification> getVerificationList() {
        return verificationList;
    }

    public void setVerificationList(List<CompanyVerification> verificationList) {
        this.verificationList = verificationList;
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

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }


    public String getCompanyIntroduc() {
        return companyIntroduc;
    }

    public void setCompanyIntroduc(String companyIntroduc) {
        this.companyIntroduc = companyIntroduc;
    }

    public CompanyNature getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(CompanyNature companyNature) {
        this.companyNature = companyNature;
    }

    public CompanyScale getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(CompanyScale companyScale) {
        this.companyScale = companyScale;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public Date getCompanyStartDate() {
        return companyStartDate;
    }

    public void setCompanyStartDate(Date companyStartDate) {
        this.companyStartDate = companyStartDate;
    }

    public Integer getCompanyVerify() {
        return companyVerify;
    }

    public void setCompanyVerify(Integer companyVerify) {
        this.companyVerify = companyVerify;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public List<Logo> getLogoList() {
        return logoList;
    }

    public void setLogoList(List<Logo> logoList) {
        this.logoList = logoList;
    }

    public List<CompanyPhoto> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<CompanyPhoto> photoList) {
        this.photoList = photoList;
    }


    public Integer getJobCount() {
        return jobCount;
    }

    public void setJobCount(Integer jobCount) {
        this.jobCount = jobCount;
    }

    public Boolean getCollected() {
        return isCollected;
    }

    public void setCollected(Boolean collected) {
        isCollected = collected;
    }

    @Override
    public Integer getId() {
        return companyId;
    }
}
