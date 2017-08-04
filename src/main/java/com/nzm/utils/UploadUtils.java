package com.nzm.utils;


import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class UploadUtils {


    public final static String MODULE = "base";

    public final static String FILE = "file";

    public final static String IMAGE = "image";

    private final static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMdd/HHmmss");
    /**
     * 默认的文件保存地址
     */
    private final static String FILE_SAVE_PATH = "excelSavePath";

    private static List<String> imageExtName;

    static {
        imageExtName = Arrays.asList("BMP", "JPG", "JPEG", "PNG", "GIF", "SVG", "PSD", "TIFF",
                "bmp", "jpg", "jpeg", "png", "gif", "svg", "psd", "tiff");
    }


    public static String getFileDate() {
        return sdfDate.format(new Date());
    }

    /**
     * 获取文件路径
     */
    public static String gitFilePath(String companyId, String module, String fileType) {
        if (StringUtils.isBlank(module)) {
            module = MODULE;
        }
        return File.separator + companyId + File.separator + module + File.separator + fileType + File.separator +
                getFileDate() + File.separator;
    }

    /**
     * 获取配置文件中key对应的值
     *
     * @param keyName 指定的key(使用默认的key传null)
     * @return 读取到的value值
     */
    public static String getPropertyValue(String propertiesName,String keyName) {
        return Configuration.getInstance().getPropertiesValue(propertiesName, keyName);
    }

    /**
     * 获取扩展名
     *
     * @param file 上传文件
     * @return
     */
    public static String getFileExtName(MultipartFile file) {
        //获取上传文件的原始名称
        String originalFilename = file.getOriginalFilename();
        //文件扩展名
        String fileEx = originalFilename.substring(originalFilename.lastIndexOf("."));
        return fileEx;
    }

    /**
     * 根据上传文件获取文件全路径
     *
     * @param fileConfigDir 配置文件中的文件目录
     * @param module        上传模块
     * @param fileType      文件类型 （file image）
     * @param fileId        文件仓库主健ID
     * @return
     */
    public static String getFileFullPath(String fileConfigDir, String module, String fileType, String fileId, String fileEx) {
        //设置上传文件的保存目录
        String dirPath = fileConfigDir + UploadUtils.gitFilePath("", module, fileType);
        File dir = new File(dirPath);
        //如果保存文件的地址不存在，就先创建目录
        if (!dir.exists()) {
            dir.mkdirs();
        }
        //使用UUID重新命名上传的文件名称（看公司需求，也可以用日期时间）
        String filePath = dirPath + fileId + fileEx;
        return filePath;
    }

    /**
     * 读取 am.properties配置文件中的信息
     *
     * @param key 配置文件的key值
     */
    public static String readAmProperties(String key) throws Exception {
        Resource resource = new ClassPathResource("am.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        return (String) props.get(key);
    }

    /**
     * 获取文件扩展名
     *
     * @param fullName 图片全名称
     */
    public static String getExtName(String fullName) {
        int j = fullName.lastIndexOf(".");
        return fullName.substring(j + 1);
    }

    /**
     * 获取文件名（不加后缀名）
     *
     * @param fullName 图片全名称
     */
    public static String getFileName(String fullName) {
        int i = fullName.lastIndexOf(".");
        return fullName.substring(0, i);
    }

    /**
     * 获取文件类型(image, file)
     */
    public static String getFileType(String extName) {
        if (imageExtName.contains(extName)) {
            return "image";
        }
        return "file";
    }

    /**
     * 根据file_info表中的path，截取出文件保存时的名字
     *
     * @param path 文件保存全路径
     * @return
     */
    public static String getSaveFileName(String path) {
        int i = path.lastIndexOf("/");
        return path.substring(i + 1);
    }
}
