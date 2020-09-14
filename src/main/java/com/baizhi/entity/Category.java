package com.baizhi.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @ClassNmae: Category
 * @Author: yddm
 * @DateTime: 2020/8/26 10:44
 * @Description: TODO
 */
@Table(name = "yx_category")
public class Category implements Serializable {
    @Id
    private String id;
    @Column(name = "cate_name")
    private String cateName;
    private Integer levels;
    @Column(name = "parent_id")
    private String parentId;

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", levels=" + levels +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Category(String id, String cateName, Integer levels, String parentId) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
    }

    public Category() {
    }
}
