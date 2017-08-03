package com.king.common.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局配置类
 *
 * @author by yjh
 * @DateTime 2017/6/30 20:09
 */
public class Global {

    public static final String SESSION_USER = "sessionUser";

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<>();

    /**
     * 获取配置
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = PropertiesLoader.getProperty(key);
            map.put(key, value);
        }
        return value;
    }

    /**
     * 获取管理端根路径
     */
    public static String getAdminPath() {
        return getConfig("adminPath");
    }
}
