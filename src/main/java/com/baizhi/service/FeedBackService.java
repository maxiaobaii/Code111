package com.baizhi.service;

import com.baizhi.entity.FeedBack;

import java.util.HashMap;

/**
 * @InterfaceNmae: FeedBackService
 * @Author: yddm
 * @DateTime: 2020/8/28 15:50
 * @Description: TODO
 */

public interface FeedBackService {
    /**
     * 展示所有反馈
     *
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> ShowFeed(Integer page, Integer rows);

    /**
     * 添加反馈
     *
     * @param feedBack
     */
    void add(FeedBack feedBack);

    /**
     * 编辑反馈
     *
     * @param feedBack
     */
    void update(FeedBack feedBack);

    /**
     * 删除反馈
     *
     * @param feedBack
     */
    void delete(FeedBack feedBack);
}
