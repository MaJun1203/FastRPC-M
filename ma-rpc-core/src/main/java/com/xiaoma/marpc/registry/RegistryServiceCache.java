package com.xiaoma.marpc.registry;

import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.xiaoma.marpc.model.ServiceMetaInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Class Name: RegistryServiceMultiCache
 * Description:注册中心服务本地缓存（支持多个服务）
 * Created on: 2025/5/11
 * Author: xiaoma
 */

@Slf4j
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    Map<String, Map<String,ServiceMetaInfo>> serviceCache = new ConcurrentHashMap<>();
    Map<String,ServiceMetaInfo> serviceNodeCache = new ConcurrentHashMap<>();

    /**
     * 写缓存
     *
     * @param serviceKey 服务键名
     * @param newServiceCache 更新后的缓存列表
     * @return
     */
    void writeCache(String serviceKey, String serviceNodeKey, ServiceMetaInfo newServiceCache) {
        serviceNodeCache.put(serviceNodeKey, newServiceCache);
        serviceCache.put(serviceKey, serviceNodeCache);
    }

    /**
     * 读缓存
     *
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> readCache(String serviceKey) {
        Map<String, ServiceMetaInfo> stringServiceMetaInfoMap = serviceCache.get(serviceKey);
        if(stringServiceMetaInfoMap == null || stringServiceMetaInfoMap.isEmpty()) {
            return null;
        }
        return stringServiceMetaInfoMap.values().stream().toList();
    }

    /**
     * 删除缓存
     */
    void removeCache(String serviceKey, String serviceNodeKey) {
        serviceNodeCache.remove(serviceNodeKey);
        this.serviceCache.put(serviceKey, serviceNodeCache);
    }
    public boolean containsCache(String serviceKey, String serviceNodeKey) {
        Map<String, ServiceMetaInfo> metaInfoMap = serviceCache.get(serviceKey);
        if (metaInfoMap == null) {
            return false;
        }
        return metaInfoMap.containsKey(serviceNodeKey);
    }

    public boolean containsKey(String serviceKey) {
        return serviceCache.containsKey(serviceKey);
    }
}
