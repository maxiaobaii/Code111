package com.baizhi.service;

import com.baizhi.entity.Video;
import com.baizhi.po.AddVideoPo;
import com.baizhi.vo.AddVideoVo;
import com.baizhi.vo.SecondVideoVo;
import com.baizhi.vo.VideoDetailVo;
import com.baizhi.vo.VideoVo;

import java.util.HashMap;
import java.util.List;

/**
 * @InterfaceNmae: VideoService
 * @Author: yddm
 * @DateTime: 2020/8/28 13:21
 * @Description: TODO
 */

public interface VideoService {
    /**
     * 查询所有分类
     *
     * @param page
     * @param rows
     * @return
     */
    HashMap<String, Object> queryPageVideo(Integer page, Integer rows);

    /**
     * 添加分类
     *
     * @param video
     * @return
     */
    String add(Video video);

    /**
     * 编辑分类
     *
     * @param video
     */
    void edit(Video video);

    /**
     * 删除分类
     *
     * @param video
     */
    void del(Video video);

    /**
     * 删除分类
     *
     * @param video
     * @return
     */
    HashMap<String, Object> delete(Video video);

    /**
     * 展示所有一二级分类
     *
     * @return
     */
    List<VideoVo> queryFirst();

    /**
     * 首页添加视频
     *
     * @param id
     * @param videoTitle
     * @param cover
     * @param path
     * @param uploadTime
     * @param description
     * @param cateName
     * @param categoryId
     * @param userId
     */
    /*void addVideo(String id, String videoTitle, String cover, String path,
                  Data uploadTime, String description, String cateName,
                  String categoryId, String userId);*/

    /**
     * 首页添加视频
     *
     * @param addVideoPo
     */
    AddVideoVo addVideo(AddVideoPo addVideoPo);

    /**
     * 查询二级类型下的视频
     *
     * @param cateId
     * @return
     */
    List<SecondVideoVo> querySecondVideo(String cateId);

    /**
     * 查看视频详情
     *
     * @param videoId
     * @param cateId
     * @param userId
     * @return
     */
    VideoDetailVo queryByVideoDetail(String videoId, String cateId, String userId);

    /**
     * 查询一条视频信息
     *
     * @param video
     * @return
     */
    Video queryOneVideo(Video video);

    /**
     * ES查询
     *
     * @param content
     * @return
     */
    List<Video> queryEs(String content);

    //高亮查询
    List<Video> querySearch1(String content);
}
