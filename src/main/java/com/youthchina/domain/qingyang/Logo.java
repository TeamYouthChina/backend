package com.youthchina.domain.qingyang;

/**
 * @author: Qingyang Zhao
 * @create: 2019-03-23
 **/
public class Logo {
    private Integer logoId;
    private String docuLocalId;
    private Integer relaType;
    private Integer relaId;
    private String url;

    public Integer getLogoId() {
        return logoId;
    }

    public void setLogoId(Integer logoId) {
        this.logoId = logoId;
    }

    public String getDocuLocalId() {
        return docuLocalId;
    }

    public void setDocuLocalId(String docuLocalId) {
        this.docuLocalId = docuLocalId;
    }

    public Integer getRelaType() {
        return relaType;
    }

    public void setRelaType(Integer relaType) {
        this.relaType = relaType;
    }

    public Integer getRelaId() {
        return relaId;
    }

    public void setRelaId(Integer relaId) {
        this.relaId = relaId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
