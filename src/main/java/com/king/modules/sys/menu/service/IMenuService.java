package com.king.modules.sys.menu.service;

import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.entity.Menu;

import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 16:27.
 * 注释:
 */
public interface IMenuService {
    Menu getById(String id);

    void save(Menu menu);

    List<Menu> findAll();

    List<TreeNode> getTreeNode(String parentId);

    List<Menu> findByParentId(String parentId);

    boolean isHasChild(String menuId);

    void edit(Menu menu);
}
