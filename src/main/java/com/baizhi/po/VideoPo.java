package com.baizhi.po;

import java.util.Date;

/**
 * @ClassNmae: VideoPo
 * @Author: yddm
 * @DateTime: 2020/9/1 11:15
 * @Description: TODO
 */

public class VideoPo {
    private String id;
    private String title;
    private String cover;
    private String path;
    private Date publishDate;
    private String brief;
    private String cateName;
    private String headImg;

    @Override
    public String toString() {
        return "VideoPo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", path='" + path + '\'' +
                ", publishDate=" + publishDate +
                ", brief='" + brief + '\'' +
                ", cateName='" + cateName + '\'' +
                ", headImg='" + headImg + '\'' +
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

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public VideoPo(String id, String title, String cover, String path, Date publishDate, String brief, String cateName, String headImg) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.path = path;
        this.publishDate = publishDate;
        this.brief = brief;
        this.cateName = cateName;
        this.headImg = headImg;
    }

    public VideoPo() {
    }
}
