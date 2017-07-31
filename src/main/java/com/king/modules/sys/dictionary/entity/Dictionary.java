package com.king.modules.sys.dictionary.entity;

import com.king.common.persistence.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author by yjh
 * @DateTime 2017/7/12 19:54
 * 数据字典
 */
@Entity
@Table(name = "sys_dictionary")
public class Dictionary extends BaseEntity<Dictionary> {

    private String dataKey;     //键
    private String dataCode;    //编码
    private String dataValue;   //值
    private Integer sort;       //排序

    public Dictionary() {
        super();
    }

    @Column(name = "data_key")
    public String getDataKey() {
        return dataKey;
    }

    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    @Column(name = "data_code")
    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    @Column(name = "data_value")
    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
