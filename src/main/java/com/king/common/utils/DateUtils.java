package com.king.common.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * Created by YJH
 * on 2017/8/4 10:09.
 * 注释: 时间工具类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String parseString(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

}
