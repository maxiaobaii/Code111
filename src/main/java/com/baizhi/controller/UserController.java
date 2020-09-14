package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Common;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunSendPhoneUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @Resource
    HttpSession session;

    @ResponseBody
    @RequestMapping("allUser")
    public HashMap<String, Object> queryAllUserPage(Integer page, Integer rows) {
        HashMap<String, Object> users = userService.queryAllUserPage(page, rows);
        return users;
    }

    @AddLog(value = "编辑用户")
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String, Object> edit(User user, String oper) {
        HashMap<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            System.out.println(user);
            String id = userService.add(user);
            map.put("id", id);
        }
        if ("edit".equals(oper)) {
            System.out.println("user = " + user);
            String add = userService.updataStatus(user, session);
            map.put("add", add);
        }
        if ("del".equals(oper)) {

        }
        return map;
    }

    //文件上传至本地
    /*@RequestMapping("upload")
    public void upload(String id, MultipartFile headImg, HttpServletRequest request) {
        //文件上传    本地
        //1.获取绝对路径
        String path = request.getSession().getServletContext().getRealPath("/bootstrap/img");
        //2.文件上传
        String cover = String.valueOf(System.currentTimeMillis()) + "."
                + FilenameUtils.getExtension(headImg.getOriginalFilename());
        try {
            headImg.transferTo(new File(path, cover));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //修改当前用户的头像信息
        User user = new User();
        user.setId(id);
        user.setHeadImg(cover);
        userService.edit(user);
    }*/

    //文件上传至阿里云服务器
    @AddLog(value = "头像上传至阿里云")
    @RequestMapping("uploadFile")
    public void uploadFile(String id, MultipartFile headImg, HttpServletRequest request) {
        //文件上传    服务器
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4G5UMgiJX68FJdio9jnG";
        String accessKeySecret = "Lr2RmJvktEgy84j2tgmeBNwJHZX9Iz";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 上传Byte数组。
        byte[] content = new byte[0];
        try {
            content = headImg.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String bucketName = "yingx-sjt";
        String cover = String.valueOf(System.currentTimeMillis()) + "."
                + FilenameUtils.getExtension(headImg.getOriginalFilename());
        String fileName = "photo/" + cover;
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content));

        // 关闭OSSClient。
        ossClient.shutdown();

        //修改当前用户的头像信息
        User user = new User();
        user.setId(id);
        user.setHeadImg("https://yingx-sjt.oss-cn-beijing.aliyuncs.com/" + fileName);
        System.out.println("user.getHeadImg() = " + user.getHeadImg());
        userService.edit(user);
    }

    //验证码
    @AddLog(value = "发送短信")
    @ResponseBody
    @RequestMapping("/getAliSendPhone")
    public HashMap<String, Object> getAliSendPhone(String number) {
        String s = AliyunSendPhoneUtil.getCode();
        String message = AliyunSendPhoneUtil.sendCode(number, s);
        HashMap<String, Object> map = new HashMap<>();
        map.put("message", message);
        return map;
    }

    /**
     * 导出
     *
     * @return
     */
    @AddLog(value = "导出用户数据")
    @ResponseBody
    @RequestMapping("/easyPoiWrite")
    public HashMap<String, Object> easyBto() {
        //调用service层导出功能
        HashMap<String, Object> map = userService.easyPoiWrite();
        map.put("message", "导出成功");
        return map;
    }

    @AddLog(value = "导入用户数据")
    @ResponseBody
    @RequestMapping("easyPoiRead")
    public void easyPoiRead() {
        //配置导入的相关参数
        ImportParams params = new ImportParams();
        //标题行数
        params.setTitleRows(1);
        //表头行数
        params.setHeadRows(1);
        try {
            FileInputStream stream = new FileInputStream(new File("E:\\yingx-user.xls"));
            List<User> user = ExcelImportUtil.importExcel(stream, User.class, params);
            System.out.println("导入数据如下：");
            user.forEach(person -> System.err.println(person));
            //批量入库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ResponseBody
    @RequestMapping("/queryUserNumByMonth")
    public HashMap<String, Object> Echarts() {
        HashMap<String, Object> map = userService.Echarts();
        return map;
    }

    @ResponseBody
    @RequestMapping("/EchartsCity")
    public ArrayList<Common> EchartsCity() {
        ArrayList<Common> commons = userService.EchartsCity();
        return commons;
    }
}
