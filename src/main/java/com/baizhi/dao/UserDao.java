package com.baizhi.dao;

import com.baizhi.entity.City;
import com.baizhi.entity.Month;
import com.baizhi.entity.User;
import com.baizhi.po.UserDetailPo;
import com.baizhi.po.UserMovingPo;
import com.baizhi.po.userVideoPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @InterfaceNmae: UserDao
 * @Author: yddm
 * @DateTime: 2020/8/25 14:52
 * @Description: TODO
 */

public interface UserDao extends Mapper<User> {
    /**
     * 查询关注用户动态
     *
     * @param userId
     * @return
     */
    List<UserMovingPo> UserDynamics(String userId);

    /**
     * 展示用户信息
     *
     * @param userId
     * @return
     */
    UserDetailPo queryByUserDateil(@Param("userId") String userId);

    /**
     * 查询用户发布的视频
     *
     * @param userId
     * @return
     */
    List<userVideoPo> queryByUserVideo(String userId);

    /**
     * 查询每月的用户注册量
     *
     * @return
     */
    List<Month> queryByMonthMan();

    List<Month> queryByMonthWoMan();

    /**
     * 查询城市分布
     *
     * @return
     */
    List<City> selectUserManByCity();

    List<City> selectUserWomenByCity();
}
