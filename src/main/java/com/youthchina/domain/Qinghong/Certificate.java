package com.youthchina.domain.Qinghong;

import com.youthchina.dto.applicant.CertificateRequestDTO;

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
    private String insti_country;
    private java.util.Date certificate_grant_date;
    private java.util.Date certificate_expir_date;
    private String docu_local_id;
    private Integer stu_id;
    private Integer is_delete;
    private Timestamp is_delete_time;

    public Certificate(CertificateRequestDTO certificateRequestDTO) {
        this.certificate_name= certificateRequestDTO.getName();
        this.certificate_insti= certificateRequestDTO.getAuthority();
        this.certificate_grant_date= certificateRequestDTO.getDuration().getBegin();
        this.certificate_expir_date= certificateRequestDTO.getDuration().getEnd();
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

    public String getInsti_country() {
        return insti_country;
    }

    public void setInsti_country(String insti_country) {
        this.insti_country = insti_country;
    }

    public String getDocu_local_id() {
        return docu_local_id;
    }

    public void setDocu_local_id(String docu_local_id) {
        this.docu_local_id = docu_local_id;
    }
}
