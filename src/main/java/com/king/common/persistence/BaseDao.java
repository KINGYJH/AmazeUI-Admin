package com.king.common.persistence;

import com.king.common.annotation.DbInsertBefore;
import com.king.common.annotation.DbUpdateBefore;
import com.king.common.persistence.search.Criterion;
import com.king.common.utils.GenericsUtils;
import com.king.common.web.Pagination;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:42
 */
@Repository
public class BaseDao<T, ID extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<T, ID> {

    private final String POSTFIX = "Dao";

    private final String _INSERT = ".insert";

    private final String _INSERT_SELECTIVE = ".insertSelective";

    private final String _SELECT_BY_PRIMARY_KEY = ".selectByPrimaryKey";

    private final String _SELECT_ALL = ".selectAll";

    private final String _SELECT_BY_ENTITY = ".selectByEntity";

    private final String _UPDATE_BY_PRIMARY_KEY = ".updateByPrimaryKey";

    private final String _UPDATE_BY_PRIMARY_KEY_SELECTIVE = ".updateByPrimaryKeySelective";

    private final String _DELETE_BY_PRIMARY_KEY = ".deleteByPrimaryKey";

    private final String _SELECT_PAGINATION = ".selectPagination";


    /*GenericsUtils为工具类，请见下方代码
          泛型获得XXXEntity，将其转换为XXXEntityDao，具体操作替换掉Entity变成XXXDao，对应Mapper.xml中的namespace命名
        */
    public String getNameSpace() {
        Class clazz = GenericsUtils.getSuperClassGenricType(this.getClass());
        String simpleName = clazz.getSimpleName() + POSTFIX;
        return simpleName.contains("Entity") ? simpleName.replace("Entity", "") : simpleName;
    }

    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    /**
     * 根据主键获取数据
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public T selectByPrimaryKey(ID id) {
        return getSqlSession().selectOne(getNameSpace() + _SELECT_BY_PRIMARY_KEY, id);
    }

    @Override
    public T save(T entity) {
        try {
            // 获取实体编号
            Object id = null;
            for (Method method : entity.getClass().getMethods()) {
                DbInsertBefore idAnn = method.getAnnotation(DbInsertBefore.class);
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

        getSqlSession().insert(getNameSpace() + _INSERT, entity);
        return entity;
    }

    @Override
    public List<T> findAll() {
        return getSqlSession().selectList(getNameSpace() + _SELECT_ALL);
    }

    @Override
    public Pagination<T> pagination(Pagination pagination, List<Criterion> criterionList) {
        Integer total = findAll().size();

        List<T> rows = getSqlSession().selectList(getNameSpace() + _SELECT_PAGINATION, pagination);

        pagination.setTotal(total);
        pagination.setRows(rows);
        return pagination;
    }
}
