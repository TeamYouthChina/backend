package com.youthchina.dto.security;

import com.youthchina.dto.RequestDTO;

/**
 * Created by zhongyangwu on 2/10/19.
 */
public class RegisterUserDTO implements RequestDTO {
    private String username;
    private String password;
    private String email;

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

}
