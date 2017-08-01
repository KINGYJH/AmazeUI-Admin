package com.king.modules.sys.role.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.role.dao.IRoleDao;
import com.king.modules.sys.role.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * Created by YJH
 * on 2017/8/1 16:38.
 * 注释:
 */
@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role, String> implements IRoleDao<Role, String> {
}
