package com.king.common.persistence;

import com.king.common.persistence.search.Criterion;
import com.king.common.web.Pagination;

import java.io.Serializable;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:59
 */
public interface IBaseDao<T, ID extends Serializable> {
    T selectByPrimaryKey(ID id);

    T save(T entity);

    List<T> findAll();

    Pagination<T> pagination(Pagination pagination, List<Criterion> criterionList);
}
