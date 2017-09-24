package com.king.modules.sys.log.service.impl;

import com.king.common.web.Pagination;
import com.king.modules.sys.log.dao.ISysLogDao;
import com.king.modules.sys.log.entity.SysLog;
import com.king.modules.sys.log.service.ISysLogService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:36
 */
@Service("sysLogService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao<SysLog, String> sysLogDao;

    @Override
    public Pagination<SysLog> pagination(Pagination<SysLog> pagination, SysLog sysLog) {
        DetachedCriteria dc = sysLogDao.createDetachedCriteria();

        return sysLogDao.pagination(pagination, dc);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }
}
