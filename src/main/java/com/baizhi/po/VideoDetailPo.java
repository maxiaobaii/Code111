package com.baizhi.po;

import com.baizhi.entity.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * 首页视频详情
 *
 * @ClassNmae: VideoDetailPo
 * @Author: yddm
 * @DateTime: 2020/9/1 23:37
 * @Description: TODO
 */

public class VideoDetailPo {
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
    private String userPicImg;
    private String userName;
    private List<Video> videoList;

    @Override
    public String toString() {
        return "VideoDetailPo{" +
                "id='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime=" + uploadTime +
                ", description='" + description + '\'' +
                ", cateName='" + cateName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", userId='" + userId + '\'' +
                ", userPicImg='" + userPicImg + '\'' +
                ", userName='" + userName + '\'' +
                ", videoList=" + videoList +
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

    public String getUserPicImg() {
        return userPicImg;
    }

    public void setUserPicImg(String userPicImg) {
        this.userPicImg = userPicImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Video> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<Video> videoList) {
        this.videoList = videoList;
    }

    public VideoDetailPo(String id, String videoTitle, String cover, String path, Date uploadTime, String description, String cateName, String categoryId, String userId, String userPicImg, String userName, List<Video> videoList) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.cateName = cateName;
        this.categoryId = categoryId;
        this.userId = userId;
        this.userPicImg = userPicImg;
        this.userName = userName;
        this.videoList = videoList;
    }

    public VideoDetailPo() {
    }
}
