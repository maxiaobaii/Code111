package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ClassNmae: FeedBack
 * @Author: yddm
 * @DateTime: 2020/8/28 15:36
 * @Description: TODO
 */

@Table(name = "yx_feedback")
public class FeedBack {
    @Id
    private String id;
    private String title;
    private String content;
    @Column(name = "user_id")
    private String userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "save_date")
    private Date saveDate;

    @Override
    public String toString() {
        return "FeedBack{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", saveDate=" + saveDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public FeedBack(String id, String title, String content, String userId, Date saveDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.saveDate = saveDate;
    }

    public FeedBack() {
    }
}
