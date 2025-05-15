package com.xiaoma.marpc.fault.tolerant;

import com.xiaoma.marpc.model.RpcResponse;

import java.util.Map;

/**
 * Class Name: FailFastTolerantStrategy
 * Description: 快速失败容错策略（立刻通知外层调用方）
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错",e);
    }
}
