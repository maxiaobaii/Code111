package com.baizhi.controller;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.FeedBack;
import com.baizhi.service.FeedBackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassNmae: FeedBackController
 * @Author: yddm
 * @DateTime: 2020/8/28 15:55
 * @Description: TODO
 */
@Controller
@RequestMapping("Feed")
public class FeedBackController {
    @Resource
    FeedBackService feedBackService;

    @ResponseBody
    @RequestMapping("/show")
    public HashMap<String, Object> showFeed(Integer page, Integer rows) {
        return feedBackService.ShowFeed(page, rows);
    }

    @AddLog(value = "编辑反馈")
    @ResponseBody
    @RequestMapping("/edit")
    public void edit(FeedBack feedBack, String oper) {
        //执行添加动作
        if (oper.equals("add")) {
            feedBackService.add(feedBack);
        }

        //执行修改
        if (oper.equals("edit")) {
            feedBackService.update(feedBack);
        }

        //执行删除动作
        if (oper.equals("del")) {
            feedBackService.delete(feedBack);
        }
    }
}
