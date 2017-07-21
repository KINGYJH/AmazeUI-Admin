package com.king.modules.sys.sequence.dao;

import com.king.common.persistence.IBaseDao;

import java.io.Serializable;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:34
 */
public interface ISequenceDao<T, ID extends Serializable> extends IBaseDao<T, ID> {
    T findByTableName(ID tableName);
}
