package com.baizhi.vo;

import com.baizhi.po.VideoPo;

import java.util.Date;
import java.util.List;

/**
 * @ClassNmae: UserMovingVo
 * @Author: yddm
 * @DateTime: 2020/9/2 21:03
 * @Description: TODO
 */

public class UserMovingVo {
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
    private Integer likeCount;
    private Integer playCount;
    private Boolean isAttention;
    private List<VideoPo> videoPos;

    @Override
    public String toString() {
        return "UserMovingVo{" +
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
                ", likeCount=" + likeCount +
                ", playCount=" + playCount +
                ", isAttention=" + isAttention +
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Boolean getAttention() {
        return isAttention;
    }

    public void setAttention(Boolean attention) {
        isAttention = attention;
    }

    public List<VideoPo> getVideoPos() {
        return videoPos;
    }

    public void setVideoPos(List<VideoPo> videoPos) {
        this.videoPos = videoPos;
    }

    public UserMovingVo(String id, String videoTitle, String cover, String path, Date uploadTime, String description, String cateName, String categoryId, String userId, String userName, String userPicImg, Integer likeCount, Integer playCount, Boolean isAttention, List<VideoPo> videoPos) {
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
        this.likeCount = likeCount;
        this.playCount = playCount;
        this.isAttention = isAttention;
        this.videoPos = videoPos;
    }

    public UserMovingVo() {
    }
}
