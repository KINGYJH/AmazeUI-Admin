package com.king.common.utils;

import org.apache.commons.lang3.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by YJH
 * on 2017/8/3 10:45.
 * 注释:
 */
public class HttpUtils {

    private static String[] HTTP_PROXY_HEADER_NAME = new String[]{
            "CLIENTIP",
            "X-FORWARDED-FOR"
    };

    /**
     * 获取客户端IP
     *
     * @param request
     * @return
     */
    public static String getClientIP(HttpServletRequest request) {
        for (String headerName : HTTP_PROXY_HEADER_NAME) {
            String clientIP = request.getHeader(headerName);
            if (org.apache.commons.lang3.StringUtils.isNotBlank(clientIP)) {
                return clientIP;
            }
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取客户端平台
     *
     * @param request
     * @return
     */
    public static String getLoginPlatform(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if (userAgent.contains(LoginPlatform.WINDOW.name())) {
            return LoginPlatform.WINDOW.getName();
        } else if (userAgent.contains(LoginPlatform.IPHONE.name())) {
            return LoginPlatform.IPHONE.getName();
        } else if (userAgent.contains(LoginPlatform.ANDROID.name())) {
            return LoginPlatform.ANDROID.getName();
        }else if(userAgent.contains(LoginPlatform.LINUX.name())){
            return LoginPlatform.LINUX.getName();
        }
        return null;
    }

    private enum LoginPlatform {
        WINDOW("PC", 0),
        IPHONE("iPhone", 1),
        ANDROID("Android", 2),
        LINUX("Linux",3);

        private String name;
        private int value;

        LoginPlatform(String name, int value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public int getValue() {
            return value;
        }
    }

}
