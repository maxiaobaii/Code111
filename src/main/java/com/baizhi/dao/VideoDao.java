package com.baizhi.dao;

import com.baizhi.entity.Video;
import com.baizhi.po.SecondVideoPo;
import com.baizhi.po.VideoDetailPo;
import com.baizhi.po.VideoPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * @InterfaceNmae: VideoDao @Author: yddm @DateTime: 2020/8/28 13:19 @Description: TODO
 */
public interface VideoDao extends Mapper<Video> {
    /**
     * 首页展示所有视频
     *
     * @return
     */
    List<VideoPo> queryFirst();

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
    void addVideo(
            @Param("id") String id,
            @Param("videoTitle") String videoTitle,
            @Param("cover") String cover,
            @Param("path") String path,
            @Param("uploadTime") Data uploadTime,
            @Param("description") String description,
            @Param("cateName") String cateName,
            @Param("categoryId") String categoryId,
            @Param("userId") String userId);

    /**
     * 查询二级类型对应的视频
     *
     * @param cateId
     * @return
     */
    List<SecondVideoPo> querySecondVideo(String cateId);

    /**
     * 查看视频详细信息
     *
     * @param videoId
     * @param cateId
     * @param userId
     * @return
     */
    VideoDetailPo queryByVideoDetail(
            @Param("videoId") String videoId,
            @Param("cateId") String cateId,
            @Param("userId") String userId);
}
