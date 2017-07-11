package com.king.common.persistence;

import com.king.common.annotation.DbInsertBefore;
import com.king.common.annotation.DbUpdateBefore;
import com.king.common.utils.GenericsUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by YJH
 * on 2017/7/10 14:27.
 * 注释: 实体通用字段类
 */
public class BaseEntity<T> implements Serializable {

    private String id;              //id

    private String createUserId;    //创建者id

    private String createUserName;  //创建者名称

    private Date createDate;        //创建时间

    private String updateUserId;    //更新者id

    private String updateUserName;  //更新者名称

    private Date updateDate;        //更新时间

    @DbInsertBefore
    public void insertBefore() {
        Class clazz = (Class) GenericsUtils.getSuperClassGenricType(this.getClass());
        String simpleName = clazz.getSimpleName();
        this.id = UUID.randomUUID().toString().replaceAll("-", "");

        //TODO 添加创建者信息
    }

    @DbUpdateBefore
    public void updateBefore() {
        //TODO 添加更新者信息
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
