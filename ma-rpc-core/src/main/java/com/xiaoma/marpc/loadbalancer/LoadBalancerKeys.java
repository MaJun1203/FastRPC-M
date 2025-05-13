package com.xiaoma.marpc.loadbalancer;

/**
 * Class Name: LoadBalancerKeys
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public interface LoadBalancerKeys {
    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    String RANDOM = "random";

    String CONSISTENT_HASH = "consistentHash";
}
