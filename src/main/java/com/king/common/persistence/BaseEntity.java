package com.king.common.persistence;

import com.king.common.annotation.DbInsertBefore;
import com.king.common.annotation.DbUpdateBefore;
import com.king.common.exception.ConcurrencyException;
import com.king.modules.sys.sequence.util.SequenceUtil;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;

/**
 * Created by YJH
 * on 2017/7/10 14:27.
 * 注释: 实体通用字段类
 */
@MappedSuperclass
public class BaseEntity<T> implements Serializable {

    private String id;              //id

    private Integer version;        //数据版本号

    private String createUserId;    //创建者id

    private String createUserName;  //创建者名称

    private Date createDate;        //创建时间

    private String updateUserId;    //更新者id

    private String updateUserName;  //更新者名称

    private Date updateDate;        //更新时间

    @DbInsertBefore
    public void insertBefore() {
        String tableName = "";
        Class<T> tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

        Table tb = tClass.getAnnotation(Table.class);
        if (null != tb) {
            tableName = tb.name();
            System.out.println("表名为：" + tableName);
        }

        this.id = SequenceUtil.getNextId(tableName);

        //TODO 添加创建者信息
        this.createDate = new Date();
    }

    @DbUpdateBefore
    public void updateBefore() {
        //TODO 添加更新者信息

        this.updateDate = new Date();
    }

    public BaseEntity() {
        super();
    }

    public BaseEntity(String id, String createUserId, String createUserName, Date createDate, String updateUserId, String updateUserName, Date updateDate) {
        this.id = id;
        this.createUserId = createUserId;
        this.createUserName = createUserName;
        this.createDate = createDate;
        this.updateUserId = updateUserId;
        this.updateUserName = updateUserName;
        this.updateDate = updateDate;
    }

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Version
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "create_user_id", length = 32)
    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    @Column(name = "create_user_name", length = 32)
    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    @Column(name = "create_date")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Column(name = "update_user_id", length = 32)
    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    @Column(name = "update_user_name", length = 32)
    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    @Column(name = "update_date")
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void fainWhenConcurrencyViolation(Integer version) {
        if (!version.equals(this.getVersion())) {
            throw new ConcurrencyException("记录在提交之前已发生改变[id=" + this.getId() + "],请重新提交.");
        }
    }
}
