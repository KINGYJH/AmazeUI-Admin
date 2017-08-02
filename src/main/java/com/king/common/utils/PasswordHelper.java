package com.king.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * Created by YJH
 * on 2017/8/2 10:34.
 * 注释: 密码工具类
 */
public class PasswordHelper {

    private final static String algorithmName = "md5";   //加密类型

    /**
     * 加密
     *
     * @param pwd
     * @return
     */
    public static String encryptPwd(String pwd) {
        return new SimpleHash(algorithmName, pwd).toHex();
    }

    /**
     * 验证密码是否相等
     *
     * @param pwd_1
     * @param pwd_2
     * @return
     */
    public static boolean equalsPwd(String pwd_1, String pwd_2) {
        String newPassword = new SimpleHash(algorithmName, pwd_2).toHex();

        return pwd_1.equals(newPassword);
    }

}
