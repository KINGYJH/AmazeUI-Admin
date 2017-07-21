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
@Repository("menuDao")
public class MenuDaoImpl extends BaseDao<Menu, String> implements IMenuDao<Menu, String> {

}
