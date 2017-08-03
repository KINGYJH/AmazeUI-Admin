package com.king.common.shiro;

import java.io.Serializable;

/**
 * Created by YJH
 * on 2017/8/3 10:37.
 * 注释:
 */
public class LoginEntity implements Serializable {

    private String acctName;  //用户名
    private String pwd;       //密码
    private String captcha;   //验证码
    private boolean isRememberMe = false;//记住我
    private String loginIP;         //登录IP
    private String loginPlatform;   //登录平台

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public boolean isRememberMe() {
        return isRememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        isRememberMe = rememberMe;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginPlatform() {
        return loginPlatform;
    }

    public void setLoginPlatform(String loginPlatform) {
        this.loginPlatform = loginPlatform;
    }
}
