package com.king.modules.sys.menu.service.impl;

import com.king.common.utils.StringUtils;
import com.king.common.web.Pagination;
import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.dao.IMenuDao;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.menu.util.MenuTreeUtil;
import org.hibernate.criterion.DetachedCriteria;
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
    @Transactional(readOnly = false)
    public Menu save(Menu menu) {
        menuDao.saveOrUpdate(menu);
        return menu;
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
        return menuDao.find(dc);
    }
}
