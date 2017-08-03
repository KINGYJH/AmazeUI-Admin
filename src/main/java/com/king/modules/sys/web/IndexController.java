package com.king.modules.sys.web;

import com.king.common.config.Global;
import com.king.common.config.PropertiesLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by YJH
 * on 2017/6/30 11:34.
 * 注释:
 */
@Controller
public class IndexController {

    @RequestMapping(value = "${adminPath}")
    public ModelAndView index(HttpSession session) {
        if (null != session.getAttribute(Global.SESSION_USER)) {
            return new ModelAndView("/pages/index");
        } else {
            return new ModelAndView("redirect:/login");
        }
    }

    @RequestMapping(value = "/404")
    public ModelAndView error_404() {
        return new ModelAndView("/pages/error/404");
    }

    @RequestMapping(value = "/500")
    public ModelAndView error_500() {
        return new ModelAndView("/pages/error/500");
    }

    @RequestMapping("/denied")
    public ModelAndView unauthorized() throws Exception {
        return new ModelAndView("/pages/error/denied");
    }
}
