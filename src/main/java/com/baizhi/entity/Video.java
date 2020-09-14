package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassNmae: Video
 * @Author: yddm
 * @DateTime: 2020/8/28 13:20
 * @Description: TODO
 */

@Document(indexName = "yingxvideo", type = "video")
@Table(name = "yx_video")
public class Video implements Serializable {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String brief;

    @Field(type = FieldType.Keyword)
    private String path;

    @Field(type = FieldType.Keyword)
    private String cover;

    @Column(name = "publish_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Field(type = FieldType.Date)
    private Date publishDate;

    @Field(type = FieldType.Keyword)
    @Column(name = "category_id")
    private String categoryId;

    @Field(type = FieldType.Keyword)
    @Column(name = "group_id")
    private String groupId;

    @Field(type = FieldType.Keyword)
    @Column(name = "user_id")
    private String userId;

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", path='" + path + '\'' +
                ", cover='" + cover + '\'' +
                ", publishDate=" + publishDate +
                ", categoryId='" + categoryId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", userId='" + userId + '\'' +
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

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Video() {
    }

    public Video(String id, String title, String brief, String path, String cover, Date publishDate, String categoryId, String groupId, String userId) {
        this.id = id;
        this.title = title;
        this.brief = brief;
        this.path = path;
        this.cover = cover;
        this.publishDate = publishDate;
        this.categoryId = categoryId;
        this.groupId = groupId;
        this.userId = userId;
    }
}
