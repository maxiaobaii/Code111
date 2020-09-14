package com.baizhi.dao;

import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @InterfaceNmae: CategoryDao
 * @Author: yddm
 * @DateTime: 2020/8/26 10:45
 * @Description: TODO
 */

public interface CategoryDao extends Mapper<Category> {
    /**
     * 查询所有一二级类别
     *
     * @return
     */
    List<CategoryPo> queryAllcategory();
}
