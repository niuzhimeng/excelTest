package com.nzm.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component("configuration")
public class Configuration {

    private static Properties properties;

    private static Configuration INSTANCE;
    private final static String DEFAULT_PROPERTIES = "/mis.properties";

    static Configuration getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Configuration();
        return INSTANCE;
    }

    /**
     * 初始化Configuration类
     */
    private Configuration() {
        properties = new Properties();

    }

    /**
     * 重载函数，得到key的值
     *
     * @param key 取得其值的键
     * @return key的值
     */
    public String getPropertiesValue(String propertiesFile, String key) {
        try {
            if (StringUtils.isNotBlank(propertiesFile)) {
                loadProperties(propertiesFile);
            } else {
                loadProperties(DEFAULT_PROPERTIES);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
            return "";
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
            return "";
        }

        if (properties.containsKey(key)) {
            //得到某一属性的值
            return properties.getProperty(key);
        } else
            return "";
    }

    private void loadProperties(String property) throws IOException {
        try {
            InputStream in;
            in = this.getClass().getResourceAsStream(property);
            properties.load(in);
            in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("读取属性文件--->失败！- 原因：文件路径错误或者文件不存在");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("装载文件--->失败!");
            ex.printStackTrace();
        }
    }
}
