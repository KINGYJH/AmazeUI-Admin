package com.king.modules.sys.log.web;

import com.king.common.type.OperationType;
import com.king.common.web.BaseController;
import com.king.common.web.Pagination;
import com.king.modules.sys.log.entity.SysLog;
import com.king.modules.sys.log.service.ISysLogService;
import com.king.modules.sys.log.util.LogUtil;
import com.king.modules.sys.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:37
 */
@Controller
@RequestMapping("${adminPath}/sys/log")
public class SysLogController extends BaseController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        return new ModelAndView("/pages/sys/log/LogManager");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<SysLog> dataList(SysLog sysLog, HttpServletRequest request) {
        LogUtil.write(sysLog, OperationType.SEARCH, "日志:查询数据");
        return sysLogService.pagination(new Pagination<>(request), sysLog);
    }
}
