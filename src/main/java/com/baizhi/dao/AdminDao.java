package com.baizhi.dao;

import com.baizhi.entity.Admin;
import tk.mybatis.mapper.common.Mapper;

/**
 * @InterfaceNmae: AdminDao
 * @Author: yddm
 * @DateTime: 2020/8/24 14:47
 * @Description: TODO
 */

public interface AdminDao extends Mapper<Admin> {
    /**
     * 管理员登陆
     *
     * @param username
     * @return
     */
    Admin queryByUsername(String username);
}
