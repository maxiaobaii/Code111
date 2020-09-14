package com.baizhi.vo;

/**
 * @ClassNmae: VideoVo
 * @Author: yddm
 * @DateTime: 2020/9/1 11:15
 * @Description: TODO
 */

public class VideoVo {
    private String id;
    private String videoTitle;
    private String cover;
    private String path;
    private String uploadTime;
    private String description;
    private String cateName;
    private Integer likeCount;
    private String userPhoto;

    @Override
    public String toString() {
        return "VideoVo{" +
                "id='" + id + '\'' +
                ", videoTitle='" + videoTitle + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                ", description='" + description + '\'' +
                ", cateName='" + cateName + '\'' +
                ", likeCount=" + likeCount +
                ", userPhoto='" + userPhoto + '\'' +
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public VideoVo(String id, String videoTitle, String cover, String path, String uploadTime, String description, String cateName, Integer likeCount, String userPhoto) {
        this.id = id;
        this.videoTitle = videoTitle;
        this.cover = cover;
        this.path = path;
        this.uploadTime = uploadTime;
        this.description = description;
        this.cateName = cateName;
        this.likeCount = likeCount;
        this.userPhoto = userPhoto;
    }

    public VideoVo() {
    }
}
