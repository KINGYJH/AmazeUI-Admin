package com.king.modules.sys.dictionary.entity;

import com.king.common.persistence.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author by yjh
 * @DateTime 2017/7/12 19:54
 * 数据字典
 */
@Entity
@Table(name = "sys_dictionary")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Dictionary extends BaseEntity<Dictionary> {

    private String dataKey;     //键
    private String dataValue;   //值
    private String showValue;   //显示值
    private String describes;   //描述
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

    @Column(name = "data_value")
    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
    }

    @Column(name = "show_value")
    public String getShowValue() {
        return showValue;
    }

    public void setShowValue(String showValue) {
        this.showValue = showValue;
    }

    @Column(name = "describes")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
