package com.baizhi.controller;

import com.baizhi.annotation.AddLog;
import com.baizhi.entity.Video;
import com.baizhi.repository.VideoRepository;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.IntercepterPhotoUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassNmae: VideoController
 * @Author: yddm
 * @DateTime: 2020/8/28 13:18
 * @Description: TODO
 */

@Controller
@RequestMapping("video")
public class VideoController {
    @Resource
    VideoService videoService;
    @Resource
    VideoRepository videoRepository;

    /**
     * 查询所有视频，
     *
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("queryPageVideo")
    @ResponseBody
    public HashMap<String, Object> queryPageVideo(Integer page, Integer rows) {
        HashMap<String, Object> map = videoService.queryPageVideo(page, rows);
        return map;
    }

    /**
     * 对视频进行编辑
     * add():添加视频
     * del():删除视频
     *
     * @param video
     * @param oper
     * @return
     */
    @AddLog(value = "编辑视频")
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String, Object> edit(Video video, String oper) {
        HashMap<String, Object> map = new HashMap<>();
        if ("add".equals(oper)) {
            String id = videoService.add(video);
            map.put("message", id);
        }
        if ("del".equals(oper)) {
            map = videoService.delete(video);
        }
        return map;
    }

    /**
     * 视频封面
     *
     * @param id
     * @param path
     * @param request
     */
    @AddLog(value = "更新视频")
    @RequestMapping("uploadVideo")
    public void uploadVideo(String id, MultipartFile path, HttpServletRequest request) {
        System.err.println("id = " + id);
        System.err.println("path = " + path);
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/bootstrap/img");
        //获取上传的文件名
        String name = System.currentTimeMillis() + "-" + path.getOriginalFilename();
        //1.进行服务器文件上传
        AliyunOssUtil.uploadByteFile(path, "video/" + name);
        //获取远程视频存放路径
        String fetFilePath = "https://yingx-sjt.oss-cn-beijing.aliyuncs.com/video/" + name;

        //获取封面的文件名   1585660250687-xxx.jpg
        //1585660250687-火花       mp4
        String[] split = name.split("\\.");
        String fileName = split[0] + ".jpg";
        String filePath = realPath + "\\" + fileName;
        //截取视频封面
        try {
            IntercepterPhotoUtil.fetchFrame(fetFilePath, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //上传本地封面文件
        AliyunOssUtil.uploadLocalFile("photo/" + fileName, filePath);

        //删除本地封面文件
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            file.delete();
        }

        //2.修改视频数据
        Video video = new Video();
        video.setId(id);
        video.setPath(fetFilePath);
        video.setCover("https://yingx-sjt.oss-cn-beijing.aliyuncs.com/photo/" + fileName);
        videoService.edit(video);

        //向ES中添加索引
        Video video1 = videoService.queryOneVideo(video);
        videoRepository.save(video1);
    }

    @RequestMapping("queryEs")
    @ResponseBody
    public List<Video> queryEs(String content) {
        return videoService.queryEs(content);
    }

    @RequestMapping("queryEs1")
    @ResponseBody
    public List<Video> queryEs1(String content) {
        return videoService.querySearch1(content);
    }
}

