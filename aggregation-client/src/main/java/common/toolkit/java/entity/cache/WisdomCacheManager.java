/**
 * Project Name:com.wisdombud.cache
 * File Name:ApplicationCacheManager.java
 * Package Name:com.wisdombud.cache.manager
 * Date:2013-7-5 下午10:56:44
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.cache;

import com.google.common.cache.LoadingCache;
import lombok.extern.log4j.Log4j2;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

/**
 * ClassName: ApplicationCacheManager. <br/>
 * Function: TODO <br/>
 * date: 2013-7-5 下午10:56:44 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
@Log4j2
@SuppressWarnings("unchecked")
public class WisdomCacheManager {
    /**
     * 包含所有cache的map.
     */
    public final static Map<String, LoadingCache> CACHES = new ConcurrentHashMap<String, LoadingCache>();

    /**
     * 添加或替换其中一个cache. <br/>
     * @author ghlin
     * @param cacheId
     * @param cache
     */
    public static void addCache(String cacheId, LoadingCache cache) {
        CACHES.put(cacheId, cache);
    }

    /**
     * 获取一个cache. <br/>
     * @author ghlin
     * @param cacheId
     * @return
     */
    public static LoadingCache<String, Object> getCache(String cacheId) {
        return CACHES.get(cacheId);
    }

    /**
     * 从定义的cache里获取一个cache对象. <br/>
     * @author ghlin
     * @param cacheId
     * @param elementId
     * @return
     */
    public static Object getCachedObject(String cacheId, String elementId) {
        Object result = null;

        LoadingCache cache = getCache(cacheId);
        if (cache != null) {
            try {
                result = cache.get(elementId);
            } catch (ExecutionException e) {
                log.error("loading cache : " + elementId + " error!", e);
            }
        }

        return result;
    }
}
