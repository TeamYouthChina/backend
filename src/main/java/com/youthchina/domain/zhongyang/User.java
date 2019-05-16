package com.youthchina.domain.zhongyang;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import com.youthchina.annotation.JsonTimeStamp;
import com.youthchina.dto.security.RegisterUserDTO;
import com.youthchina.dto.security.UserDTO;
import com.youthchina.util.HasId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhongyangwu on 11/8/18.
 */
public class User implements UserDetails, HasId<Integer> {
    private Integer id;
    private String password;
    private String email;
    private String phonenumber;
    private Timestamp registerTime;
    private Timestamp dateOfBirth;
    private Timestamp modifiedTime;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String avatarUrl;
    private Boolean isHired;
    private List<Role> role;
    private Boolean isMailVerified;
    private Boolean isPhoneVerified;

    public User() {
        this.gender = Gender.MALE;
        this.isHired = false;
        this.firstName = "John";
        this.lastName = "Doe";
        this.phonenumber = "000000000";
        this.dateOfBirth = Timestamp.valueOf("1970-01-01 00:00:00.0");
        this.modifiedTime = Timestamp.from(Calendar.getInstance().toInstant());
        this.registerTime = Timestamp.from(Calendar.getInstance().toInstant());
    }

    public User(UserDTO userDTO) {
        this();
        this.id = userDTO.getId();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.phonenumber = userDTO.getPhonenumber();
        this.registerTime = userDTO.getRegister_date();
        this.firstName = userDTO.getFirst_name();
        this.lastName = userDTO.getLast_name();
        this.gender = userDTO.getGender();
        this.avatarUrl = userDTO.getAvatar_url();
        this.role = userDTO.getRole();
    }

    public User(RegisterUserDTO registerUserDTO) {
        this();
        this.email = registerUserDTO.getEmail();
        this.gender = Gender.valueOf(registerUserDTO.getGender());
        this.password = registerUserDTO.getPassword();
        this.firstName = registerUserDTO.getFirstName();
        this.lastName = registerUserDTO.getLastName();
        this.dateOfBirth = new Timestamp(registerUserDTO.getDateOfBirth());
        this.isMailVerified = false;
        this.isPhoneVerified = false;
        this.role = Lists.newArrayList(Role.APPLICANT);
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

    @Override
    public String getUsername() {
        return this.getId() == null ? "" : this.getId().toString();
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

    public Timestamp getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Timestamp registerTime) {
        this.registerTime = registerTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public void setRole(Role role) {
        this.role = Lists.newArrayList(role);
    }


    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getHired() {
        return isHired;
    }

    public void setHired(Boolean hired) {
        this.isHired = hired;
    }

    public Boolean getPhoneVerified() {
        return isPhoneVerified;
    }

    public void setPhoneVerified(Boolean phoneVerified) {
        isPhoneVerified = phoneVerified;
    }

    public Boolean getMailVerified() {
        return isMailVerified;
    }

    public void setMailVerified(Boolean mailVerified) {
        isMailVerified = mailVerified;
    }

    @JsonTimeStamp
    public Timestamp getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Timestamp dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonTimeStamp
    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
