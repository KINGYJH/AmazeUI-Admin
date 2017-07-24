package com.king.modules.sys.sequence.service.impl;

import com.king.common.web.Pagination;
import com.king.modules.sys.sequence.dao.ISequenceDao;
import com.king.modules.sys.sequence.entity.Sequence;
import com.king.modules.sys.sequence.service.ISequenceService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:35
 */
@Service("sequenceService")
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SequenceServiceImpl implements ISequenceService {

    @Autowired
    private ISequenceDao<Sequence, String> sequenceDao;

    @Override
    public Pagination<Sequence> pagination(Pagination<Sequence> pagination, Sequence sequence) {
        DetachedCriteria dc = sequenceDao.createDetachedCriteria();
        return sequenceDao.pagination(pagination, dc);
    }

    @Override
    public Sequence getById(String id) {
        return sequenceDao.getById(id);
    }

    @Override
    @Transactional()
    public void save(Sequence sequence) {
        sequenceDao.saveOrUpdate(sequence);
    }

    @Override
    public Sequence findByTableName(String tableName) {
        return sequenceDao.findByTableName(tableName);
    }

    @Override
    @Transactional()
    public void updateValue(String id, Integer number) {
        Sequence sequence = getById(id);
        sequence.setNewValue(number);
        sequenceDao.saveOrUpdate(sequence);
    }
}
