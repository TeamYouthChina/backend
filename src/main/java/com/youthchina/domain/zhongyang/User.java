package com.youthchina.domain.zhongyang;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.youthchina.dto.RegisterUserDTO;
import com.youthchina.dto.UserDTO;
import com.youthchina.util.zhongyang.HasId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
public class User implements UserDetails, HasId<Integer> {
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

    public User() {

    }

    public User(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.phonenumber = userDTO.getPhonenumber();
        this.registerDate = userDTO.getRegister_date();
        this.realName = userDTO.getReal_name();
        this.gender = userDTO.getGender();
        this.nation = userDTO.getNation();
        this.avatarUrl = userDTO.getAvatar_url();
        this.role = userDTO.getRole();
        this.age = userDTO.getAge();
    }

    public User(RegisterUserDTO registerUserDTO) {
        this.username = registerUserDTO.getUsername();
        this.email = registerUserDTO.getEmail();
        this.password = registerUserDTO.getPassword();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.registerDate = simpleDateFormat.format(Calendar.getInstance().getTime());
        this.phonenumber = registerUserDTO.getPhoneNumber();
        this.gender = registerUserDTO.getGender();
        this.age = registerUserDTO.getAge();
        this.realName = registerUserDTO.getUsername();
        this.nation = registerUserDTO.getNation();
        this.role = 1;
    }

    public User(Integer id) {
        this.id = id;
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

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.role.toString()));
        return authorities;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
