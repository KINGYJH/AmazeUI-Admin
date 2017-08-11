package com.king.modules.sys.user.service.impl;

import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.utils.PasswordHelper;
import com.king.common.web.Pagination;
import com.king.modules.sys.role.entity.Role;
import com.king.modules.sys.role.service.IRoleService;
import com.king.modules.sys.user.dao.IUserDao;
import com.king.modules.sys.user.entity.User;
import com.king.modules.sys.user.service.IUserService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:21
 */
@Service("userService")
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao<User, String> userDao;

    @Autowired
    private IRoleService roleService;

    @Override
    public Pagination<User> pagination(Pagination<User> pagination, User user) {
        DetachedCriteria dc = userDao.createDetachedCriteria();

        return userDao.pagination(pagination, dc);
    }

    @Override
    public User getById(String id) {
        User user = userDao.getById(id);
        if (null == user) {
            throw new NoFoundException("id为[" + id + "]的数据未找到");
        }
        return user;
    }

    @Override
    @Transactional()
    public void save(User user) {
        if (checkIsExist(user.getAcctName())) {
            throw new ExistException("用户名[" + user.getAcctName() + "]的数据已存在");
        }

        String[] roleIds = user.getRoleIds().split(",");
        List<Role> roleList = roleService.findByIds(roleIds);

        user.setRole(roleList);
        user.setPwd(PasswordHelper.encryptPwd(user.getPwd()));

        userDao.save(user);
    }

    /**
     * 判断数据是否存在
     *
     * @param acctName 用户名
     * @return true存在false反之
     */
    @Override
    public boolean checkIsExist(String acctName) {
        DetachedCriteria dc = userDao.createDetachedCriteria();
        dc.add(Restrictions.eq("acctName", acctName));
        return !userDao.find(dc).isEmpty();
    }

    @Override
    public User getByAcctName(String acctName) {
        return userDao.getByAcctName(acctName);
    }

    @Override
    @Transactional()
    public void updateLogin(User user) {
        userDao.update(user);
    }
}
