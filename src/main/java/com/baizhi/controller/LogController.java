package com.baizhi.controller;

import com.baizhi.entity.Log;
import com.baizhi.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @ClassNmae: LogController
 * @Author: yddm
 * @DateTime: 2020/8/31 21:53
 * @Description: TODO
 */

@Controller
@RequestMapping("/Log")
public class LogController {
    @Resource
    LogService logService;

    @ResponseBody
    @RequestMapping("/show")
    public HashMap<String, Object> ShowLog(Integer page, Integer rows) {
        return logService.ShowLog(page, rows);
    }

    @ResponseBody
    @RequestMapping("/edit")
    public void edit(Log log, String oper) {
        //执行添加动作
        if (oper.equals("add")) {

        }

        //执行修改
        if (oper.equals("edit")) {
        }

        //执行删除动作
        if (oper.equals("del")) {
            logService.delete(log);
        }
    }
}
