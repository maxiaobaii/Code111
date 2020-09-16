package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @ClassNmae: AdminController
 * @Author: yddm
 * @DateTime: 2020/8/24 18:30
 * @Description: TODO
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("login")
    @ResponseBody
    public HashMap<String, Object> login(Admin admin, String code) {
        System.out.println("Controller admin = " + admin);
        System.out.println("Controller code = " + code);
        HashMap<String, Object> map = adminService.queryByUsername(admin, code);
        return map;
    }

    @RequestMapping("login1")
    @ResponseBody
    public HashMap<String, Object> login1(Admin admin, String code) {
        System.out.println("Controller admin = " + admin);
        System.out.println("Controller code = " + code);
        HashMap<String, Object> map = adminService.queryByUsername1(admin, code);
        return map;
    }

    @RequestMapping("/out")
    public String out(HttpSession session) {
        session.removeAttribute("loginAdmin");
        return "redirect:/login/login.jsp";
    }
}
