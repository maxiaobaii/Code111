package com.baizhi.service;

import com.baizhi.entity.Category;
import com.baizhi.vo.CategoryVo;

import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceNmae: CategoryService
 * @Author: yddm
 * @DateTime: 2020/8/26 10:48
 * @Description: TODO
 */

public interface CategoryService {
    /**
     * 一级类别
     *
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> selectOneCategory(Integer page, Integer rows);

    /**
     * 二级类别
     *
     * @param oneId
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> selectSecondCategory(String oneId, Integer page, Integer rows);

    /**
     * 添加分类
     *
     * @param category
     */
    void addCategory(Category category);

    /**
     * 更新分类
     *
     * @param category
     */
    void updateCategory(Category category);

    /**
     * 删除分类
     *
     * @param category
     * @return
     */
    HashMap<String, Object> deleteCategory(Category category);

    /**
     * 查询所有一二级类别
     *
     * @return
     */
    List<CategoryVo> queryAllCategory();
}
