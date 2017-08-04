package com.king.modules.sys.dictionary.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.dictionary.dao.IDictionaryGroupDao;
import com.king.modules.sys.dictionary.entity.Dictionary;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:09
 */
@Repository("dictionaryGroupDao")
public class DictionaryGroupDaoImpl extends BaseDao<Dictionary, String>
        implements IDictionaryGroupDao<Dictionary, String> {
}
