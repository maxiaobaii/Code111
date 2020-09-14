package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassNmae: Log
 * @Author: yddm
 * @DateTime: 2020/8/31 21:12
 * @Description: TODO
 */

@Table(name = "yx_log")
public class Log {
    @Id
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "manipulate")
    private String manipulate;
    @Column(name = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date date;
    @Column(name = "status")
    private String status;

    @Override
    public String toString() {
        return "Log{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manipulate='" + manipulate + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManipulate() {
        return manipulate;
    }

    public void setManipulate(String manipulate) {
        this.manipulate = manipulate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Log(String id, String name, String manipulate, Date date, String status) {
        this.id = id;
        this.name = name;
        this.manipulate = manipulate;
        this.date = date;
        this.status = status;
    }

    public Log() {
    }
}
