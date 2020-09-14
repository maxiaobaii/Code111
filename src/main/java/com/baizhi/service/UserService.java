package com.baizhi.service;

import com.baizhi.entity.Common;
import com.baizhi.entity.User;
import com.baizhi.vo.UserDetailVo;
import com.baizhi.vo.UserMovingVo;
import com.baizhi.vo.userVideoVo;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceNmae: UserService
 * @Author: yddm
 * @DateTime: 2020/8/25 18:14
 * @Description: TODO
 */

public interface UserService {
    /**
     * 展示所有用户  分页
     *
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> queryAllUserPage(Integer page, Integer rows);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public String add(User user);

    /**
     * 编辑信息
     *
     * @param user
     */
    public void edit(User user);

    /**
     * 修改状态
     *
     * @param user
     * @param session
     * @return
     */
    String updataStatus(User user, HttpSession session);

    /**
     * 导出
     *
     * @return
     */
    HashMap<String, Object> easyPoiWrite();

    /**
     * 动态
     *
     * @param userId
     * @return
     */
    List<UserMovingVo> UserDynamics(String userId);

    /**
     * 用户信息
     *
     * @param userId
     * @return
     */
    UserDetailVo queryByUserDetail(String userId);

    /**
     * 查询用户发布的视频
     *
     * @param userId
     * @return
     */
    List<userVideoVo> queryByUserVideo(String userId);

    /**
     * 各月份用户注册统计
     *
     * @return
     */
    HashMap<String, Object> Echarts();

    /**
     * 用户分布
     *
     * @return
     */
    ArrayList<Common> EchartsCity();
}
