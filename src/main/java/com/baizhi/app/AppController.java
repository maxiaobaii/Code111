package com.baizhi.app;

import com.baizhi.common.ResultCommon;
import com.baizhi.po.AddVideoPo;
import com.baizhi.service.CategoryService;
import com.baizhi.service.UserService;
import com.baizhi.service.VideoService;
import com.baizhi.util.AliyunOssUtil;
import com.baizhi.util.AliyunSendPhoneUtil;
import com.baizhi.util.IntercepterPhotoUtil;
import com.baizhi.vo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @ClassNmae: AppController
 * @Author: yddm
 * @DateTime: 2020/9/1 10:52
 * @Description: TODO
 */

@RestController
@RequestMapping("app")
public class AppController {
    @Resource
    VideoService videoService;
    @Resource
    CategoryService categoryService;
    @Resource
    HttpServletRequest request;
    @Resource
    UserService userService;

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping("getPhoneCode")
    public ResultCommon getPhoneCode(String phone) {
        //获取验证码
        String code = AliyunSendPhoneUtil.getCode();
        //保存验证码
        //发送短信
        String message = AliyunSendPhoneUtil.sendCode(phone, code);
        if (message.equals("发送成功")) {
            return new ResultCommon(phone, "验证码发送成功", "100");
        } else {
            return new ResultCommon(phone, "验证码发送成功", "104");
        }
    }

    /**
     * 查询首页视频
     *
     * @return
     */
    @RequestMapping("queryByReleaseTime")
    public ResultCommon queryByReleaseTime() {
        List<VideoVo> videoVos = videoService.queryFirst();
        if (videoVos.size() != 0) {
            return new ResultCommon(videoVos, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    /**
     * 查询分类
     *
     * @return
     */
    @RequestMapping("queryAllCategory")
    public ResultCommon queryAllCategory() {
        List<CategoryVo> categoryVos = categoryService.queryAllCategory();
        if (categoryVos.size() != 0) {
            return new ResultCommon(categoryVos, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    /**
     * 查看二级分类下的视频
     *
     * @param cateId
     * @return
     */
    @RequestMapping("queryCateVideoList")
    public ResultCommon queryCateVideoList(String cateId) {
        List<SecondVideoVo> secondVideoVos = videoService.querySecondVideo(cateId);
        if (secondVideoVos.size() != 0) {
            return new ResultCommon(secondVideoVos, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    /**
     * 发布视频
     *
     * @param description
     * @param videoFile
     * @param videoTitle
     * @param categoryId
     * @param userId
     * @return
     */
    @RequestMapping("save")
    public ResultCommon save(String description, MultipartFile videoFile,
                             String videoTitle, String categoryId, String userId) {
        //获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/bootstrap/img");
        //获取上传的文件名
        String name = System.currentTimeMillis() + "-" + videoFile.getOriginalFilename();
        //1.进行服务器文件上传
        AliyunOssUtil.uploadByteFile(videoFile, "video/" + name);
        //获取远程视频存放路径
        String fetFilePath = "https://yingx-sjt.oss-cn-beijing.aliyuncs.com/video/" + name;

        //封面
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
        AddVideoPo addVideoPo = new AddVideoPo(UUID.randomUUID().toString(), videoTitle,
                "https://yingx-sjt.oss-cn-beijing.aliyuncs.com/photo/" + fileName,
                fetFilePath, new Date(), description, null, categoryId,
                UUID.randomUUID().toString());
        AddVideoVo addVideoVo = videoService.addVideo(addVideoPo);
        if (addVideoVo != null) {
            return new ResultCommon(null, "添加成功", "100");
        } else {
            return new ResultCommon(null, "添加失败", "104");
        }
    }

    /**
     * 查看视频详情
     *
     * @param videoId
     * @param cateId
     * @param userId
     * @return
     */
    @RequestMapping("queryByVideoDetail")
    public ResultCommon queryByVideoDetail(String videoId, String cateId, String userId) {
        VideoDetailVo videoDetailVo = videoService.queryByVideoDetail(videoId, cateId, userId);
        if (videoDetailVo != null) {
            return new ResultCommon(videoDetailVo, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    /**
     * 动态
     *
     * @param userId
     * @return
     */
    @RequestMapping("queryByUserMoving")
    public ResultCommon UserMoving(String userId) {
        List<UserMovingVo> userMovingVos = userService.UserDynamics(userId);
        if (userMovingVos.size() != 0) {
            return new ResultCommon(userMovingVos, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    @RequestMapping("queryByUserDetail")
    public ResultCommon queryByUserDetail(String userId) {
        UserDetailVo userDetailVo = userService.queryByUserDetail(userId);
        if (userDetailVo != null) {
            return new ResultCommon(userDetailVo, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

    @RequestMapping("queryByUserVideo")
    public ResultCommon queryByUserVideo(String userId) {
        List<userVideoVo> userVideoVos = userService.queryByUserVideo(userId);
        if (userVideoVos.size() != 0) {
            return new ResultCommon(userVideoVos, "查询成功", "100");
        } else {
            return new ResultCommon(null, "查询失败", "104");
        }
    }

}
