package com.king.modules.sys.sequence.service;

import com.king.common.web.Pagination;
import com.king.modules.sys.sequence.entity.Sequence; /**
 * @author by yjh
 * @DateTime 2017/7/21 20:35
 */
public interface ISequenceService {
    Pagination<Sequence> pagination(Pagination<Sequence> pagination, Sequence sequence);

    Sequence getById(String id);

    void save(Sequence sequence);

    Sequence findByTableName(String tableName);

    void updateValue(String id, Integer number);
}
