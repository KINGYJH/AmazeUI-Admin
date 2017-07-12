package com.king.modules.sys.dictionary.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.dictionary.dao.IDictionaryDao;
import com.king.modules.sys.dictionary.entity.Dictionary;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:09
 */
@Repository
public class DictionaryDaoImpl extends BaseDao<Dictionary, String> implements IDictionaryDao<Dictionary, String> {
}
