package com.youthchina.dto.applicant;

import com.youthchina.domain.Qinghong.Certificate;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.util.DurationDTO;

/**
 * Created by zhong on 2018/12/30.
 */
public class CertificateRequestDTO implements RequestDTO<Certificate> {
    private Integer id;
    private String name;
    private String authority;
    private String country;
    private DurationDTO duration;
    private String note;

    public CertificateRequestDTO() {
    }

    public CertificateRequestDTO(Certificate certificate) {
        this.name = certificate.getCertificate_name();
        this.authority = certificate.getCertificate_insti();
        this.duration = new DurationDTO(certificate.getCertificate_grant_date(), certificate.getCertificate_expir_date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Certificate convertToDomain() {
        return new Certificate(this);
    }
}
