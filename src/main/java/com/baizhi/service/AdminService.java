package com.baizhi.service;

import com.baizhi.entity.Admin;

import java.util.HashMap;

/**
 * @InterfaceNmae: AdminService
 * @Author: yddm
 * @DateTime: 2020/8/24 18:11
 * @Description: TODO
 */

public interface AdminService {
    /**
     * 管理员登陆
     *
     * @param admin
     * @param code
     * @return
     */
    HashMap<String, Object> queryByUsername(Admin admin, String code);
}
