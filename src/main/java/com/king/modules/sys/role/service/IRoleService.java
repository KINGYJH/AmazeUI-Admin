package com.king.modules.sys.role.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.role.entity.Role;

import java.util.List;

/**
 * Created by YJH
 * on 2017/8/1 16:38.
 * 注释:
 */
public interface IRoleService {
    Pagination<Role> pagination(Pagination<Role> pagination, Role role);

    void save(Role role);

    Role getById(String id);

    void edit(Role role);

    void del(Role role);

    boolean checkIsExist(String name);

    List<Role> findByIds(Object[] roleIds);
}
