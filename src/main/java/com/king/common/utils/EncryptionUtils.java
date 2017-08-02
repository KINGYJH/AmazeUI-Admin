package com.king.common.utils;

import java.security.MessageDigest;

/**
 * Created by YJH
 * on 2017/8/2 10:26.
 * 注释:
 */
public class EncryptionUtils {


    /**
     * 加密字符串
     *
     * @param src
     * @return
     */
    public static String MD5(String src) {
        return MD5(src, 32, true, "UTF-8");
    }

    /**
     * md5 加密
     *
     * @param src     加密字符串
     * @param length  长度
     * @param upCase  是否转换为大写
     * @param charset 字符编码
     * @return 加密后字符串
     */
    public static String MD5(String src, int length, boolean upCase, String charset) {

        if (org.apache.commons.lang3.StringUtils.isEmpty(src)) {
            throw new NullPointerException("The src must not be null.");
        }

        if (!(length == 16 || length == 32)) {
            throw new IllegalArgumentException("The length value only is 16 or 32.");
        }

        String result = null;
        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            byte[] md = mdInst.digest(src.getBytes(charset));
            StringBuilder sb = new StringBuilder();
            for (byte b : md) {
                int val = b & 0xff;
                if (val < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(val));
            }

            result = length == 16 ? sb.substring(8, 23) : sb.toString();

            return upCase ? result.toUpperCase() : result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

}
