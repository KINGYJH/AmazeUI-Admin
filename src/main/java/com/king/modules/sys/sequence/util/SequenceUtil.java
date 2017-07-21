package com.king.modules.sys.sequence.util;

import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.sequence.entity.Sequence;
import com.king.modules.sys.sequence.service.ISequenceService;
import com.king.modules.sys.sequence.service.impl.SequenceServiceImpl;

import java.util.UUID;

/**
 * @author by yjh
 * @DateTime 2017/7/21 20:36
 */
public class SequenceUtil {

    private static ISequenceService sequenceService = SpringContextHolder.getBean(ISequenceService.class);

    /**
     * 根据表名获取id序列值
     *
     * @param tableName
     * @return
     */
    public static String getNextId(String tableName) {
        Sequence sequence = sequenceService.findByTableName(tableName);
        if (null != sequence) {
            Integer number = sequence.getNewValue() + 1;
            String id = sequence.getPrefix() + "_" + String.format("%0" + sequence.getLength() + "d", number);
            sequenceService.updateValue(sequence.getId(), number);
            return id;
        } else {
            return UUID.randomUUID().toString().replaceAll("-", "");
        }
    }

}
