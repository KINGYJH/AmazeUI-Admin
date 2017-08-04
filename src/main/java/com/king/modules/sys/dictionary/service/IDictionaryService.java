package com.king.modules.sys.dictionary.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.entity.Dictionary;

import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:07
 */
public interface IDictionaryService {
    Pagination<Dictionary> pagination(Pagination<Dictionary> pagination, Dictionary dictionary);

    void save(Dictionary dictionary);

    Dictionary getById(String id);

    void edit(Dictionary dictionary);

    List<Dictionary> getByDataKey(String dataKey);

    void del(Dictionary dictionary);

    boolean checkIsExist(String dataKey, String dataValue);

    List<Dictionary> findAll();

}
