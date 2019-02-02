package com.youthchina.service.Qinghong;


import java.util.Map;

public interface MailService {

    public void sendSimpleMail(String to, String subject, String content);
    public void sendtemplateMail(String to, String subject, String content);
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
    public void sendUserRegisterEmail(Map<String,Object> valueMap);
}
