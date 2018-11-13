package com.youthchina.domain.jinhao;

public class CommunityUser {
    /*
     * 主键
     * 用户id
     */
    private String id;

    /*
     * 用户名称
     */
    private String username;

    /*
     * 用户密码
     */
    private String passWord;

    /*
     * 用户身份，学生/在职人员/HR，如果身份都列出来可以写一个enum类
     */
    private String status;

    /*
     * 用户影响力
     */
    private String credit;

    /*
     * 用户邮箱
     */
    private String email;

    /*
     * 用户手机
     */
    private String phoneNumber;

    /*
     * 用户头像
     */
    private String profilePicture;

    /*
     * 用户简介
     */
    private String introduction;

    /*
     * 创建时间
     */
    private String createTime;

    /*
     * 上次修改时间
     */
    private String lastModifyTime;

    /*
     * 背景图片
     */
    private String backgroundPicture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
