package com.youthchina.domain.qingyang;

import java.sql.Timestamp;

public class CompanyScale {
/* create table SYS_COMPANY_SCALE
(
	SCALE_NUM int auto_increment comment '规模编号'
		primary key,
	SCALE_CHN varchar(100) not null comment '中文描述',
	SCALE_ENG varchar(100) not null comment '英文描述',
	START_TIME timestamp not null comment '启用时间'
)
comment '企业规模表';
*/

    private Integer scaleNum;
    private String scaleChn;
    private String scaleEng;
    private Timestamp startTime;

    public Integer getScaleNum() {
        return scaleNum;
    }

    public void setScaleNum(Integer scaleNum) {
        this.scaleNum = scaleNum;
    }

    public String getScaleChn() {
        return scaleChn;
    }

    public void setScaleChn(String scaleChn) {
        this.scaleChn = scaleChn;
    }

    public String getScaleEng() {
        return scaleEng;
    }

    public void setScaleEng(String scaleEng) {
        this.scaleEng = scaleEng;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
