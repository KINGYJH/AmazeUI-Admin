package com.king.modules.sys.menu.web;

import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
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

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/menu/MenuManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public List<Menu> dataList(String parentId) {
        return menuService.findByParentId(parentId);
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/menu/MenuSave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(Menu menu) {
        JSONMessage msg = new JSONMessage();
        try {
            menuService.save(menu);
            msg.setMsg("添加成功!");
            msg.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            msg.setMsg("系统错误,请稍后在试!");
            msg.setStatus(JSONMessage.Status.FAIL);
        }
        return msg;
    }

    @RequestMapping(value = "/user_tree_data", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> userTreeData(String parentId) {
        return menuService.getTreeNode(parentId);
    }

    @RequestMapping(value = "/get_all_treeData", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage getAllTreeData() {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            List<TreeNode> data = menuService.getTreeNode("");
            jsonMessage.setMsg("获取数据成功");
            jsonMessage.setData(data);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("获取数据失败");
        }
        return jsonMessage;
    }
}
