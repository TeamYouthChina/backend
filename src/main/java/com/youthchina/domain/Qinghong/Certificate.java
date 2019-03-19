package com.youthchina.domain.Qinghong;

import com.youthchina.dto.applicant.CertificateDTO;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @program: youthchina
 * @description: 申请者证书信息实体
 * @author: Qinghong Wang
 * @create: 2018-12-22 00:02
 **/
public class Certificate {
    private Integer certificate_id;
    private String certificate_name;
    private String certificate_insti;
    private java.util.Date certificate_grant_date;
    private java.util.Date certificate_expir_date;
    private String certificate_url;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Certificate(CertificateDTO certificateDTO) {
        this.certificate_name = certificateDTO.getName();
        this.certificate_insti = certificateDTO.getAuthority();
        this.certificate_grant_date = certificateDTO.getDuration().getBegin();
        this.certificate_expir_date = certificateDTO.getDuration().getEnd();
    }

    public Certificate() {
    }

    public Integer getCertificate_id() {
        return certificate_id;
    }

    public void setCertificate_id(Integer certificate_id) {
        this.certificate_id = certificate_id;
    }

    public String getCertificate_name() {
        return certificate_name;
    }

    public void setCertificate_name(String certificate_name) {
        this.certificate_name = certificate_name;
    }

    public String getCertificate_insti() {
        return certificate_insti;
    }

    public void setCertificate_insti(String certificate_insti) {
        this.certificate_insti = certificate_insti;
    }

    public Date getCertificate_grant_date() {
        return certificate_grant_date;
    }

    public void setCertificate_grant_date(Date certificate_grant_date) {
        this.certificate_grant_date = certificate_grant_date;
    }

    public Date getCertificate_expir_date() {
        return certificate_expir_date;
    }

    public void setCertificate_expir_date(Date certificate_expir_date) {
        this.certificate_expir_date = certificate_expir_date;
    }

    public String getCertificate_url() {
        return certificate_url;
    }

    public void setCertificate_url(String certificate_url) {
        this.certificate_url = certificate_url;
    }

    public Integer getStu_id() {
        return stu_id;
    }

    public void setStu_id(Integer stu_id) {
        this.stu_id = stu_id;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Timestamp getIs_delete_time() {
        return is_delete_time;
    }

    public void setIs_delete_time(Timestamp is_delete_time) {
        this.is_delete_time = is_delete_time;
    }
}
