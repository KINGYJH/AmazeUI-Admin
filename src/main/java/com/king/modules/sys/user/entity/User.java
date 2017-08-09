package com.king.modules.sys.user.entity;

import com.king.common.persistence.BaseEntity;
import com.king.modules.sys.role.entity.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:25
 */
@Entity
@Table(name = "sys_user")
public class User extends BaseEntity<User> {

    private String acctName;                //用户名
    private String nickName;                //昵称
    private String pwd;                     //密码
    private Date lastLoginDate;             //登录时间
    private String lastLoginIP;             //登录IP
    private String lastLoginAddress;        //登录地址
    private String lastLoginPlatform;       //登录平台
    private String status;                  //用户状态
    private String headImg;                 //头像
    private List<Role> role = new ArrayList<>();//角色集合
    private String roleIds;                 //角色所有id

    @Column(name = "acct_name", length = 25)
    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    @Column(name = "nick_name", length = 25)
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Column(name = "pwd", length = 32)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Column(name = "last_login_date")
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    @Column(name = "last_login_ip", length = 50)
    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    @Column(name = "last_login_address")
    public String getLastLoginAddress() {
        return lastLoginAddress;
    }

    public void setLastLoginAddress(String lastLoginAddress) {
        this.lastLoginAddress = lastLoginAddress;
    }

    @Column(name = "last_login_platform")
    public String getLastLoginPlatform() {
        return lastLoginPlatform;
    }

    public void setLastLoginPlatform(String lastLoginPlatform) {
        this.lastLoginPlatform = lastLoginPlatform;
    }

    @Column(name = "status", length = 25)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    @ManyToMany//(fetch = EAGER)//多对多关系 不懒加载
//    @Cascade(value = {CascadeType.SAVE_UPDATE})        //级联关系
    @JoinTable(name = "sys_user_role",                     //中间表的名称
            joinColumns = {@JoinColumn(name = "user_id")},   //本表与中间表的外键对应关系
            inverseJoinColumns = {@JoinColumn(name = "role_id")}) //另一张表与中间表的外键的对应关系
    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    @Column(name = "role_ids")
    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
