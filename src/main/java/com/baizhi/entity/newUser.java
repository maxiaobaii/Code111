package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @ClassNmae: newUser
 * @Author: yddm
 * @DateTime: 2020/9/3 20:28
 * @Description: TODO
 */

public class newUser {
    private String id;
    private String username;
    private String phone;
    private String sex;
    private String city;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String createDate;

    @Override
    public String toString() {
        return "newUser{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                ", createDate='" + createDate + '\'' +
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public newUser(String id, String username, String phone, String sex, String city, String createDate) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.sex = sex;
        this.city = city;
        this.createDate = createDate;
    }

    public newUser() {
    }
}
