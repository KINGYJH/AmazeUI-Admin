package com.king.modules.sys.dictionary.web;

import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return dictionaryService.pagination(new Pagination<>(request), dictionary);
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/dictionary/DictionarySave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(Dictionary dictionary) {
        JSONMessage msg = new JSONMessage();
        try {
            dictionaryService.save(dictionary);
            msg.setMsg("添加成功!");
            msg.setStatus(JSONMessage.Status.SUCCESS);
        } catch (Exception e) {
            msg.setMsg("系统错误,请稍后在试!");
            msg.setStatus(JSONMessage.Status.FAIL);
        }
        return msg;
    }
}
