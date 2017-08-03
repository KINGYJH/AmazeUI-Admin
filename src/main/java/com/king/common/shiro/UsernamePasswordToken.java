package com.king.common.shiro;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:31
 * 用户和密码（包含验证码）令牌类
 */
public class UsernamePasswordToken extends org.apache.shiro.authc.UsernamePasswordToken {

    public UsernamePasswordToken() {
        super();
    }

    public UsernamePasswordToken(String username, String password, boolean rememberMe, String host) {
        super(username, password, rememberMe, host);
    }

}
