package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * @ClassNmae: AdminServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/24 18:13
 * @Description: TODO
 */

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminDao adminDao;
    @Autowired
    private HttpSession session;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String, Object> queryByUsername(Admin admin, String code) {
        System.out.println("Service admin = " + admin);
        System.out.println("Service code = " + code);
        //获取作用域的验证码进行判断
        String captcha = (String) session.getAttribute("code");
        HashMap<String, Object> map = new HashMap<>();
        if (captcha.equals(code)) {
            //判断用户名是否正确
            Example example = new Example(Admin.class);
            example.createCriteria().andEqualTo("username", admin.getUsername());
            Admin admin1 = adminDao.selectOneByExample(example);
            if (admin1 != null) {
                //判断密码正误
                if (admin1.getPassword().equals(admin.getPassword())) {
                    map.put("status", true);
                } else {
                    map.put("status", false);
                    map.put("message", "密码输入错误");
                }
            } else {
                map.put("status", false);
                map.put("message", "用户名输入错误");
            }
            session.setAttribute("loginAdmin", admin1);
        } else {
            map.put("status", false);
            map.put("message", "验证码输入错误");
        }
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String, Object> queryByUsername1(Admin admin, String code) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
        String captcha = (String) session.getAttribute("code");
        HashMap<String, Object> map = new HashMap<>();
        if (captcha.equals(code)) {
            try {
                subject.login(usernamePasswordToken);
                map.put("status", "true");
            } catch (UnknownAccountException e) {
                map.put("status", false);
                map.put("message", "用户名输入错误");
                e.printStackTrace();
            } catch (IncorrectCredentialsException e) {
                map.put("status", false);
                map.put("message", "密码输入错误");
                e.printStackTrace();
            }
        } else {
            map.put("status", false);
            map.put("message", "验证码输入错误");
        }
        return map;
    }
}
