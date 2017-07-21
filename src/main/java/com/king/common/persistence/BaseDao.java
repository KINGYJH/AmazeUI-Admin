package com.king.common.persistence;

import com.king.common.annotation.DbInsertBefore;
import com.king.common.annotation.DbUpdateBefore;
import com.king.common.web.Pagination;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:42
 */
@Repository
public abstract class BaseDao<T, ID extends Serializable> implements IBaseDao<T, ID> {

    private Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public BaseDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    /**
     * 创建与会话无关的检索标准对象
     *
     * @return
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return DetachedCriteria.forClass(getPersistentClass());
    }

    @Override
    public void clear() {
        getSession().clear();
    }

    @Override
    public void evict(Object obj) {
        getSession().evict(obj);
    }

    @Override
    public void refresh(Object obj) {
        getSession().refresh(obj);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T loadById(ID id) {
        return (T) getSession().load(getPersistentClass(), id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public T getById(ID id) {
        return (T) getSession().get(getPersistentClass(), id);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void save(T entity) {
        try {
            // 获取实体编号
            Object id = null;
            for (Method method : entity.getClass().getMethods()) {
                Id idAnn = method.getAnnotation(Id.class);
                if (idAnn != null) {
                    id = method.invoke(entity);
                    break;
                }
            }

            // 插入前执行方法
            if (null == id || "".equals(id)) {
                for (Method method : entity.getClass().getMethods()) {
                    DbInsertBefore insertBefore = method.getAnnotation(DbInsertBefore.class);
                    if (insertBefore != null) {
                        method.invoke(entity);
                        break;
                    }
                }
            } else {
                // 更新前执行方法
                for (Method method : entity.getClass().getMethods()) {
                    DbUpdateBefore updateBefore = method.getAnnotation(DbUpdateBefore.class);
                    if (updateBefore != null) {
                        method.invoke(entity);
                        break;
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        getSession().saveOrUpdate(entity);
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return find(createDetachedCriteria());
    }

    /**
     * 使用检索标准对象查询
     *
     * @param detachedCriteria
     * @return
     */
    @Override
    public List<T> find(DetachedCriteria detachedCriteria) {
        return find(detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * 使用检索标准对象查询
     *
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> find(DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {
        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        criteria.setResultTransformer(resultTransformer);
        return criteria.list();
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @Override
    public Pagination<T> pagination(Pagination<T> page) {
        return pagination(page, createDetachedCriteria());
    }


    /**
     * 使用检索标准对象分页查询
     *
     * @param page
     * @param detachedCriteria
     * @return
     */
    @Override
    public Pagination<T> pagination(Pagination<T> page, DetachedCriteria detachedCriteria) {
        return pagination(page, detachedCriteria, Criteria.DISTINCT_ROOT_ENTITY);
    }

    /**
     * 使用检索标准对象分页查询
     *
     * @param page
     * @param detachedCriteria
     * @param resultTransformer
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public Pagination<T> pagination(Pagination<T> page, DetachedCriteria detachedCriteria, ResultTransformer resultTransformer) {

        Criteria criteria = detachedCriteria.getExecutableCriteria(getSession());
        criteria.setResultTransformer(resultTransformer);

        // set page
        int firstResult = (page.getPageNumber() - 1) * page.getPageSize();
        criteria.setFirstResult(firstResult).setMaxResults(page.getPageSize());

        //set data
        page.setRows(criteria.list());

        // set total
        Criteria criteriaCount = detachedCriteria.getExecutableCriteria(getSession()).setProjection(Projections.rowCount());
        int total = ((Long) criteriaCount.uniqueResult()).intValue();
        page.setTotal(total);
        return page;
    }
}
