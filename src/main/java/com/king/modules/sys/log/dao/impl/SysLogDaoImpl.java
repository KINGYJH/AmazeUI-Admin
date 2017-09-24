package com.king.modules.sys.log.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.log.dao.ISysLogDao;
import com.king.modules.sys.log.entity.SysLog;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/9/24 16:34
 */
@Repository("sysLogDao")
public class SysLogDaoImpl extends BaseDao<SysLog, String> implements ISysLogDao<SysLog, String> {
}
