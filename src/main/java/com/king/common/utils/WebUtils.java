package com.king.common.utils;

/**
 * Created by YJH
 * on 2017/7/26 10:46.
 * 注释:
 */
public class WebUtils {

    public static String getPath() {
        String path = WebUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (path.indexOf("WEB-INF") > 0) {
            path = path.substring(0, path.indexOf("WEB-INF/classes"));
        }
        path = path.replaceAll("%20", " ");
        return path;
    }

    public static void main(String[] args) {

        System.out.println(WebUtils.getPath());
    }
}
