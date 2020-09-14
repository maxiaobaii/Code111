package com.baizhi.po;

import java.util.Date;
import java.util.List;

/**
 * @ClassNmae: UserMovingPo
 * @Author: yddm
 * @DateTime: 2020/9/2 21:03
 * @Description: TODO
 */

public class UserMovingPo {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private Date uploadTime;
    private String description;
    private String cateName;
    private String categoryId;
    private String userId;
    private String userName;
    private String userPicImg;
    private List<VideoPo> videoPos;

    @Override
    public String toString() {
        return "UserMovingPo{" +
                "id='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime=" + uploadTime +
                ", description='" + description + '\'' +
                ", cateName='" + cateName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPicImg='" + userPicImg + '\'' +
                ", videoPos=" + videoPos +
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPicImg() {
        return userPicImg;
    }

    public void setUserPicImg(String userPicImg) {
        this.userPicImg = userPicImg;
    }

    public List<VideoPo> getVideoPos() {
        return videoPos;
    }

    public void setVideoPos(List<VideoPo> videoPos) {
        this.videoPos = videoPos;
    }

    public UserMovingPo(String id, String videoTitle, String cover, String path, Date uploadTime, String description, String cateName, String categoryId, String userId, String userName, String userPicImg, List<VideoPo> videoPos) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.cateName = cateName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.userName = userName;
        this.userPicImg = userPicImg;
        this.videoPos = videoPos;
    }

    public UserMovingPo() {
    }
}
