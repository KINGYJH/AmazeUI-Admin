package com.king.modules.sys.menu.entity;

import com.king.common.persistence.BaseEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;


/**
 * Created by YJH
 * on 2017/7/10 14:27.
 * 注释: 菜单
 */
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Menu extends BaseEntity<Menu> {

    private Menu parent;        //父级菜单
    private String name;        //菜单名称
    private String href;        //菜单连接
    private String permission;  //菜单权限标识
    private String icon;        //菜单图标
    private Integer sort;       //菜单排序
    private String isShow;      //菜单是否显示

    private String state = "open"; // 用于datagrid判断

    public Menu() {
        super();
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    @NotFound(action = NotFoundAction.IGNORE)
    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    @Column(name = "name", length = 125)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "href", length = 125)
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Column(name = "sort")
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Column(name = "is_show", length = 2)
    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    @Transient
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
