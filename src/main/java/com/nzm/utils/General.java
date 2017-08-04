package com.nzm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 通用工具类
 * Created by nzm on 2017/6/2.
 */
public class General {
    /**
     * 获取uuid
     *
     * @return 字符串类型的uuid
     */
    public static String getUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前的时间(标准格式)
     */
    public static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * 获取到图片名称中 后拼接的信息
     *
     * @param fullName 图片全名称
     */
    public static String getAddInfo(String fullName) {
        int i = fullName.lastIndexOf("#");
        int j = fullName.lastIndexOf(".");
        return fullName.substring(i + 1, j);
    }

    /**
     * 获取文件扩展名
     *
     * @param fullName 图片全名称
     */
    public static String getExtName(String fullName) {
        int j = fullName.lastIndexOf(".");
        return fullName.substring(j);
    }

    /**
     * 获取原始文件名
     *
     * @param fullName 图片全名称
     */
    public static String getFileName(String fullName) {
        int i = fullName.lastIndexOf("#");
        return fullName.substring(0, i);
    }

    /**
     * 获取文件类型(image, file)
     *
     * @param extName 文件后缀名
     */
    public static String getFileType(String extName) {
        List<String> imageExtName = Arrays.asList(".BMP", ".JPG", ".JPEG", ".PNG", ".GIF", ".SVG", ".PSD", ".TIFF",
                ".bmp", ".jpg", ".jpeg", ".png", ".gif", ".svg", ".psd", ".tiff");
        if (imageExtName.contains(extName)) {
            return "image";
        }
        return "file";
    }
}
