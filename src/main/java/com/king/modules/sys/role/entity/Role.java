package com.king.modules.sys.role.entity;

import com.king.common.persistence.BaseEntity;
import com.king.modules.sys.menu.entity.Menu;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * Created by YJH
 * on 2017/8/1 16:36.
 * 注释:
 */
@Entity
@Table(name = "sys_role")
public class Role extends BaseEntity<Role> {

    private String name;            //名称
    private String describes;       //描述
    private List<Menu> permission = new ArrayList<>();  //权限集合
    private String permissionIds;   //权限所有id
    private Integer sort;           //排序

    @Column(name = "name", length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "describes")
    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    @ManyToMany//(fetch = EAGER)//多对多关系
//    @Cascade(value = {CascadeType.SAVE_UPDATE})        //级联关系
    @JoinTable(name = "sys_role_menu",                     //中间表的名称
            joinColumns = {@JoinColumn(name = "role_id")},   //本表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "menu_id")}) //另一张表与中间表的外键的对应关系
    public List<Menu> getPermission() {
        return permission;
    }

    public void setPermission(List<Menu> permission) {
        this.permission = permission;
    }

    @Column(name = "permission_ids",length = 2000)
    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
