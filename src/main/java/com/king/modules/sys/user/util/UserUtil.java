package com.king.modules.sys.user.util;

import com.king.common.shiro.ExtendSimplePrincipalCollection;
import com.king.common.utils.CacheUtils;
import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH
 * on 2017/8/4 14:48.
 * 注释:用户工具类
 */
public class UserUtil {

    public static final String CACHE_USER = "cacheUser";

    private static IUserService userService = SpringContextHolder.getBean(IUserService.class);

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static User getUser() {
        Subject subject = SecurityUtils.getSubject();
        String acctName = (String) ((ExtendSimplePrincipalCollection) subject.getPrincipal()).getPrimaryPrincipal();
        Map<String, User> userMap = (Map<String, User>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_USER);
        if (null == userMap) {
            userMap = new HashMap<>();
        }
        if (userMap.containsKey(acctName)) {
            return userMap.get(acctName);
        } else {
            User user = userService.getByAcctName(acctName);
            userMap.put(acctName, user);
            CacheUtils.put(CacheUtils.SYS_CACHE, CACHE_USER, userMap);
            return user;
        }
    }

    /**
     * 获取当前登录用户的菜单
     *
     * @return
     */
    public static List<Menu> getUserMenu() {
        List<Menu> menuList = new ArrayList<>();
        User user = getUser();
        for (Role role : user.getRole()) {
            menuList.addAll(role.getPermission());
        }
        return menuList;
    }

    /**
     * 更新缓存中的用户信息
     */
    @SuppressWarnings("unchecked")
    public static void updateAllUser() {
        Map<String, User> userMap = (Map<String, User>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_USER);
        if (null == userMap) {
            userMap = new HashMap<>();
        }
        for (String key : userMap.keySet()) {
            User user = userService.getByAcctName(key);
            userMap.put(key, user);
        }
    }
}
