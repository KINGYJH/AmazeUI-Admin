package com.king.modules.sys.sequence.service.impl;

import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.utils.DataBaseUtils;
import com.king.common.web.Pagination;
import com.king.modules.sys.sequence.dao.ISequenceDao;
import com.king.modules.sys.sequence.entity.Sequence;
import com.king.modules.sys.sequence.service.ISequenceService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
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
        Sequence sequence = sequenceDao.getById(id);
        if (null == sequence) {
            throw new NoFoundException("id为[" + id + "]的数据未找到");
        }
        return sequence;
    }

    @Override
    @Transactional()
    public void save(Sequence sequence) {
        if (!DataBaseUtils.tableIsExist(sequence.getTableName())) {
            throw new NoFoundException("[" + sequence.getTableName() + "]表不存在");
        }

        if (checkIsExist(sequence.getTableName())) {
            throw new ExistException("表名为[" + sequence.getTableName() + "]数据已存在");
        }

        sequenceDao.save(sequence);
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
        sequenceDao.update(sequence);
    }

    @Override
    @Transactional
    public void edit(Sequence sequence) {
        Sequence editSequence = getById(sequence.getId());
        editSequence.fainWhenConcurrencyViolation(sequence.getVersion());

        editSequence.setTableName(sequence.getTableName());
        editSequence.setLength(sequence.getLength());
        editSequence.setPkName(sequence.getPkName());
        editSequence.setPrefix(sequence.getPrefix());
        editSequence.setDescribes(sequence.getDescribes());
        sequenceDao.update(editSequence);
    }

    @Override
    @Transactional
    public void del(Sequence sequence) {
        Sequence delSequence = getById(sequence.getId());
        delSequence.fainWhenConcurrencyViolation(sequence.getVersion());

        sequenceDao.delete(delSequence);
    }

    /**
     * 判断tableName数据是否存在
     *
     * @param tableName 表名
     * @return true存在false反之
     */
    @Override
    public boolean checkIsExist(String tableName) {
        DetachedCriteria dc = sequenceDao.createDetachedCriteria();
        dc.add(Restrictions.eq("tableName", tableName));
        return !sequenceDao.find(dc).isEmpty();
    }
}
