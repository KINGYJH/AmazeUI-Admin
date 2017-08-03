package com.king.modules.sys.user.service.impl;

import com.king.common.shiro.LoginEntity;
import com.king.common.shiro.UsernamePasswordToken;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IAuthService;
import com.king.modules.sys.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:13
 */
@Service("authService")
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Override
    public User searchByAcctName(String acctName) {
        return userService.getByAcctName(acctName);
    }

    @Override
    public List<Menu> findAllPermission() {
        return menuService.findAll();
    }

    @Override
    public User login(LoginEntity login) {
        User user = searchByAcctName(login.getAcctName());
        if (null == user) {
            throw new UnknownAccountException("用户不存在");
        }

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(login.getAcctName(), login.getPwd(), false, login.getLoginIP());
        subject.login(token);

        user.setLastLoginDate(new Date());
        user.setLastLoginIP(login.getLoginIP());
        user.setLastLoginPlatform(login.getLoginPlatform());
        return user;
    }
}
