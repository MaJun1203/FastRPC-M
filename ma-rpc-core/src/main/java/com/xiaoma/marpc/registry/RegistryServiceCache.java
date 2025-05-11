package com.xiaoma.marpc.registry;

import com.xiaoma.marpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * Class Name: RegistryServiceCache
 * Description:注册中心服务本地缓存
 * Created on: 2025/5/11
 * Author: xiaoma
 */

public class RegistryServiceCache {
    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     * 写缓存
     *
     * @param newServiceCache
     * @return
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     *
     * @return
     */
    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    void clearCache() {
        this.serviceCache = null;
    }
}
