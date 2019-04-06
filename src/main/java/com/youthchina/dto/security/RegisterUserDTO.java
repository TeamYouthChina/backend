package com.youthchina.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhongyangwu on 2/10/19.
 */
public class RegisterUserDTO {
    private String username;
    private String password;
    private String email;
    private String phonenumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone_number")
    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
