package com.baizhi.controller;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Category;
import com.baizhi.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassNmae: CategoryController
 * @Author: yddm
 * @DateTime: 2020/8/26 10:47
 * @Description: TODO
 */
@Controller
@RequestMapping("category")
public class CategoryController {
    @Resource
    CategoryService categoryService;

    //一级分类页面查询方法
    @ResponseBody
    @RequestMapping("/one")
    public HashMap<String, Object> selectOneCategory(Integer page, Integer rows) {
        return categoryService.selectOneCategory(page, rows);
    }

    //二级分类页面查询方法
    @ResponseBody
    @RequestMapping("/second")
    public HashMap<String, Object> selectSecondCategory(String oneId, Integer page, Integer rows) {
        return categoryService.selectSecondCategory(oneId, page, rows);
    }

    @AddLog(value = "编辑分类")
    @ResponseBody
    @RequestMapping("/edit")
    public HashMap<String, Object> edit(Category category, String oper) {
        System.err.println("category = " + category);
        System.err.println("oper = " + oper);
        HashMap<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            categoryService.addCategory(category);
        }
        if ("edit".equals(oper)) {
            categoryService.updateCategory(category);
        }
        if ("del".equals(oper)) {
            map = categoryService.deleteCategory(category);
        }
        return map;
    }
}
