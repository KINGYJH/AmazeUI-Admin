package com.king.modules.sys.menu.web;

import com.king.common.web.BaseController;
import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 15:41.
 * 注释:
 */
@Controller
@RequestMapping("${adminPath}/sys/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    @RequestMapping(value = "/page")
    public ModelAndView beginPage() {
        return new ModelAndView("/pages/sys/menu/MenuManager");
    }

    @RequestMapping(value = "/user_tree_data", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> userTreeData(String parentId) {
        return menuService.getTreeNode(parentId);
    }

}
