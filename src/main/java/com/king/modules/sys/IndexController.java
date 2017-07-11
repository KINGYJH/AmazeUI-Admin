package com.king.modules.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by YJH
 * on 2017/6/30 11:34.
 * 注释:
 */
@Controller
public class IndexController {

    @RequestMapping(value = "${adminPath}")
    public ModelAndView index() {
        return new ModelAndView("/index");
    }

}
