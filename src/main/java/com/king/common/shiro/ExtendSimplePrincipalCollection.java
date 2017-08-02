package com.king.common.shiro;

import org.apache.shiro.subject.SimplePrincipalCollection;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:19
 * 扩展SimplePrincipalCollection
 */
public class ExtendSimplePrincipalCollection extends SimplePrincipalCollection {

    public ExtendSimplePrincipalCollection() {
    }

    public ExtendSimplePrincipalCollection(Object principal, String realmName) {
        super(principal, realmName);
    }

}
