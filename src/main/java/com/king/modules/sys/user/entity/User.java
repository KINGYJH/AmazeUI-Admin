package com.king.modules.sys.user.entity;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:25
 */
public class User {

    private String id;
    private String username;
    private String pwd;

    public User() {
    }

    public User(String id, String username, String pwd) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
