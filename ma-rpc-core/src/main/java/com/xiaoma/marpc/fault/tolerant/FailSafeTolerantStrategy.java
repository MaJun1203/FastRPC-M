package com.xiaoma.marpc.fault.tolerant;

import com.xiaoma.marpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Class Name: FailSafeTolerantStrategy
 * Description:
 * Created on: 2025/5/14
 * Author: xiaoma
 */

@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常",e);
        return new RpcResponse();
    }
}
