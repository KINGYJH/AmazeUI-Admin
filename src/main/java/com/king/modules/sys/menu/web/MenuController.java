package com.king.modules.sys.menu.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.DataErrorException;
import com.king.common.exception.NoFoundException;
import com.king.common.shiro.ShiroFilterChainManager;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.TreeNode;
import com.king.modules.sys.log.util.LogUtil;
import com.king.modules.sys.menu.entity.Menu;
import com.king.modules.sys.menu.service.IMenuService;
import com.king.modules.sys.role.util.RoleUtil;
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
        LogUtil.write(parentId, OperationType.SEARCH, "菜单:查询数据");
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

            RoleUtil.editMenuAfter();

            LogUtil.write(menu, OperationType.SAVE, "菜单:添加");
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后在试!");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(menu, OperationType.SAVE, e.getMessage(), ResultType.FAIL, "菜单:添加");
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

            RoleUtil.editMenuAfter();

            LogUtil.write(menu, OperationType.UPDATE, "菜单:修改");
        } catch (NoFoundException | DataErrorException | ConcurrencyException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后在试!");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(menu, OperationType.UPDATE, e.getMessage(), ResultType.FAIL, "菜单:修改");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/user_tree_data", method = RequestMethod.POST)
    @ResponseBody
    public List<TreeNode> userTreeData() {
        LogUtil.write("", OperationType.SEARCH, "菜单:获取用户菜单");
        return menuService.getUserTree();
    }

    @RequestMapping(value = "/get_all_tree_data", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage getAllTreeData() {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            List<TreeNode> data = menuService.getAllTree();
            jsonMessage.setMsg("获取数据成功");
            jsonMessage.setData(data);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write("", OperationType.SEARCH, "菜单:获取所有菜单");
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("获取数据失败");

            LogUtil.write("", OperationType.SEARCH, e.getMessage(), ResultType.FAIL, "菜单:获取所有菜单");
        }
        return jsonMessage;
    }
}
