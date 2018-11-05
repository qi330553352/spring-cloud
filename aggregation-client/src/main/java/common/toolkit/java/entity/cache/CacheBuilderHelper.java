/**
 * Project Name:com.wisdombud.cache
 * File Name:CacheBuilderHelper.java
 * Package Name:com.wisdombud.cache.manager
 * Date:2013-7-5 下午11:16:46
 * Copyright (c) 2013, www.wisdombud.com All Rights Reserved.
 */

package common.toolkit.java.entity.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Map;

/**
 * ClassName: CacheBuilderHelper. <br/>
 * Function: TODO <br/>
 * date: 2013-7-5 下午11:16:46 <br/>
 * @author ghlin
 * @version
 * @since JDK 1.6
 */
public class CacheBuilderHelper {
    private final static long MAX_SIZE_STATIC_CACHE = 200;

    /**
     * 预加载静态缓存
     * @param cacheId 缓存key
     * @param values 初始化的缓存值
     */
    @SuppressWarnings("all")
    public static void createPrepopulatedStaticCache(String cacheId, Map values) {
        LoadingCache staticCache = CacheBuilder.newBuilder().maximumSize(MAX_SIZE_STATIC_CACHE)
                .build(new CacheLoader() {
                    @Override
                    public Object load(Object key) throws Exception {
                        return null;
                    }
                });

        // 从参数values里预填充缓存
        if (values != null) {
            for (Object key : values.keySet()) {
                staticCache.asMap().putIfAbsent(key.toString(), values.get(key));
            }
        }

        // 将缓存添加到manager
        WisdomCacheManager.addCache(cacheId, staticCache);
    }

    /**
     * 无预加载的动态缓存.
     * @author ghlin
     * @param cacheId
     * @param dynamicCache
     */
    public static void createDynamicCache(String cacheId, LoadingCache dynamicCache) {
        WisdomCacheManager.addCache(cacheId, dynamicCache);
    }
}
