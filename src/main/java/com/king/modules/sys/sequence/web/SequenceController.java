package com.king.modules.sys.sequence.web;

import com.king.common.exception.ConcurrencyException;
import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.type.OperationType;
import com.king.common.type.ResultType;
import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.log.util.LogUtil;
import com.king.modules.sys.sequence.entity.Sequence;
import com.king.modules.sys.sequence.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:37
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/sequence")
public class SequenceController extends BaseController {

    @Autowired
    private ISequenceService sequenceService;

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/sequence/SequenceManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<Sequence> dataList(Sequence sequence, HttpServletRequest request) {
        LogUtil.write(sequence, OperationType.SEARCH, "序列:查询数据");
        return sequenceService.pagination(new Pagination<>(request), sequence);
    }

    @RequestMapping(value = "/save_page")
    public ModelAndView savePage() {
        return new ModelAndView("/pages/sys/sequence/SequenceSave");
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage save(Sequence sequence) {
        JSONMessage msg = new JSONMessage();
        try {
            sequenceService.save(sequence);
            msg.setMsg("添加成功!");
            msg.setStatus(JSONMessage.Status.SUCCESS);

            LogUtil.write(sequence, OperationType.SAVE, "序列:添加");
        } catch (ExistException | NoFoundException e) {
            msg.setMsg(e.getMessage());
            msg.setStatus(JSONMessage.Status.FAIL);
        } catch (Exception e) {
            msg.setMsg("系统错误,请稍后在试!");
            msg.setStatus(JSONMessage.Status.FAIL);

            LogUtil.write(sequence, OperationType.SAVE, e.getMessage(), ResultType.FAIL, "角色:添加");
        }
        return msg;
    }

    @RequestMapping(value = "/edit_page")
    public ModelAndView editPage(String id, Model model) {
        try {
            model.addAttribute("data", sequenceService.getById(id));
        } catch (NoFoundException e) {
            model.addAttribute("alertMessage", e.getMessage());
        }
        return new ModelAndView("/pages/sys/sequence/SequenceEdit");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage edit(Sequence sequence) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            sequenceService.edit(sequence);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
            jsonMessage.setMsg("修改成功");

            LogUtil.write(sequence, OperationType.UPDATE, "序列:修改");
        } catch (NoFoundException | ConcurrencyException e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg(e.getMessage());
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("系统错误,请稍后再试");

            LogUtil.write(sequence, OperationType.UPDATE, e.getMessage(), ResultType.FAIL, "角色:修改");
        }
        return jsonMessage;
    }

    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public JSONMessage del(Sequence sequence) {
        JSONMessage jsonMessage = new JSONMessage();
        try {
            sequenceService.del(sequence);
            jsonMessage.setStatus(JSONMessage.Status.SUCCESS);
            jsonMessage.setMsg("删除成功");

            LogUtil.write(sequence, OperationType.DEL, "序列:删除");
        } catch (NoFoundException | ConcurrencyException e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg(e.getMessage());
        } catch (Exception e) {
            jsonMessage.setStatus(JSONMessage.Status.FAIL);
            jsonMessage.setMsg("系统错误,请稍后再试");

            LogUtil.write(sequence, OperationType.DEL, e.getMessage(), ResultType.FAIL, "角色:删除");
        }
        return jsonMessage;
    }

}
