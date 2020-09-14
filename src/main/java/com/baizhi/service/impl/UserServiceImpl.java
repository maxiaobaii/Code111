package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.annotation.AddLog;
import com.baizhi.dao.UserDao;
import com.baizhi.dao.VideoDao;
import com.baizhi.entity.City;
import com.baizhi.entity.Common;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.po.UserDetailPo;
import com.baizhi.po.UserMovingPo;
import com.baizhi.po.VideoPo;
import com.baizhi.po.userVideoPo;
import com.baizhi.service.UserService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.MD5Util;
import com.baizhi.vo.UserDetailVo;
import com.baizhi.vo.UserMovingVo;
import com.baizhi.vo.userVideoVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @ClassNmae: UserServiceImpl
 * @Author: yddm
 * @DateTime: 2020/8/25 18:14
 * @Description: TODO
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Resource
    VideoDao videoDao;

    @AddLog(value = "查询所有用户")
    @Override
    public HashMap<String, Object> queryAllUserPage(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<>();
        User user = new User();
        //分页
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //查所有
        List<User> users = userDao.selectByRowBounds(user, rowBounds);
        //总条数
        Integer records = userDao.selectCount(user);
        //总页数
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        map.put("page", page);
        map.put("total", total);
        map.put("records", records);
        map.put("rows", users);
        for (User user1 : users) {
            System.err.println("user1 = " + user1);
        }
        return map;
    }

    @AddLog(value = "添加用户")
    @Override
    public String add(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus("正常");
        user.setCreateDate(new Date());
        userDao.insertSelective(user);
        return user.getId();
    }

    @AddLog(value = "更新用户信息")
    @Override
    public void edit(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }

    @AddLog(value = "修改用户状态")
    @Override
    public String updataStatus(User user, HttpSession session) {
        session.setAttribute("userUpdata", user);
        userDao.updateByPrimaryKeySelective(user);
        return user.getId();
    }

    @Override
    public HashMap<String, Object> easyPoiWrite() {
        List<User> users = userDao.selectAll();
        System.out.println("数据库的用户数据");
        users.forEach(user -> System.err.println("user = " + user));
        ArrayList<User> users1 = new ArrayList<>();
        for (User user : users) {
            String[] split = user.getHeadImg().split("/");
            String img = split[4];
            String filePath = "E:\\image\\" + img;
            AliyunOssUtil.testDownload("photo/" + img, filePath);
            user.setHeadImg(filePath);
            users1.add(user);
        }
        System.out.println("导出后的用户数据");
        users1.forEach(user -> System.out.println("user = " + user));
        HashMap<String, Object> map = new HashMap<>();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户信息",
                "信息one"), User.class, users1);
        try {
            workbook.write(new FileOutputStream("E:\\yingx-user.xls"));
            map.put("message", "导出成功");
            workbook.close();
        } catch (IOException e) {
            map.put("message", "导出出现异常，导出失败");
        }
        return map;
    }

    @Override
    public List<UserMovingVo> UserDynamics(String userId) {
        ArrayList<UserMovingVo> userMovingVos = new ArrayList<>();
        try {
            List<UserMovingPo> ump = userDao.UserDynamics(userId);
            List<VideoPo> videoPos = videoDao.queryFirst();
            for (UserMovingPo userMovingPo : ump) {
                UserMovingVo userMovingVo = new UserMovingVo(userMovingPo.getId(), userMovingPo.getVideoTitle(),
                        userMovingPo.getCover(), userMovingPo.getPath(), userMovingPo.getUploadTime(),
                        userMovingPo.getDescription(), userMovingPo.getCateName(), userMovingPo.getCategoryId(),
                        userMovingPo.getUserId(), userMovingPo.getUserName(), userMovingPo.getUserPicImg(),
                        2, 10, true, videoPos);
                userMovingVos.add(userMovingVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userMovingVos;
    }

    @Override
    public UserDetailVo queryByUserDetail(String userId) {
        UserDetailPo udp = userDao.queryByUserDateil(userId);
        System.err.println("udp = " + udp);
        String status = null;
        if ("正常".equals(udp.getStatus())) {
            status = "1";
        } else {
            status = "0";
        }
        UserDetailVo udv = new UserDetailVo(udp.getId(), udp.getUsername(), udp.getPicImg(),
                udp.getPhone(), "123456", udp.getIntroduction(), MD5Util.getSalt(),
                status, udp.getWechat(), "10", udp.getRegisterDate());
        return udv;
    }

    @Override
    public List<userVideoVo> queryByUserVideo(String userId) {
        ArrayList<userVideoVo> userVideoVos = new ArrayList<>();
        try {
            List<userVideoPo> userVideoPos = userDao.queryByUserVideo(userId);
            for (userVideoPo uvp : userVideoPos) {
                userVideoVo userVideoVo = new userVideoVo(uvp.getId(), uvp.getVideoTitle(),
                        uvp.getCover(), uvp.getPath(), uvp.getUploadTime(), uvp.getDescription(),
                        uvp.getCateName(), uvp.getCategoryId(), uvp.getUserId(), 10);
                userVideoVos.add(userVideoVo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userVideoVos;
    }

    @Override
    public HashMap<String, Object> Echarts() {
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> lists = new ArrayList<>();
        List<Month> months = userDao.queryByMonthMan();
        List<Month> months1 = userDao.queryByMonthWoMan();
        for (int i = 0; i <= 11; i++) {
            list.add(i, "0");
        }
        for (Month month : months) {
            if (month.getMonth().equals("1月")) {
                list.add(0, month.getCount());
            } else if (month.getMonth().equals("2月")) {
                list.add(1, month.getCount());
            } else if (month.getMonth().equals("3月")) {
                list.add(2, month.getCount());
            } else if (month.getMonth().equals("4月")) {
                list.add(3, month.getCount());
            } else if (month.getMonth().equals("5月")) {
                list.add(4, month.getCount());
            } else if (month.getMonth().equals("6月")) {
                list.add(5, month.getCount());
            } else if (month.getMonth().equals("7月")) {
                list.add(6, month.getCount());
            } else if (month.getMonth().equals("8月")) {
                list.add(7, month.getCount());
            } else if (month.getMonth().equals("9月")) {
                list.add(8, month.getCount());
            } else if (month.getMonth().equals("10月")) {
                list.add(9, month.getCount());
            } else if (month.getMonth().equals("11月")) {
                list.add(10, month.getCount());
            } else if (month.getMonth().equals("12")) {
                list.add(11, month.getCount());
            }
        }

        for (int i = 0; i <= 11; i++) {
            lists.add(i, "0");
        }
        for (Month month : months1) {
            if (month.getMonth().equals("1月")) {
                lists.add(0, month.getCount());
            } else if (month.getMonth().equals("2月")) {
                lists.add(1, month.getCount());
            } else if (month.getMonth().equals("3月")) {
                lists.add(2, month.getCount());
            } else if (month.getMonth().equals("4月")) {
                lists.add(3, month.getCount());
            } else if (month.getMonth().equals("5月")) {
                lists.add(4, month.getCount());
            } else if (month.getMonth().equals("6月")) {
                lists.add(5, month.getCount());
            } else if (month.getMonth().equals("7月")) {
                lists.add(6, month.getCount());
            } else if (month.getMonth().equals("8月")) {
                lists.add(7, month.getCount());
            } else if (month.getMonth().equals("9月")) {
                lists.add(8, month.getCount());
            } else if (month.getMonth().equals("10月")) {
                lists.add(9, month.getCount());
            } else if (month.getMonth().equals("11月")) {
                lists.add(10, month.getCount());
            } else if (month.getMonth().equals("12")) {
                lists.add(11, month.getCount());
            }
        }

        map.put("month", Arrays.asList("1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"));//先查一共有多少月份
        map.put("boys", list);//
        map.put("girls", lists);
        return map;
    }

    @Override
    public ArrayList<Common> EchartsCity() {

        ArrayList<Common> common = new ArrayList<>();
        //小男孩分布的城市
        List<City> cityman = userDao.selectUserManByCity();

        //小女孩分布的城市
        List<City> cityboy = userDao.selectUserWomenByCity();

        common.add(new Common("男生", cityman));
        common.add(new Common("女生", cityboy));
        return common;
    }
}
