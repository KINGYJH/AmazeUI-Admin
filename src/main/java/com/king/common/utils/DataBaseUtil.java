package com.king.common.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

/**
 * Created by YJH
 * on 2017/8/1 11:21.
 * 注释:
 */
public class DataBaseUtil {

    private static JdbcTemplate jdbcTemplate = SpringContextHolder.getBean(JdbcTemplate.class);

    /**
     * 验证表是否存在
     *
     * @param tableName 表名
     * @return true代表存在false反之
     */
    public static boolean tableIsExist(String tableName) {
        String sql = "SELECT COUNT(*) FROM information_schema.TABLES where TABLE_SCHEMA = 'admin' AND TABLE_NAME = '" + tableName + "'";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        if (rowSet.next()) {
            int count = rowSet.getInt(1);
            return count != 0;
        }
        return false;
    }
}
