package com.king.modules.sys.role.service.impl;

import com.king.common.exception.NoFoundException;
import com.king.common.web.Pagination;
import com.king.modules.sys.role.dao.IRoleDao;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.role.service.IRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by YJH
 * on 2017/8/1 16:39.
 * 注释:
 */
@Service("roleService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao<Role, String> roleDao;

    @Override
    public Pagination<Role> pagination(Pagination<Role> pagination, Role role) {
        DetachedCriteria dc = roleDao.createDetachedCriteria();
        return roleDao.pagination(pagination, dc);
    }

    @Override
    @Transactional()
    public void save(Role role) {

        roleDao.save(role);
    }

    @Override
    public Role getById(String id) {
        Role role = roleDao.getById(id);
        if (null == role) {
            throw new NoFoundException("id为[" + id + "]的数据未找到");
        }
        return role;
    }

    @Override
    @Transactional()
    public void edit(Role role) {
        Role editRole = getById(role.getId());
        editRole.fainWhenConcurrencyViolation(role.getVersion());

        roleDao.update(editRole);
    }

    @Override
    @Transactional
    public void del(Role role) {
        Role delRole = getById(role.getId());
        delRole.fainWhenConcurrencyViolation(role.getVersion());

        roleDao.delete(delRole);
    }
}
