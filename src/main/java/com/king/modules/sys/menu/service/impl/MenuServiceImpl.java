package com.king.modules.sys.menu.service.impl;

import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.dao.IMenuDao;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.menu.util.MenuTreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 16:27.
 * 注释:
 */
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private IMenuDao<Menu, String> menuDao;

    @Autowired
    private MenuTreeUtil menuTreeUtil;

    @Override
    public List<Menu> findAll() {
        return menuDao.findAll();
    }

    @Override
    public List<Menu> findByParentId(String parentId) {
        return menuDao.findByParentId(parentId);
    }

    @Override
    public List<TreeNode> getTreeNode(String parentId) {
        return menuTreeUtil.getByParentId(parentId);
    }
}
