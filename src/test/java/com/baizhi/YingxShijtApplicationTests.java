package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.CategoryDao;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.*;
import com.baizhi.po.CategoryPo;
import com.baizhi.po.UserDetailPo;
import com.baizhi.po.VideoDetailPo;
import com.baizhi.service.CategoryService;
import com.baizhi.service.UserService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunSendPhoneUtil;
import com.baizhi.util.MD5Util;
import com.baizhi.vo.SecondVideoVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YingxShijtApplicationTests {

    @Resource
    AdminDao adminDao;
    @Resource
    UserDao userDao;
    @Resource
    VideoDao videoDao;
    @Resource
    CategoryDao categoryDao;
    @Resource
    CategoryService categoryService;
    @Resource
    VideoService videoService;
    @Resource
    UserService userService;

    @Test
    public void contextLoads() {
        Example example = new Example(Admin.class);
        example.createCriteria().andEqualTo("username", "admin1111");
        Admin admin = adminDao.selectOneByExample(example);
        System.out.println("admin = " + admin);
    }

    @Test
    public void test1() {
        Example example = new Example(User.class);
        RowBounds rowBounds = new RowBounds(1, 1);
        List<User> users = userDao.selectByExampleAndRowBounds(example, rowBounds);
        System.out.println("users = " + users);
    }

    @Test
    public void test2() {
//        List<Category> categories = categoryDao.selectPage(1, 2);
//        System.out.println("categories = " + categories);
    }

    @Test
    public void test3() {
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo(21);
        Integer count = categoryDao.selectCountByExample(example);
        System.err.println("count = " + count);
    }

    @Test
    public void test4() {
        String code = AliyunSendPhoneUtil.getCode();
        String s = AliyunSendPhoneUtil.sendCode("18295994914", code);
        System.out.println("s = " + s);
    }

    @Test
    public void test5() {
        /*String a = "null";
        String a1 = "null";
        byte[] bytes = a.getBytes();
        byte[] bytes1 = a1.getBytes();
        String aNull = new String("null");
        System.out.println("bytes = " + bytes);
        System.out.println("bytes1 = " + bytes1);
        System.out.println(a == a1);
        System.out.println(aNull.getBytes());*/
        System.out.println("ʦ����");
    }

    @Test
    public void test6() {
        //List<CategoryVo> categoryVos = categoryService.queryAllCategory();
        //categoryVos.forEach(categoryVo -> System.out.println("categoryVo = " + categoryVo));
        List<CategoryPo> categoryPos = categoryDao.queryAllcategory();
        categoryPos.forEach(categoryPo -> System.err.println("categoryPo = " + categoryPo));
    }

    @Test
    public void test7() {
        List<SecondVideoVo> secondVideoVos = videoService.querySecondVideo("12");
        secondVideoVos.forEach(secondVideoVo -> System.err.println("secondVideoVo = " + secondVideoVo));
    }

    @Test
    public void test8() {
        List<User> users = userDao.selectAll();
        users.forEach(user -> System.err.println("user = " + user));
        HashMap<String, Object> map = new HashMap<>();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("�û���Ϣ", "��Ϣone"), User.class, users);
        try {
            workbook.write(new FileOutputStream("E:\\yingx-user.xls"));
            map.put("message", "�����ɹ�");
            workbook.close();
        } catch (IOException e) {
            map.put("message", "���������쳣������ʧ��");
        }
    }

    @Test
    public void test9() {
        VideoDetailPo videoDetailPo = videoDao.queryByVideoDetail("78df6ffa-0e8d-4462-bd71-161c966c8ba2", "10", "438d48c8-af1c-4a90-b09a" +
                "-2f21b57223ea");
        System.err.println("videoDetailPo = " + videoDetailPo);
    }

    @Test
    public void test10() {
        UserDetailPo userDetailPo = userDao.queryByUserDateil("438d48c8-af1c-4a90-b09a-2f21b57223ea");
        System.err.println("userDetailPo = " + userDetailPo);
    }

    @Test
    public void test11() {
        String salt = MD5Util.getSalt();
        System.out.println("salt = " + salt);
    }

    @Test
    public void test12() {
        HashMap<String, Object> echarts = userService.Echarts();
        for (String s : echarts.keySet()) {
            System.err.println();
        }
    }

    @Test
    public void test13() {
        List<Month> newUsers = userDao.queryByMonthMan();
        System.err.println("newUsers = " + newUsers);
    }

    @Test
    public void test14() {
        ArrayList<Common> commons = userService.EchartsCity();
        commons.forEach(common -> System.err.println("common = " + common));
    }

    @Test
    public void test15() {
        //获取安全管理器工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:testUser.ini");
        //创建安全管理器
        SecurityManager instance = iniSecurityManagerFactory.createInstance();
        //将安全管理器交由工具类
        SecurityUtils.setSecurityManager(instance);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("shijt", "123456");
        try {
            //4、登录，即身份验证
            subject.login(token);
            System.out.println("登陆成功！");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            System.out.println("登陆失败！");
        }
        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }
}
