package com.king.modules.sys.menu.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.DataErrorException;
import com.king.common.exception.NoFoundException;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.TreeNode;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        JSONMessage jsonMessage = new JSONMessage();
        try {
            menuService.save(menu);
            jsonMessage.setMsg("添加成功!");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后在试!");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/edit_page")
    public ModelAndView editPage(String id, Model model) {
        try {
            model.addAttribute("data", menuService.getById(id));
        } catch (NoFoundException e) {
            model.addAttribute("alertMessage", e.getMessage());
        }
        return new ModelAndView("/pages/sys/menu/MenuEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage edit(Menu menu) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            menuService.edit(menu);
            jsonMessage.setMsg("修改成功!");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (NoFoundException | DataErrorException | ConcurrencyException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后在试!");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        }
        return jsonMessage;
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
