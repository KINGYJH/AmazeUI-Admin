package com.king.common.shiro;

import com.king.common.utils.Encodes;
import com.king.common.utils.StringUtils;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IAuthService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by yjh
 * @DateTime 2017/8/2 21:11
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IAuthService authService;

    /**
     * 权限验证
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ExtendSimplePrincipalCollection principalCollection = (ExtendSimplePrincipalCollection) principals;
        String acctName = (String) principalCollection.getPrimaryPrincipal();
        User user = authService.searchByAcctName(acctName);

        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        authenticationInfo.setRoles(this.getAllRoles(user));
        authenticationInfo.setStringPermissions(this.getAllPermissions(user));
        return authenticationInfo;
    }

    /**
     * 获取用户所有权限
     *
     * @param user
     * @return
     */
    private Set<String> getAllPermissions(User user) {
        Set<String> permissions = new HashSet<>();
        List<Role> roleList = user.getRole();
        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                List<Menu> permissionList = role.getPermission();
                if (!permissionList.isEmpty()) {
                    for (Menu menu : permissionList) {
                        if (StringUtils.isNotBlank(menu.getHref())) {
                            permissions.add(menu.getPermission());
                        }
                    }
                }
            }
        }
        return permissions;
    }

    /**
     * 获取用户所有角色
     *
     * @param user
     * @return
     */
    private Set<String> getAllRoles(User user) {
        Set<String> roles = new HashSet<>();
        List<Role> roleList = user.getRole();
        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                roles.add(role.getName());
            }
        }
        return roles;
    }

    /**
     * 登陆验证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        User user = authService.searchByAcctName(token.getPrincipal().toString());

        //TODO 验证码

        if (null == user) {
            throw new UnknownAccountException(); //用户不存在
        } else {
            if (user.getStatus().equals("dic_00004")) {//TODO 从数据获取字典值
                throw new LockedAccountException(); //用户被禁用
            }
        }

        byte[] salt = Encodes.decodeHex(user.getPwd().substring(0, 16));
        return new SimpleAuthenticationInfo(new ExtendSimplePrincipalCollection(user, user.getAcctName()),
                user.getPwd().substring(16), ByteSource.Util.bytes(salt), getName());
    }
}
