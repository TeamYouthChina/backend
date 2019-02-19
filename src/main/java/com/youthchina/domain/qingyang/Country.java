package com.youthchina.domain.qingyang;

import com.youthchina.dto.NationDTO;

import java.sql.Timestamp;

public class Country {
    /*create table SYS_COUNTRY
(
	COUNTRY_ABBRE varchar(10) not null comment '国家简称'
		primary key,
	COUNTRY_CHN varchar(100) not null comment '中文描述',
	COUNTRY_ENG varchar(100) not null comment '英文描述',
	START_TIME timestamp not null comment '启用时间',
	IS_DELETE int default 0 null comment '是否删除',
	IS_DELETE_TIME timestamp null comment '删除时间'
)
comment '国别表';
*/

    private String countryAbbre;
    private String countryChn;
    private String countryEng;
    private Timestamp startTime;

    public Country() {
    }

    public Country(NationDTO nation) {
        this.setCountryAbbre(nation.getCountryAbbre());
    }

    public String getCountryAbbre() {
        return countryAbbre;
    }

    public void setCountryAbbre(String countryAbbre) {
        this.countryAbbre = countryAbbre;
    }

    public String getCountryChn() {
        return countryChn;
    }

    public void setCountryChn(String countryChn) {
        this.countryChn = countryChn;
    }

    public String getCountryEng() {
        return countryEng;
    }

    public void setCountryEng(String countryEng) {
        this.countryEng = countryEng;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }
}
