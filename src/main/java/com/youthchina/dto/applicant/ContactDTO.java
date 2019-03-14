package com.youthchina.dto.applicant;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: youthchina
 * @description: 联系信息DTO
 * @author: Qinghong Wang
 * @create: 2019-02-26 13:33
 **/
public class ContactDTO {
    private List<String> emails;
    private List<String> phonenumbers;

    public ContactDTO() {
    }

    public ContactDTO(String email,String phonenumber) {
        List<String> emails=new ArrayList<>();
        emails.add(email);
        List<String> phonenumbers=new ArrayList<>();
        phonenumbers.add(phonenumber);
        this.emails=emails;
        this.phonenumbers=phonenumbers;

    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public List<String> getPhonenumbers() {
        return phonenumbers;
    }

    public void setPhonenumbers(List<String> phonenumbers) {
        this.phonenumbers = phonenumbers;
    }
}
