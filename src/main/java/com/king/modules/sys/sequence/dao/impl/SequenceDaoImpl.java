package com.king.modules.sys.sequence.dao.impl;

import com.king.common.persistence.BaseDao;
import com.king.modules.sys.sequence.dao.ISequenceDao;
import com.king.modules.sys.sequence.entity.Sequence;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:34
 */
@Repository("sequenceDao")
public class SequenceDaoImpl extends BaseDao<Sequence, String> implements ISequenceDao<Sequence, String> {
    @Override
    public Sequence findByTableName(String tableName) {
        DetachedCriteria dc = createDetachedCriteria();
        dc.add(Restrictions.eq("tableName", tableName));
        return (Sequence) dc.getExecutableCriteria(getSession()).uniqueResult();
    }
}
