package com.king.common.persistence;

import com.king.common.utils.GenericsUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * @author by yjh
 * @DateTime 2017/7/2 19:42
 */
@Repository
public class BaseDao<T, ID extends Serializable> extends SqlSessionDaoSupport implements IBaseDao<T, ID> {

    private final String POSTFIX = "Dao";

    private final String _INSERT = ".insert";

    private final String _INSERTSELECTIVE = ".insertSelective";

    private final String _SELECTBYPRIMARYKEY = ".selectByPrimaryKey";

    private final String _UPDATEBYPRIMARYKEY = ".updateByPrimaryKey";

    private final String _UPDATEBYPRIMARYKEYSELECTIVE = ".updateByPrimaryKeySelective";

    private final String _DELETEBYPRIMARYKEY = ".deleteByPrimaryKey";

    /*GenericsUtils为工具类，请见下方代码
          泛型获得XXXEntity，将其转换为XXXEntityDao，具体操作替换掉Entity变成XXXDao，对应Mapper.xml中的namespace命名
        */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public String getNampSpace() {
        Class<T> clazz = (Class) GenericsUtils.getSuperClassGenricType(this.getClass());
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
        return getSqlSession().selectOne(getNampSpace() + _SELECTBYPRIMARYKEY, id);
    }
}
