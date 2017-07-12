package com.king.modules.sys.dictionary.entity;

import com.king.common.persistence.BaseEntity;

import java.util.Date;

/**
 * @author by yjh
 * @DateTime 2017/7/12 19:54
 * 数据字典
 */
public class Dictionary extends BaseEntity<Dictionary> {

    private String dataKey;
    private String dataValue;
    private Integer sort;

    public Dictionary() {
        super();
    }

    public Dictionary(String dataKey, String value, Integer sort) {
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.sort = sort;
    }

    public Dictionary(String id, String createUserId, String createUserName, Date createDate, String updateUserId, String updateUserName, Date updateDate, String dataKey, String dataValue, Integer sort) {
        super(id, createUserId, createUserName, createDate, updateUserId, updateUserName, updateDate);
        this.dataKey = dataKey;
        this.dataValue = dataValue;
        this.sort = sort;
    }

    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
