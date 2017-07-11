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
    List<Menu> findAll();

    List<Menu> findByParentId(String parentId);

    List<TreeNode> getTreeNode(String parentId);

    boolean isHasChild(String menuId);
}
