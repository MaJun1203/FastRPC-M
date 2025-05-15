package com.xiaoma.marpc.fault.tolerant;

import com.xiaoma.marpc.model.RpcResponse;

import java.util.Map;

/**
 * Class Name: TolerantStrategy
 * Description: 容错策略
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public interface TolerantStrategy {
    /**
     * 容错处理
     * @param context
     * @return
     * @throws Exception
     */
    RpcResponse doTolerant(Map<String, Object> context,Exception e);
}
