package com.xiaoma.marpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class Name: LocalRegistry
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class LocalRegistry {
    public static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     */
    public static void register(String serviceName, Class<?> service) {
        map.put(serviceName, service);
    }

    /**
     * 获取服务
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 注销服务
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
