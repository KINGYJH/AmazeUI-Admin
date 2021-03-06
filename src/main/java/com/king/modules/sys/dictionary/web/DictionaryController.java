package com.king.modules.sys.dictionary.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import com.king.modules.sys.log.util.LogUtil;
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
        LogUtil.write(dictionary, OperationType.SEARCH, "数据字典:查询数据");
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

            LogUtil.write(dictionary, OperationType.SAVE, "数据字典:添加");
        } catch (ExistException e) {
            msg.setMsg(e.getMessage());
            msg.setStatus(JSONMessage.Status.FAIL);
        } catch (Exception e) {
            msg.setMsg("系统错误,请稍后在试!");
            msg.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(dictionary, OperationType.SAVE, e.getMessage(), ResultType.FAIL, "数据字典:添加");
        }
        return msg;
    }

    @RequestMapping(value = "/edit_page")
    public ModelAndView editPage(String id, Model model) {
        try {
            model.addAttribute("data", dictionaryService.getById(id));
        } catch (NoFoundException e) {
            model.addAttribute("alertMessage", e.getMessage());
        }
        return new ModelAndView("/pages/sys/dictionary/DictionaryEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage edit(Dictionary dictionary) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            dictionaryService.edit(dictionary);
            jsonMessage.setMsg("修改成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write(dictionary, OperationType.UPDATE, "数据字典:修改");
        } catch (ExistException | ConcurrencyException | NoFoundException e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg(e.getMessage());
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("系统错误，请稍后再试");

            LogUtil.write(dictionary, OperationType.UPDATE, e.getMessage(), ResultType.FAIL, "数据字典:修改");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/get_by_dataKey")
    @ResponseBody
    public JSONMessage getByDataKey(String dataKey) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            List<Dictionary> data = dictionaryService.getByDataKey(dataKey);
            jsonMessage.setData(data);
            jsonMessage.setMsg("获取数据成功");
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write(dataKey, OperationType.SEARCH, "数据字典:根据Key获取数据");
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg(e.getMessage());

            LogUtil.write(dataKey, OperationType.SEARCH, e.getMessage(), ResultType.FAIL, "数据字典:根据Key获取数据");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage delete(Dictionary dictionary) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            dictionaryService.del(dictionary);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
            jsonMessage.setMsg("删除成功");

            LogUtil.write(dictionary, OperationType.DEL, "数据字典:删除");
        } catch (NoFoundException | ConcurrencyException e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg(e.getMessage());
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("系统异常,请稍后再试");

            LogUtil.write(dictionary, OperationType.DEL, e.getMessage(), ResultType.FAIL, "数据字典:删除");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/get_all_data", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage getAll() {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            List<Dictionary> data = dictionaryService.findAll();
            jsonMessage.setData(data);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
            jsonMessage.setMsg("获取数据成功");

            LogUtil.write("", OperationType.SEARCH, "数据字典:获取所有数据");
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("系统异常,请稍后再试");

            LogUtil.write("", OperationType.SEARCH, e.getMessage(), ResultType.FAIL, "数据字典:获取所有数据");
        }
        return jsonMessage;
    }
}
