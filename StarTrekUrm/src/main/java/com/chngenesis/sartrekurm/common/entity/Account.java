package com.chngenesis.sartrekurm.common.entity;

import java.util.Date;

/**
 * 账户表
 */
public class Account {

    private String id;

    /**
     * 昵称
     */
    private String username;

    /**
     * 用户职位
     */
    private String duties;

    /**
     * 用户出生日期
     */
    private Date birthday;

    /**
     * 民族
     */
    private String nation;

    /**
     * 性别  0未知 1男 2女
     */
    private Integer gender;

    /**
     * 电话号码，可用于登录
     */
    private String phonenum;

    /**
     * 邮件地址 可以用于登录
     */
    private String email;

    /**
     * 盐值 用于登录密码加密
     */
    private String salt;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 关联后的机构
     */
    private String orgId;

    /**
     * 逻辑删除 0未删除 1已删除
     */
    private Integer del;

    /**
     * 账号创建时间
     */
    private Date creatime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties == null ? null : duties.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }
}