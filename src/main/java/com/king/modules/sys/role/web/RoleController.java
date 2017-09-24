package com.king.modules.sys.role.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.shiro.ShiroFilterChainManager;
import com.king.common.shiro.UserRealm;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.log.util.LogUtil;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.role.service.IRoleService;
import com.king.modules.sys.role.util.RoleUtil;
import com.king.modules.sys.user.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by YJH
 * on 2017/8/1 16:40.
 * 注释:
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/role/RoleManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<Role> dataList(Role role, HttpServletRequest request) {
        LogUtil.write(role, OperationType.SEARCH, "角色:查询数据");
        return roleService.pagination(new Pagination<>(request), role);
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/role/RoleSave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(Role role) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            roleService.save(role);
            jsonMessage.setMsg("添加成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            RoleUtil.editRoleAfter();

            LogUtil.write(role, OperationType.SAVE, "角色:添加");
        } catch (NoFoundException | ExistException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(role, OperationType.SAVE, e.getMessage(), ResultType.FAIL, "角色:添加");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/edit_page")
    public ModelAndView editPage(String id, Model model) {
        try {
            model.addAttribute("data", roleService.getById(id));
        } catch (NoFoundException e) {
            model.addAttribute("alertMessage", e.getMessage());
        }
        return new ModelAndView("/pages/sys/role/RoleEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage edit(Role role) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            roleService.edit(role);
            UserUtil.updateAllUser();
            jsonMessage.setMsg("修改成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            RoleUtil.editRoleAfter();

            LogUtil.write(role, OperationType.UPDATE, "角色:修改");
        } catch (ConcurrencyException | NoFoundException | ExistException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(role, OperationType.UPDATE, e.getMessage(), ResultType.FAIL, "角色:修改");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage del(Role role) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            roleService.del(role);
            jsonMessage.setMsg("删除成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            RoleUtil.editRoleAfter();

            LogUtil.write(role, OperationType.DEL, "角色:删除");
        } catch (ConcurrencyException | NoFoundException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write(role, OperationType.DEL, e.getMessage(), ResultType.FAIL, "角色:删除");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/get_all_data", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage getAllData() {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            List<Role> data = roleService.getAllData();
            jsonMessage.setData(data);
            jsonMessage.setMsg("获取数据成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write("", OperationType.SEARCH, "角色:获取所有数据");
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write("", OperationType.SEARCH, e.getMessage(), ResultType.FAIL, "角色:获取所有数据");
        }
        return jsonMessage;
    }
}
