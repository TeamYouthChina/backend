package com.youthchina.dto;

/**
 * Created by zhongyangwu on 12/2/18.
 */
public class OrganizationDTO {
    private int id;
    private String name;
    private String avatarUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
