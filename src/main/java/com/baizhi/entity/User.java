package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNmae: User
 * @Author: yddm
 * @DateTime: 2020/8/25 14:15
 * @Description: TODO
 */

@Table(name = "yx_user")
public class User implements Serializable {
    @Excel(name = "用户id")
    @Id
    private String id;
    @Excel(name = "用户名", width = 25, height = 20)
    private String username;
    @Excel(name = "联系方式", width = 25, height = 20)
    private String phone;
    @Excel(name = "头像", type = 2, width = 25, height = 20)
    @Column(name = "head_img")
    private String headImg;
    @Excel(name = "简介", width = 25, height = 20)
    private String sign;
    @Excel(name = "微信号", width = 25, height = 20)
    private String wechat;
    @Excel(name = "状态", width = 25, height = 20)
    private String status;
    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 25, height = 20)
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", headImg='" + headImg + '\'' +
                ", sign='" + sign + '\'' +
                ", wechat='" + wechat + '\'' +
                ", status='" + status + '\'' +
                ", createDate=" + createDate +
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User() {
    }

    public User(String id, String username, String phone, String headImg, String sign, String wechat, String status, Date createDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.headImg = headImg;
        this.sign = sign;
        this.wechat = wechat;
        this.status = status;
        this.createDate = createDate;
    }
}
