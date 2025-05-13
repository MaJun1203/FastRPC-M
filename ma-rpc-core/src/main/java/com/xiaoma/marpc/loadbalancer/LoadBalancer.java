package com.xiaoma.marpc.loadbalancer;

import com.xiaoma.marpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * Class Name: LoadBalancer
 * Description:负载均衡器（消费端使用）
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public interface LoadBalancer {
    /**
     * 选择服务调用
     *
     * @param requestParams       请求参数
     * @param serviceMetaInfoList 可用服务列表
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
