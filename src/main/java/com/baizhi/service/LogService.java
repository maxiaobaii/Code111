package com.baizhi.service;

import com.baizhi.entity.Log;

import java.util.HashMap;

/**
 * @InterfaceNmae: LogService
 * @Author: yddm
 * @DateTime: 2020/8/31 21:48
 * @Description: TODO
 */

public interface LogService {
    /**
     * 展示所有日志
     *
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> ShowLog(Integer page, Integer rows);

    /**
     * 添加日志
     *
     * @param log
     */
    void addLog(Log log);

    /**
     * 删除日志
     *
     * @param log
     */
    void delete(Log log);
}
