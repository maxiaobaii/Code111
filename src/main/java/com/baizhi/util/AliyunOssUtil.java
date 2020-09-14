package com.baizhi.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

public class AliyunOssUtil {
    private static String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private static String accessKeyId = "LTAI4G5UMgiJX68FJdio9jnG";
    private static String accessKeySecret = "Lr2RmJvktEgy84j2tgmeBNwJHZX9Iz";
    private static String bucketName = "yingx-sjt";

    /*
     * 上传本地文件
     * 参数：
     * fileName:上传的文件名
     * localPath:指定的本地的文件路径
     * */
    public static void uploadLocalFile(String fileName, String localPath) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, new File(localPath));
        // 上传文件。
        ossClient.putObject(putObjectRequest);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /*
     * 上传byte数组
     *
     * */
    public static void uploadByteFile(MultipartFile path, String fileName) {
        byte[] content = new byte[0];
        try {
            content = path.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 上传Byte数组。
        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /*
     * 删除文件方法
     * 参数：
     *     删除的远程文件名
     * */
    public static void TestDelete(String objectName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void testDownload(String objectName, String localPath) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File(localPath));

        // 关闭OSSClient。
        ossClient.shutdown();

    }
}
