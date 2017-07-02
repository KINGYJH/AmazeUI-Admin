package com.king.modules.sys.user.service.impl;

import com.king.modules.sys.user.dao.IUserDao;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:21
 */
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao<User, String> userDao;

    @Override
    public User getByID(String id) {
        return userDao.selectByPrimaryKey(id);
    }

}
