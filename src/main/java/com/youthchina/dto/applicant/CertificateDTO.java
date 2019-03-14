package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Certificate;
import com.youthchina.dto.util.DurationDTO;

/**
 * Created by zhong on 2018/12/30.
 */
public class CertificateDTO {
    private String name;
    private String authority;
    private DurationDTO duration;
    private String note;

    public CertificateDTO() {
    }

    public CertificateDTO(Certificate certificate) {
        this.name = certificate.getCertificate_name();
        this.authority = certificate.getCertificate_insti();
        this.duration = new DurationDTO(certificate.getCertificate_grant_date(), certificate.getCertificate_expir_date());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public DurationDTO getDuration() {
        return duration;
    }

    public void setDuration(DurationDTO duration) {
        this.duration = duration;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
