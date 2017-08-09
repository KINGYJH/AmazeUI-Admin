package com.king.modules.sys.role.util;

import com.king.common.shiro.ShiroFilterChainManager;
import com.king.common.shiro.UserRealm;
import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.user.util.UserUtil;

/**
 * Created by YJH
 * on 2017/8/9 10:43.
 * 注释: 角色权限工具类
 */
public class RoleUtil {

    private static ShiroFilterChainManager shiroFilterChainManager = SpringContextHolder.getBean(ShiroFilterChainManager.class);

    private static UserRealm userRealm = SpringContextHolder.getBean(UserRealm.class);

    /**
     * 修改角色之后操作（更新shiro信息）
     */
    public static void editRoleAfter() {
        UserUtil.updateAllUser();
        userRealm.clearAllCachedAuthorizationInfo2();
    }

    /**
     * 修改菜单之后操作（更新shiro过滤链信息）
     */
    public static void editMenuAfter() {
        UserUtil.updateAllUser();
        shiroFilterChainManager.update();
    }

}
