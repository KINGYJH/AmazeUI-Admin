package com.king.modules.sys.home.web;

import com.king.common.exception.NoFoundException;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.modules.sys.home.entity.HomeView;
import com.king.modules.sys.home.entity.HomeViewList;
import com.king.modules.sys.home.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by YJH
 * on 2017/7/26 14:42.
 * 注释:
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/home_view")
public class HomeController extends BaseController {

    @Autowired
    private IHomeService homeService;

    @RequestMapping(value = "/index")
    public ModelAndView home(Model model) {
        HomeViewList data = homeService.getAllView();
        model.addAttribute("data", data.getShowHomeViews());
        return new ModelAndView("/home");
    }

    @RequestMapping(value = "/manager")
    public ModelAndView manager(Model model) {
        HomeViewList data = homeService.getAllView();
        model.addAttribute("data", data.getHomeViews());
        return new ModelAndView("/pages/sys/home/HomeManager_1");
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/home/HomeSave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(HomeView homeView) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            homeService.save(homeView);
            jsonMessage.setMsg("添加成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("添加失败");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/edit_page")
    public ModelAndView editPage(String id, Model model) {
        try {
            HomeView homeView = homeService.getById(id);
            model.addAttribute("homeView", homeView);
            return new ModelAndView("/pages/sys/home/HomeEdit");
        } catch (NoFoundException e) {
            model.addAttribute("alertMessage", e.getMessage());
        }
        return new ModelAndView("/pages/sys/home/HomeEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage edit(HomeView homeView) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            homeService.edit(homeView);
            jsonMessage.setMsg("修改成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("修改失败");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage del(String id) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            homeService.del(id);
            jsonMessage.setMsg("删除成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setMsg("删除失败");
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
        }
        return jsonMessage;
    }
}
