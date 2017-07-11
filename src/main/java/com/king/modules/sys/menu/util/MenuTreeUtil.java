package com.king.modules.sys.menu.util;

import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by YJH
 * on 2017/7/10 16:36.
 * 注释:
 */
@Component
public class MenuTreeUtil {

    @Autowired
    private IMenuService menuService;


    public MenuTreeUtil() {
    }

    /**
     * 根据父级id获取数据
     *
     * @param parentId
     * @return
     */
    public List<TreeNode> getByParentId(String parentId) {
        //TODO 用缓存获取
        List<Menu> menuList = menuService.findByParentId(parentId);

        List<TreeNode> treeNodes = null;

        if (null != menuList && menuList.size() > 0) {
            treeNodes = new ArrayList<>();
            for (Menu menu : menuList) {
                //得到每个节点下的子节点集合
                TreeNode treeNode = getTreeNodeByMenu(menu);
                treeNodes.add(treeNode);
            }
        }

        return treeNodes;
    }

    /**
     * 获取tree node
     *
     * @param menu
     * @return
     */
    private TreeNode getTreeNodeByMenu(Menu menu) {
        TreeNode treeNode = new TreeNode();
        treeNode.setText(menu.getName());
        treeNode.setId(menu.getId());
        treeNode.setState("close");

        //属性
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("url", menu.getHref());
        attributes.put("parentId", menu.getParentId());
        treeNode.setAttributes(attributes);

        //递归添加子节点,直到menuList为空
        List<Menu> menuList = menuService.findByParentId(menu.getId());
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu itemMenu : menuList) {
            TreeNode itemTreeNode = getTreeNodeByMenu(itemMenu);
            treeNodes.add(itemTreeNode);
        }
        //添加封装好数据的子节点集合
        treeNode.setChildren(treeNodes);
        return treeNode;
    }

}
