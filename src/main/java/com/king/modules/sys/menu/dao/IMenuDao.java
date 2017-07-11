package com.king.modules.sys.menu.dao;

import com.king.common.persistence.IBaseDao;
import com.king.modules.sys.menu.entity.Menu;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YJH
 * on 2017/7/10 16:28.
 * 注释:
 */
public interface IMenuDao<T, ID extends Serializable> extends IBaseDao<T, ID> {
    List<Menu> findByParentId(String parentId);
}
