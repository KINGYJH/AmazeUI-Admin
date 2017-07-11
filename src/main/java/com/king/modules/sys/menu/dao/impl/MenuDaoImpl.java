package com.king.modules.sys.menu.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.menu.dao.IMenuDao;
import com.king.modules.sys.menu.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 16:28.
 * 注释:
 */
@Repository
public class MenuDaoImpl extends BaseDao<Menu, String> implements IMenuDao<Menu, String> {

    private final String _SELECT_BY_PARENT_ID = ".selectByParentId";

    private final String _IS_HAS_CHILD = ".isHasChild";

    @Override
    public List<Menu> findByParentId(String parentId) {
        return getSqlSession().selectList(getNameSpace() + _SELECT_BY_PARENT_ID, parentId);
    }

    @Override
    public Integer hasChildCount(String id) {
        return getSqlSession().selectOne(getNameSpace() + _IS_HAS_CHILD, id);
    }
}
