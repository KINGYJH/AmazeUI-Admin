package com.king.modules.sys.role.service.impl;

import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.web.Pagination;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.role.dao.IRoleDao;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.role.service.IRoleService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    private IMenuService menuService;

    @Override
    public Pagination<Role> pagination(Pagination<Role> pagination, Role role) {
        DetachedCriteria dc = roleDao.createDetachedCriteria();
        return roleDao.pagination(pagination, dc);
    }

    @Override
    @Transactional()
    public void save(Role role) {
        if (checkIsExist(role.getName())) {
            throw new ExistException("name[" + role.getName() + "]的数据已存在");
        }

        String[] menu_ids = role.getPermissionIds().split(",");
        List<Menu> menuList = menuService.findByIds(menu_ids);
        role.setPermission(menuList);
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

        if (!editRole.getName().equals(role.getName())) {
            if (checkIsExist(role.getName())) {
                throw new ExistException("name[" + role.getName() + "]的数据已存在");
            }
        }

        String[] menu_ids = role.getPermissionIds().split(",");
        List<Menu> menuList = menuService.findByIds(menu_ids);
        editRole.setPermission(menuList);

        editRole.setName(role.getName());
        editRole.setPermissionIds(role.getPermissionIds());
        editRole.setDescribes(role.getDescribes());
        editRole.setSort(role.getSort());
        roleDao.update(editRole);
    }

    @Override
    @Transactional
    public void del(Role role) {
        Role delRole = getById(role.getId());
        delRole.fainWhenConcurrencyViolation(role.getVersion());

        roleDao.delete(delRole);
    }

    /**
     * 判断数据是否存在
     *
     * @param name 名称
     * @return true存在false反之
     */
    @Override
    public boolean checkIsExist(String name) {
        DetachedCriteria dc = roleDao.createDetachedCriteria();
        dc.add(Restrictions.eq("name", name));
        return !roleDao.find(dc).isEmpty();
    }
}
