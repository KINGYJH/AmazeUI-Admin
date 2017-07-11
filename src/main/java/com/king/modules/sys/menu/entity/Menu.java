package com.king.modules.sys.menu.entity;

import com.king.common.persistence.BaseEntity;

import java.util.Date;

/**
 * Created by YJH
 * on 2017/7/10 14:27.
 * 注释: 菜单
 */
public class Menu extends BaseEntity<Menu> {

    private String parentId;    //父级菜单id
    private String parentIds;   //所有父级菜单id
    private String name;        //菜单名称
    private String href;        //菜单连接
    private String permission;  //菜单权限标识
    private String icon;        //菜单图标
    private Integer sort;       //菜单排序
    private String isShow;      //菜单是否显示

    public Menu() {
        super();
    }

    public Menu(String parentId, String parentIds, String name, String href, String permission, String icon, Integer sort, String isShow) {
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.name = name;
        this.href = href;
        this.permission = permission;
        this.icon = icon;
        this.sort = sort;
        this.isShow = isShow;
    }

    public Menu(String id, String createUserId, String createUserName, Date createDate, String updateUserId, String updateUserName, Date updateDate, String parentId, String parentIds, String name, String href, String permission, String icon, Integer sort, String isShow) {
        super(id, createUserId, createUserName, createDate, updateUserId, updateUserName, updateDate);
        this.parentId = parentId;
        this.parentIds = parentIds;
        this.name = name;
        this.href = href;
        this.permission = permission;
        this.icon = icon;
        this.sort = sort;
        this.isShow = isShow;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }
}
