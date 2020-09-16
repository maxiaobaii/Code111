package com.baizhi.service.impl;

import com.baizhi.dao.CategoryDao;
import com.baizhi.entity.Category;
import com.baizhi.po.CategoryPo;
import com.baizhi.service.CategoryService;
import com.baizhi.vo.CategoryVo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @ClassNmae: CategoryServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/26 10:48
 * @Description: TODO
 */

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Resource
    CategoryDao categoryDao;

    //    @AddLog(value = "查看一级分类")
    @Override
    public HashMap<String, Object> selectOneCategory(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Category category = new Category();
        category.setLevels(1);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Category> categories = categoryDao.selectByRowBounds(category, rowBounds);
        //总条数
        Integer records = categoryDao.selectCount(category);
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        //存值
        map.put("total", total);
        map.put("records", records);
        map.put("rows", categories);
        //当前页
        map.put("page", page);
        return map;
    }

    //    @AddLog(value = "查看二级分类")
    @Override
    public HashMap<String, Object> selectSecondCategory(String oneId, Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        Category category = new Category();
        category.setLevels(2);
        category.setParentId(oneId);
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Category> categories = categoryDao.selectByRowBounds(category, rowBounds);
        //总条数
        Integer records = categoryDao.selectCount(category);
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        //存值
        map.put("total", total);
        map.put("records", records);
        map.put("rows", categories);
        //当前页
        map.put("page", page);
        return map;
    }

    //    @AddLog(value = "添加分类")
    @Override
    public void addCategory(Category category) {
        //判断添加的是一级类别还是二级类别
        //判断是否有parentid
        category.setId(UUID.randomUUID().toString());
        if (category.getParentId() == null) {
            //说明添加的是一级类别
            category.setLevels(1);
        } else {
            //说明是添加的二级类别
            category.setLevels(2);
        }
        categoryDao.insertSelective(category);
        System.out.println("category = " + category);
    }

    @Override
    public List<CategoryVo> queryAllCategory() {
        ArrayList<CategoryVo> categoryVos = new ArrayList<>();
        try {
            List<CategoryPo> categoryPos = categoryDao.queryAllcategory();
            for (CategoryPo categoryPo : categoryPos) {
                System.err.println("categoryPo = " + categoryPo);
                CategoryVo categoryVo = new CategoryVo(categoryPo.getId(), categoryPo.getCateName(), categoryPo.getLevels(),
                        categoryPo.getParentId(), categoryPo.getCategoryList());
                categoryVos.add(categoryVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryVos;
    }

    //    @AddLog(value = "更新分类")
    @Override
    public void updateCategory(Category category) {
        categoryDao.updateByPrimaryKeySelective(category);
    }

    //    @AddLog(value = "删除分类")
    @Override
    public HashMap<String, Object> deleteCategory(Category category) {
        HashMap<String, Object> map = new HashMap<>();
        //判断当前要删除的类别是一级、二级类别
        if (category.getParentId() == null) {
            //它是一级类别
            //判断当前类别下有无二级类别
            Example example = new Example(Category.class);
            example.createCriteria().andEqualTo("parentId", category.getId());
            Integer i = categoryDao.selectCountByExample(example);
            System.err.println("i = " + i);
            if (i == 0) {
                //未发现二级类别，可以删除
                map.put("message", "删除成功！");
                categoryDao.deleteByPrimaryKey(category);
            } else {
                //有二级类别 ，不能删除
                map.put("message", "该类别下有子类别，不能进行删除");
            }
        } else {
            //当前类别是二级类别，
            //判断是否有视频存在，有：不能删；无：可以删
            categoryDao.deleteByPrimaryKey(category);
            map.put("message", "删除成功！");
        }
        return map;
    }
}
