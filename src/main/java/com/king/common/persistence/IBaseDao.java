package com.king.common.persistence;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:59
 */
public interface IBaseDao<T, ID extends Serializable> {

    T loadById(ID id);

    T getById(ID id);

    void save(T entity);

    void saveOrUpdate(T entity);

    void update(T entity);

    void delete(T entity);

    void flush();

    DetachedCriteria createDetachedCriteria();

    void clear();

    void evict(Object obj);

    void refresh(Object obj);

    @SuppressWarnings("unchecked")
    List<T> findAll();

    List<T> find(DetachedCriteria detachedCriteria);

    @SuppressWarnings("unchecked")
    List<T> find(DetachedCriteria detachedCriteria, ResultTransformer resultTransformer);
}
