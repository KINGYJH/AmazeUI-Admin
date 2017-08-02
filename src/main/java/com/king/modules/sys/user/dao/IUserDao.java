package com.king.modules.sys.user.dao;

import com.king.common.persistence.IBaseDao;
import com.king.modules.sys.user.entity.User;

import java.io.Serializable;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:19
 */
public interface IUserDao<T, ID extends Serializable> extends IBaseDao<T, ID> {

    User getByAcctName(String acctName);
}
