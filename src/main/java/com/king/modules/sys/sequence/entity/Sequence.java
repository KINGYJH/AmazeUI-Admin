package com.king.modules.sys.sequence.entity;

import com.king.common.persistence.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:28
 */
@Entity
@Table(name = "sys_sequence")
public class Sequence extends BaseEntity<Sequence> {

    private String tableName;       //表名
    private String describes;       //描述
    private String pkName;          //主键名称
    private String prefix;          //前缀
    private Integer length;         //长度
    private Integer newValue = 0;       //当前值

    @Column(name = "table_name", length = 50)
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    @Column(name = "describes")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @Column(name = "pk_name", length = 20)
    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    @Column(name = "prefix", length = 10)
    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Column(name = "length")
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Column(name = "new_value")
    public Integer getNewValue() {
        return newValue;
    }

    public void setNewValue(Integer newValue) {
        this.newValue = newValue;
    }
}
