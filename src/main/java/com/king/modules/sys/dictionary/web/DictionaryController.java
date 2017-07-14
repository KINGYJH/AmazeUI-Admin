package com.king.modules.sys.dictionary.web;

import com.king.common.web.BaseController;
import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:06
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/dictionary")
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService dictionaryService;

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/dictionary/DictionaryManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<Dictionary> dataList(Dictionary dictionary, HttpServletRequest request) {
//        return dictionaryService.search(new Pagination<Dictionary>(request), dictionary);
        return null;
    }
}
