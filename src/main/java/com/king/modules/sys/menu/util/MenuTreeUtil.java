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
public class MenuTreeUtil {

    private List<Menu> menuList = null;

    public MenuTreeUtil(List<Menu> menuList) {
        this.menuList = menuList;
        duplicateRemoval();
    }

    /**
     * 去重
     */
    private void duplicateRemoval() {
        Map<String, String> key = new HashMap<>();
        List<Menu> newMenu = new ArrayList<>();
        for (Menu menu : menuList) {
            if (!key.containsKey(menu.getId())) {
                newMenu.add(menu);
                key.put(menu.getId(), "");
            }
        }
        this.menuList = newMenu;
    }

    /**
     * 获取菜单树数据
     *
     * @param id 顶级id
     * @return
     */
    public List<TreeNode> getMenuTree(String id) {
        List<Menu> modules = getChildNode(id);

        List<TreeNode> treeNodes = null;

        if (null != modules && modules.size() > 0) {
            treeNodes = new ArrayList<>();
            for (Menu module : modules) {
                TreeNode treeNode = getTreeNode(module); //分别得到每个节点下的子节点集合
                treeNodes.add(treeNode);
            }
        }


        return treeNodes;
    }

    /**
     * 获取tree节点
     *
     * @param menu
     * @return
     */
    private TreeNode getTreeNode(Menu menu) {
        TreeNode treeNode = new TreeNode();
        treeNode.setChecked(false);
        treeNode.setIconCls(menu.getIcon());
        treeNode.setText(menu.getName());
        treeNode.setState("close");
        treeNode.setId(menu.getId());
        Map<String, Object> map = new HashMap<>(); //附加数据,前台获取数据可(json对象.attributes.site)
        map.put("id", menu.getId());
        map.put("url", menu.getHref());
        treeNode.setAttributes(map);

        List<Menu> modules = getChildNode(menu.getId());   //得到子节点集合
        List<TreeNode> treeNodes = new ArrayList<>();
        for (Menu m : modules) {
            TreeNode tn = getTreeNode(m);  //循环子节点,得到子节点下的孙节点集合,调用本身,可一直向下递归,直到modules为空
            treeNodes.add(tn);
        }
        treeNode.setChildren(treeNodes);   //添加封装好数据的子节点集合
        return treeNode;
    }

    /**
     * 子节点的父id
     *
     * @param id 顶级id
     * @return
     */
    public List<Menu> getChildNode(String id) {
        List<Menu> resultData = new ArrayList<>();
        for (Menu menu : this.menuList) {
            if ((menu.getParent() != null && menu.getParent().getId().equals(id))) {
                resultData.add(menu);
            }
        }
        return resultData;
    }


//    /**
//     * 根据parentId获取数据
//     *
//     * @param parentId
//     * @return
//     */
//    public List<TreeNode> getByParentId(String parentId) {
//        List<Menu> menuList = menuService.findByParentId(parentId);
//
//        List<TreeNode> treeNodes = null;
//
//        if (null != menuList && menuList.size() > 0) {
//            treeNodes = new ArrayList<>();
//            for (Menu menu : menuList) {
//                //得到每个节点下的子节点集合
//                TreeNode treeNode = getTreeNodeByMenu(menu);
//                treeNodes.add(treeNode);
//            }
//        }
//
//        return treeNodes;
//    }
//
//    /**
//     * 获取tree node
//     *
//     * @param menu
//     * @return
//     */
//    private TreeNode getTreeNodeByMenu(Menu menu) {
//        TreeNode treeNode = new TreeNode();
//        treeNode.setText(menu.getName());
//        treeNode.setId(menu.getId());
//        treeNode.setState("close");
//
//        //属性
//        Map<String, Object> attributes = new HashMap<>();
//        attributes.put("url", menu.getHref());
//        attributes.put("parentId", null != menu.getParent() ? menu.getParent().getId() : "");
//        treeNode.setAttributes(attributes);
//
//        //递归添加子节点,直到menuList为空
//        List<Menu> menuList = menuService.findByParentId(menu.getId());
//        List<TreeNode> treeNodes = new ArrayList<>();
//        for (Menu itemMenu : menuList) {
//            TreeNode itemTreeNode = getTreeNodeByMenu(itemMenu);
//            treeNodes.add(itemTreeNode);
//        }
//        //添加封装好数据的子节点集合
//        treeNode.setChildren(treeNodes);
//        return treeNode;
//    }
}
