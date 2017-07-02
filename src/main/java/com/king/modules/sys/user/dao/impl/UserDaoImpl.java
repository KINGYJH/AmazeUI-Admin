package com.king.modules.sys.user.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.user.dao.IUserDao;
import com.king.modules.sys.user.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:20
 */
@Repository
public class UserDaoImpl extends BaseDao<User, String> implements IUserDao<User, String> {

}
