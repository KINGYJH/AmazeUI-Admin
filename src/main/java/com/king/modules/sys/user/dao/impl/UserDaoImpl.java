package com.king.modules.sys.user.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.user.dao.IUserDao;
import com.king.modules.sys.user.entity.User;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:20
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDao<User, String> implements IUserDao<User, String> {

    @Override
    public User getByAcctName(String acctName) {
        DetachedCriteria dc = createDetachedCriteria();
        dc.add(Restrictions.eq("acctName", acctName));
        Criteria criteria = dc.getExecutableCriteria(getSession());
        return (User) criteria.uniqueResult();
    }
}
