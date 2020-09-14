package com.baizhi.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassNmae: UserDetailPo
 * @Author: yddm
 * @DateTime: 2020/9/2 22:18
 * @Description: TODO
 */

public class UserDetailPo {
    private String id;
    private String username;
    private String picImg;
    private String phone;
    private String password;
    private String introduction;
    private String sat;
    private String status;
    private String wechat;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registerDate;

    @Override
    public String toString() {
        return "UserDetailPo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", picImg='" + picImg + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", introduction='" + introduction + '\'' +
                ", sat='" + sat + '\'' +
                ", status='" + status + '\'' +
                ", wechat='" + wechat + '\'' +
                ", registerDate=" + registerDate +
                '}';
    }

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

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSat() {
        return sat;
    }

    public void setSat(String sat) {
        this.sat = sat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public UserDetailPo(String id, String username, String picImg, String phone, String password, String introduction, String sat, String status, String wechat, Date registerDate) {
        this.id = id;
        this.username = username;
        this.picImg = picImg;
        this.phone = phone;
        this.password = password;
        this.introduction = introduction;
        this.sat = sat;
        this.status = status;
        this.wechat = wechat;
        this.registerDate = registerDate;
    }

    public UserDetailPo() {
    }
}
