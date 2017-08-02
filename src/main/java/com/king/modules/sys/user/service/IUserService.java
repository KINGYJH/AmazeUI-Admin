package com.king.modules.sys.user.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.user.entity.User;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:20
 */
public interface IUserService {
    Pagination<User> pagination(Pagination<User> pagination, User user);

    User getById(String id);

    void save(User user);

    boolean checkIsExist(String acctName);

    User getByAcctName(String acctName);
}
