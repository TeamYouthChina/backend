package com.youthchina.dto.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.domain.zhongyang.Gender;
import com.youthchina.domain.zhongyang.Role;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.RequestDTO;
import com.youthchina.dto.ResponseDTO;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by zhongyangwu on 1/29/19.
 */
public class UserDTO implements ResponseDTO<User>, RequestDTO<User> {
    private Integer id;
    private String password;
    private String email;
    private String phonenumber;
    private Timestamp register_date;
    private Timestamp date_of_birth;
    private String first_name;
    private String last_name;
    private Gender gender;
    private String avatar_url;
    private List<Role> role;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phonenumber = user.getPhonenumber();
        this.register_date = user.getRegisterTime();
        this.first_name = user.getFirstName();
        this.last_name = user.getLastName();
        this.date_of_birth = new Timestamp(user.getDateOfBirth() == null ? 0 : user.getDateOfBirth().getTime());
        this.gender = user.getGender();
        this.avatar_url = user.getAvatarUrl();
        this.role = user.getRole();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
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

    @JsonTimeStamp
    public Timestamp getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Timestamp register_date) {
        this.register_date = register_date;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public void convertToDTO(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.phonenumber = user.getPhonenumber();
        this.register_date = user.getRegisterTime();
        this.first_name = user.getFirstName();
        this.last_name = user.getLastName();
        this.gender = user.getGender();
        this.date_of_birth = new Timestamp(user.getDateOfBirth().getTime());
        this.avatar_url = user.getAvatarUrl();
        this.role = user.getRole();
    }

    @Override
    public User convertToDomain() {
        return new User(this);
    }

    @JsonTimeStamp
    public Timestamp getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Timestamp date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
