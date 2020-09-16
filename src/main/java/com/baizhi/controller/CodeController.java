package com.baizhi.controller;

import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @ClassNmae: CodeController
 * @Author: yddm
 * @DateTime: 2020/7/22 15:15
 * @Description: TODO
 */
@Controller
@RequestMapping("code")
public class CodeController {

    /**
     * 验证码
     *
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping("code")
    public void code(HttpSession session, HttpServletResponse response) throws IOException {
        System.out.println("正在获取验证码，请稍后...");
        //获取验证码字符串
        String securityCode = SecurityCode.getSecurityCode();
        System.out.println("securityCode = " + securityCode);
        //将验证码存入session
        session.setAttribute("code", securityCode);
        //生成验证码图片
        BufferedImage image = SecurityImage.createImage(securityCode);
        ServletOutputStream outputStream = response.getOutputStream();
        try {
            ImageIO.write(image, "png", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
