package com.youthchina.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.youthchina.domain.zhongyang.User;

/**
 * Created by zhongyangwu on 1/29/19.
 */
public class UserDTO {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String registerDate;
    private String realName;
    private String gender;
    private String nation;
    private String avatarUrl;
    private Integer role;
    private Integer age;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phonenumber = user.getPhonenumber();
        this.registerDate = user.getRegisterDate();
        this.realName = user.getRealName();
        this.gender = user.getGender();
        this.nation = user.getNation();
        this.avatarUrl = user.getAvatarUrl();
        this.role = user.getRole();
        this.age = user.getAge();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonSetter
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
