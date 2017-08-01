package com.king.modules.sys.menu.service.impl;

import com.king.common.exception.DataErrorException;
import com.king.common.exception.NoFoundException;
import com.king.common.utils.StringUtils;
import com.king.common.web.Pagination;
import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.dao.IMenuDao;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.menu.util.MenuTreeUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 16:27.
 * 注释:
 */
@Service("menuService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao<Menu, String> menuDao;

    @Autowired
    private MenuTreeUtil menuTreeUtil;

    @Override
    public Menu getById(String id) {
        Menu menu = menuDao.getById(id);
        if (null == menu) {
            throw new NoFoundException("id为[" + id + "]的数据未找到");
        }
        return menu;
    }

    @Override
    @Transactional()
    public void save(Menu menu) {
        Menu parent = this.getById(menu.getParent().getId());
        menu.setParent(parent);
        menuDao.save(menu);
    }

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<TreeNode> getTreeNode(String parentId) {
        return menuTreeUtil.getByParentId(parentId);
    }

    @Override
    public List<Menu> findByParentId(String parentId) {
        DetachedCriteria dc = menuDao.createDetachedCriteria();
        if (StringUtils.isNotBlank(parentId)) {
            dc.add(Restrictions.eq("parent.id", parentId));
        } else {
            dc.add(Restrictions.isNull("parent"));
        }
        dc.addOrder(Order.asc("sort"));
        List<Menu> listData = menuDao.find(dc);
        for (Menu menu : listData) {
            if (isHasChild(menu.getId())) {
                menu.setState("closed");
            } else {
                menu.setState("open");
            }
        }
        return listData;
    }

    /**
     * 判断是否包含子节点
     *
     * @param menuId 菜单id
     * @return 如果数量大于0 返会true
     */
    @Override
    public boolean isHasChild(String menuId) {
        DetachedCriteria dc = menuDao.createDetachedCriteria();
        dc.add(Restrictions.eq("parent.id", menuId));
        return menuDao.find(dc).size() > 0;
    }

    @Override
    @Transactional()
    public void edit(Menu menu) {
        Menu editMenu = getById(menu.getId());
        editMenu.fainWhenConcurrencyViolation(menu.getVersion());

        if (menu.getId().equals(menu.getParent().getId())) {
            throw new DataErrorException("自己不能为自己的父节点");
        }

        if (StringUtils.isNotBlank(menu.getParent().getId())) {
            Menu parent = getById(menu.getParent().getId());
            editMenu.setParent(parent);
        }

        editMenu.setName(menu.getName());
        editMenu.setPermission(menu.getPermission());
        editMenu.setSort(menu.getSort());
        editMenu.setIcon(menu.getIcon());
        editMenu.setIsShow(menu.getIsShow());
        editMenu.setHref(menu.getHref());

        menuDao.update(editMenu);
    }
}
