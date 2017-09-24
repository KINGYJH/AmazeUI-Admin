package com.king.modules.sys.log.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.log.entity.SysLog;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:35
 */
public interface ISysLogService {
    Pagination<SysLog> pagination(Pagination<SysLog> pagination, SysLog sysLog);

    void save(SysLog sysLog);
}
