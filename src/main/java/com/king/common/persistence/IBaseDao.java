package com.king.common.persistence;

import java.io.Serializable;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:59
 */
public interface IBaseDao<T, ID extends Serializable> {
    T selectByPrimaryKey(ID id);
}
