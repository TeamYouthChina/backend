package com.youthchina.domain.Tianjian;

/**
 * Created by zhongyangwu on 11/8/18.
 */
public class User {
    private Integer id;
    private String username;
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
