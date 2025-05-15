package com.xiaoma.marpc.fault.tolerant;

import com.xiaoma.marpc.model.RpcResponse;

import java.util.Map;

/**
 * Class Name: FailOverTolerantStrategy
 * Description:转移到其他服务节点
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public class FailOverTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        return null;
    }
}
