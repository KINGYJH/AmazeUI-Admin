package com.king.modules.sys.dictionary.service.impl;

import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.dao.IDictionaryDao;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:07
 */
@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private IDictionaryDao<Dictionary, String> dictionaryDao;

    @Override
    public Pagination<Dictionary> search(Pagination pageInfo, Dictionary dictionary) {
        return null;
    }
}
