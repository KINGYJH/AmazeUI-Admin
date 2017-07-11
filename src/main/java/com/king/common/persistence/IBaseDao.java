package com.king.common.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:59
 */
public interface IBaseDao<T, ID extends Serializable> {
    T selectByPrimaryKey(ID id);

    T save(T enyity);

    List<T> findAll();
}
