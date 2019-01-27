package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class CompanyNature {
    /*create table SYS_COMPANY_NATURE
(
	NATURE_NUM int auto_increment comment '性质编号'
		primary key,
	NATURE_CHN varchar(100) not null comment '中文描述',
	NATURE_ENG varchar(100) not null comment '英文描述',
	NATURE_DETAIL varchar(100) null comment '性质细化',
	START_TIME timestamp not null comment '启用时间'
)
comment '企业性质表';
*/

    private Integer natureNum;
    private String natureChn;
    private String natureEng;
    private String natureDetail;
    private Timestamp startTime;

    public Integer getNatureNum() {
        return natureNum;
    }

    public void setNatureNum(Integer natureNum) {
        this.natureNum = natureNum;
    }

    public String getNatureChn() {
        return natureChn;
    }

    public void setNatureChn(String natureChn) {
        this.natureChn = natureChn;
    }

    public String getNatureEng() {
        return natureEng;
    }

    public void setNatureEng(String natureEng) {
        this.natureEng = natureEng;
    }

    public String getNatureDetail() {
        return natureDetail;
    }

    public void setNatureDetail(String natureDetail) {
        this.natureDetail = natureDetail;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
