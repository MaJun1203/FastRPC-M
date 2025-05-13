package com.xiaoma.marpc.loadbalancer;

import com.xiaoma.marpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class Name: RandomLoadBalancer
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public class RandomLoadBalancer implements LoadBalancer{

    private final Random random = new Random();
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()) {
            return null;
        }
        int size = serviceMetaInfoList.size();
        if(size == 1) {
            return serviceMetaInfoList.get(0);
        }
        int index = random.nextInt(size);
        return serviceMetaInfoList.get(index);
    }
}
