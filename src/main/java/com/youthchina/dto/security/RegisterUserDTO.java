package com.youthchina.dto.security;

import com.youthchina.domain.zhongyang.Gender;

import javax.validation.constraints.NotNull;

/**
 * Created by zhongyangwu on 2/10/19.
 */
public class RegisterUserDTO {
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    @NotNull
    private Long dateOfBirth;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setGender(Gender gender){
        this.gender = gender.name();
    }

    public Long getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Long dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
