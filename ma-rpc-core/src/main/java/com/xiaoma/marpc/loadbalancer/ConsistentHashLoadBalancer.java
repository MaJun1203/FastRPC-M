package com.xiaoma.marpc.loadbalancer;

import com.google.common.hash.Funnel;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.xiaoma.marpc.model.ServiceMetaInfo;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class Name: ConsistentHashLoadBalancer
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public class ConsistentHashLoadBalancer implements LoadBalancer{

    /**
     * 一致性Hash环，存放虚拟节点
     */
    private final TreeMap<Integer,ServiceMetaInfo> virtualNodes = new TreeMap<>();

    /**
     * 选择 MurmurHash 算法，运算效率高; 预防碰撞也很强
     */
    private static final HashFunction HASH_FUNCTION = Hashing.murmur3_128();


    /**
     * 虚拟节点数
     */
    public static final int VIRTUAL_NODE_NUM = 100;
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if(serviceMetaInfoList.isEmpty()) {
            return null;
        }
        // 构建虚拟节点环
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }

        // 获取调用请求的 hash 值
        int hash = getHash(requestParams);

        // 选择最接近且大于等于调用请求 hash 值的虚拟节点
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);

        if (entry == null) {
            // 如果没有大于等于调用请求 hash 值的虚拟节点，则返回环首部的节点
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }



    /**
     * Hash 算法，可自行实现
     */
    private int getHash(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Object to hash cannot be null");
        }

        return Math.abs(
                HASH_FUNCTION.newHasher()
                        .putObject(obj, createFunnel())
                        .hash()
                        .asInt()
        ) % 16384;
    }

    /**
     * 转化为字节表示；默认使用 toString
     *
     * @return
     */
    private Funnel<Object> createFunnel() {
        return (obj, into) -> {
            into.putString(obj.toString(), StandardCharsets.UTF_8);
        };
    }


}
