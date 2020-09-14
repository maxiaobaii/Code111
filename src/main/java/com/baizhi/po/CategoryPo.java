package com.baizhi.po;

import com.baizhi.entity.Category;

import java.util.List;

/**
 * @ClassNmae: CategoryPo
 * @Author: yddm
 * @DateTime: 2020/9/1 22:07
 * @Description: TODO
 */

public class CategoryPo {
    private String id;
    private String cateName;
    private Integer levels;
    private String parentId;
    private List<Category> categoryList;

    @Override
    public String toString() {
        return "CategoryPo{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", levels=" + levels +
                ", parentId='" + parentId + '\'' +
                ", categoryList=" + categoryList +
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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryPo(String id, String cateName, Integer levels, String parentId, List<Category> categoryList) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
        this.categoryList = categoryList;
    }

    public CategoryPo() {
    }
}
