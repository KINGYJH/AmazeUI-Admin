package com.king.modules.sys.dictionary.service;

import com.king.common.web.PageInfo;
import com.king.modules.sys.dictionary.entity.Dictionary;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:07
 */
public interface IDictionaryService {
    PageInfo<Dictionary> search(PageInfo pageInfo, Dictionary dictionary);
}
