package com.king.modules.sys.role.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.role.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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
        } catch (NoFoundException | ExistException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
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
            jsonMessage.setMsg("修改成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (ConcurrencyException | NoFoundException | ExistException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
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
        } catch (ConcurrencyException | NoFoundException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        }
        return jsonMessage;
    }
}
