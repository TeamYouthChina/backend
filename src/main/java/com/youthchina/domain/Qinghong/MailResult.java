package com.youthchina.domain.Qinghong;

/**
 * @program: youthchina
 * @description: 对于邮件实体的实现
 * @author: Qinghong Wang
 * @create: 2019-02-02 13:22
 **/
public class MailResult {
    private String resCode;
    private String rspMsg;

    public MailResult() {
        this.resCode = "00";
        this.rspMsg = "邮件发送成功";
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }
}
