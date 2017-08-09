package com.king.modules.sys.dictionary.service.impl;

import com.king.common.exception.ExistException;
import com.king.common.exception.NoFoundException;
import com.king.common.web.Pagination;
import com.king.modules.sys.dictionary.dao.IDictionaryGroupDao;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import com.king.modules.sys.dictionary.util.DictionaryUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author by yjh
 * @DateTime 2017/7/12 20:07
 */
@Service("dictionaryService")
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private IDictionaryGroupDao<Dictionary, String> dictionaryDao;

    @Override
    public Pagination<Dictionary> pagination(Pagination<Dictionary> pagination, Dictionary dictionary) {
        DetachedCriteria dc = dictionaryDao.createDetachedCriteria();
        dc.addOrder(Order.asc("dataKey"));
        dc.addOrder(Order.asc("sort"));
        return dictionaryDao.pagination(pagination, dc);
    }

    @Override
    @Transactional()
    public void save(Dictionary dictionary) {
        if (checkIsExist(dictionary.getDataKey(), dictionary.getDataValue())) {
            throw new ExistException("dataKey[" + dictionary.getDataKey() + "],dataValue[" + dictionary.getDataValue() + "]的数据已存在");
        }

        dictionaryDao.save(dictionary);
        DictionaryUtil.add(dictionary);
    }

    @Override
    public Dictionary getById(String id) {
        Dictionary dictionary = dictionaryDao.getById(id);
        if (null == dictionary) {
            throw new NoFoundException("id为[" + id + "]的数据未找到");
        }
        return dictionary;
    }

    @Override
    @Transactional()
    public void edit(Dictionary dictionary) {
        Dictionary editDictionary = getById(dictionary.getId());
        editDictionary.fainWhenConcurrencyViolation(dictionary.getVersion());

        if (!dictionary.getDataValue().equals(editDictionary.getDataValue())) {
            if (checkIsExist(dictionary.getDataKey(), dictionary.getDataValue())) {
                throw new ExistException("dataKey[" + dictionary.getDataKey() + "],dataValue[" + dictionary.getDataValue() + "]的数据已存在");
            }
        }

        editDictionary.setDataValue(dictionary.getDataValue());
        editDictionary.setSort(dictionary.getSort());
        dictionaryDao.update(editDictionary);

        DictionaryUtil.update(dictionary);
    }

    @Override
    public List<Dictionary> getByDataKey(String dataKey) {
        DetachedCriteria dc = dictionaryDao.createDetachedCriteria();
        dc.add(Restrictions.eq("dataKey", dataKey));
        dc.addOrder(Order.asc("sort"));
        return dictionaryDao.find(dc);
    }

    @Override
    @Transactional()
    public void del(Dictionary dictionary) {
        Dictionary delDictionary = getById(dictionary.getId());
        delDictionary.fainWhenConcurrencyViolation(dictionary.getVersion());

        dictionaryDao.delete(delDictionary);

        DictionaryUtil.remover(dictionary);
    }

    /**
     * 判断数据是否存在
     *
     * @param dataKey   key
     * @param dataValue value
     * @return true存在false反之
     */
    @Override
    public boolean checkIsExist(String dataKey, String dataValue) {
        DetachedCriteria dc = dictionaryDao.createDetachedCriteria();
        dc.add(Restrictions.eq("dataKey", dataValue));
        dc.add(Restrictions.eq("dataValue", dataValue));
        return !dictionaryDao.find(dc).isEmpty();
    }

    @Override
    public List<Dictionary> findAll() {
        return dictionaryDao.findAll();
    }
}
