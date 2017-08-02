package com.king.modules.sys.user.service.impl;

import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IAuthService;
import com.king.modules.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
