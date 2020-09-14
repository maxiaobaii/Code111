package com.baizhi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @ClassNmae: userVideo
 * @Author: yddm
 * @DateTime: 2020/9/2 23:15
 * @Description: TODO
 */

public class userVideoVo {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;
    private String description;
    private String cateName;
    private String categoryId;
    private String userId;
    private Integer likeCount;

    @Override
    public String toString() {
        return "userVideoVo{" +
                "id='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime=" + uploadTime +
                ", description='" + description + '\'' +
                ", cateName='" + cateName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", likeCount=" + likeCount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public userVideoVo(String id, String videoTitle, String cover, String path, Date uploadTime, String description, String cateName, String categoryId, String userId, Integer likeCount) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.cateName = cateName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.likeCount = likeCount;
    }

    public userVideoVo() {
    }
}
