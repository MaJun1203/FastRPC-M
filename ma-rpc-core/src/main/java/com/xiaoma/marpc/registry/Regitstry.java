package com.xiaoma.marpc.registry;

import com.xiaoma.marpc.config.RegistryConfig;
import com.xiaoma.marpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * Class Name: Regitstry
 * Description:
 * Created on: 2025/5/9
 * Author: xiaoma
 */

public interface Regitstry {
    /**
     * 初始化
     *
     * @param registryConfig
     */
    void init(RegistryConfig registryConfig);

    /**
     * 注册服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws Exception;

    /**
     * 注销服务（服务端）
     *
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo);

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     *
     * @param serviceKey 服务键名
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    /**
     * 服务销毁
     */
    void destroy();
}
