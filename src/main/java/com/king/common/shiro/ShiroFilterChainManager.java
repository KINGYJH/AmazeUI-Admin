package com.king.common.shiro;

import com.king.common.utils.StringUtils;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.user.service.IAuthService;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:46
 */
public class ShiroFilterChainManager {

    @Autowired
    private CustomDefaultFilterChainManager filterChainManager;

    @Autowired
    private IAuthService authService;

    private Map<String, NamedFilterList> defaultFilterChains;

    /**
     * 项目启动 初始化过滤链
     */
    @PostConstruct
    public void init() {
        defaultFilterChains = new HashMap<>(filterChainManager.getFilterChains());
        List<Menu> menuList = authService.findAllPermission();
//        initFilterChains(menuList);
    }

    public void initFilterChains(List<Menu> menuList) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if (defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }

        //2、循环URL Filter 注册filter chain
        for (Menu menu : menuList) {
            //注册perms filter
            if (StringUtils.isNotBlank(menu.getHref())) {
                filterChainManager.addToChain("/admin" + menu.getHref(), "perms", menu.getPermission());
            }
        }
    }

    /**
     * 更新过滤链
     */
    public void update() {
        synchronized (filterChainManager) {
            this.init();
            filterChainManager.init();
        }
    }

}
