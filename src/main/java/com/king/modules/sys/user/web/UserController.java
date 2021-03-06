package com.king.modules.sys.user.web;

import com.king.common.exception.ExistException;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.log.util.LogUtil;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:25
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/user/UserManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<User> dataList(User user, HttpServletRequest request) {
        LogUtil.write(user, OperationType.SEARCH, "用户:查询数据");
        return userService.pagination(new Pagination<>(request), user);
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/user/UserSave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(User user) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            userService.save(user);
            jsonMessage.setMsg("添加成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write(user, OperationType.SAVE, "用户:添加");
        } catch (ExistException e) {
            jsonMessage.setMsg(e.getMessage());
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        } catch (Exception e) {
            jsonMessage.setMsg("系统错误,请稍后再试");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(user, OperationType.SAVE, e.getMessage(), ResultType.FAIL, "用户:添加");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage del() {
        JSONMessage jsonMessage = new JSONMessage();

        return jsonMessage;
    }

}
