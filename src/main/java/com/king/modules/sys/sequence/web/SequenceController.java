package com.king.modules.sys.sequence.web;

import com.king.common.web.BaseController;
import com.king.common.web.JSONMessage;
import com.king.common.web.Pagination;
import com.king.modules.sys.sequence.entity.Sequence;
import com.king.modules.sys.sequence.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        } catch (Exception e) {
            msg.setMsg("系统错误,请稍后在试!");
            msg.setStatus(JSONMessage.Status.FAIL);
        }
        return msg;
    }
}
