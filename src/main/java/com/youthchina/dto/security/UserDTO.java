package com.youthchina.dto.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.ResponseDTO;

/**
 * Created by zhongyangwu on 1/29/19.
 */
public class UserDTO implements ResponseDTO<User>, RequestDTO<User> {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phonenumber;
    private String register_date;
    private String real_name;
    private String gender;
    private String nation;
    private String avatar_url;
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
        this.register_date = user.getRegisterDate();
        this.real_name = user.getRealName();
        this.gender = user.getGender();
        this.nation = user.getNation();
        this.avatar_url = user.getAvatarUrl();
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

    @JsonIgnore
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

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
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

    @Override
    public void convertToDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phonenumber = user.getPhonenumber();
        this.register_date = user.getRegisterDate();
        this.real_name = user.getRealName();
        this.gender = user.getGender();
        this.nation = user.getNation();
        this.avatar_url = user.getAvatarUrl();
        this.role = user.getRole();
        this.age = user.getAge();
    }

    @Override
    public User convertToDomain() {
        return new User();
    }
}
