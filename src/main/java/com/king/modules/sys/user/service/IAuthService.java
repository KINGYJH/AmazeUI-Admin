package com.king.modules.sys.user.service;

import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.user.entity.User;

import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:13
 */
public interface IAuthService {
    User searchByAcctName(String acctName);

    List<Menu> findAllPermission();
}
