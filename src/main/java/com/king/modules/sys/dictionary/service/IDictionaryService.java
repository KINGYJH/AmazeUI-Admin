package com.king.modules.sys.dictionary.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.entity.Dictionary;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:07
 */
public interface IDictionaryService {
    Pagination<Dictionary> search(Pagination pageInfo, Dictionary dictionary);
}
