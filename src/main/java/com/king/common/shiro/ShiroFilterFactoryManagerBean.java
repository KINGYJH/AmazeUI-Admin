package com.king.common.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:54
 */
public class ShiroFilterFactoryManagerBean extends ShiroFilterFactoryBean {

    private PathMatchingFilterChainResolver filterChainResolver;

    @Autowired
    private CustomDefaultFilterChainManager filterChainManager;

    public void setFilterChainResolver(PathMatchingFilterChainResolver filterChainResolver) {
        this.filterChainResolver = filterChainResolver;
    }

    @Override
    protected AbstractShiroFilter createInstance() throws Exception {
        org.apache.shiro.mgt.SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            String msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        }

        if (!(securityManager instanceof WebSecurityManager)) {
            String msg = "The security manager does not implement the WebSecurityManager interface.";
            throw new BeanInitializationException(msg);
        }

        FilterChainManager manager = filterChainManager;


        PathMatchingFilterChainResolver chainResolver = filterChainResolver;
        chainResolver.setFilterChainManager(manager);


        return new SpringShiroFilter((WebSecurityManager) securityManager, chainResolver);
    }


    private static final class SpringShiroFilter extends AbstractShiroFilter {

        protected SpringShiroFilter(WebSecurityManager webSecurityManager, FilterChainResolver resolver) {
            super();
            if (webSecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(webSecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }
    }

}
