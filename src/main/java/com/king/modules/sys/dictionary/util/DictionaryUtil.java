package com.king.modules.sys.dictionary.util;

import com.king.common.utils.CacheUtils;
import com.king.common.utils.SpringContextHolder;
import com.king.modules.sys.dictionary.entity.Dictionary;
import com.king.modules.sys.dictionary.service.IDictionaryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJH
 * on 2017/8/4 9:51.
 * 注释: 字典管理类
 */
@Component
public class DictionaryUtil {

    public static final String CACHE_DIC = "cacheDic";

    private static IDictionaryService dictionaryService = SpringContextHolder.getBean(IDictionaryService.class);

    @PostConstruct
    public static void init() {
        List<Dictionary> data = dictionaryService.findAll();
        CacheUtils.put(CacheUtils.SYS_CACHE, CACHE_DIC, data);
    }

    /**
     * 根据dataKey获取数据
     *
     * @param dataKey dataKey
     * @return 字典数据
     */
    @SuppressWarnings("unchecked")
    public static List<Dictionary> getByDataKey(String dataKey) {
        List<Dictionary> resultData = new ArrayList<>();
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        for (Dictionary dictionary : cacheData) {
            if (dictionary.getDataKey().equals(dataKey)) {
                resultData.add(dictionary);
            }
        }
        return resultData;
    }

    /**
     * 根据dataKey和dataValue获取字典
     *
     * @param dataKey   dataKey
     * @param dataValue dataValue
     * @return 字典数据
     */
    @SuppressWarnings("unchecked")
    public static Dictionary getByDataKeyAndDataValue(String dataKey, String dataValue) {
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        for (Dictionary dictionary : cacheData) {
            if (dictionary.getDataKey().equals(dataKey) && dictionary.getDataValue().equals(dataValue)) {
                return dictionary;
            }
        }
        return new Dictionary();
    }

    /**
     * 根据id获取字典
     *
     * @param id id
     * @return 字典数据
     */
    @SuppressWarnings("unchecked")
    public static Dictionary getById(String id) {
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        for (Dictionary dictionary : cacheData) {
            if (dictionary.getId().equals(id)) {
                return dictionary;
            }
        }
        return new Dictionary();
    }

    /**
     * 向缓存中添加数据
     *
     * @param dictionary 字典
     */
    @SuppressWarnings("unchecked")
    public static void add(Dictionary dictionary) {
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        cacheData.add(dictionary);
        CacheUtils.put(CacheUtils.SYS_CACHE, CACHE_DIC, cacheData);
    }

    /**
     * 删除缓存中指定数据
     *
     * @param dictionary 字典
     */
    @SuppressWarnings("unchecked")
    public static void remover(Dictionary dictionary) {
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        for (Dictionary item : cacheData) {
            if (dictionary.getId().equals(item.getId())) {
                cacheData.remove(item);
                break;
            }
        }
        CacheUtils.put(CacheUtils.SYS_CACHE, CACHE_DIC, cacheData);
    }

    /**
     * 更新数据
     *
     * @param dictionary 字典
     */
    @SuppressWarnings("unchecked")
    public static void update(Dictionary dictionary) {
        List<Dictionary> cacheData = (List<Dictionary>) CacheUtils.get(CacheUtils.SYS_CACHE, CACHE_DIC);
        for (Dictionary item : cacheData) {
            if (dictionary.getId().equals(item.getId())) {
                cacheData.remove(item);
                cacheData.add(dictionary);
                break;
            }
        }
        CacheUtils.put(CacheUtils.SYS_CACHE, CACHE_DIC, cacheData);
    }

    /**
     * 重新加载
     */
    public static void reload() {
        init();
    }
}
