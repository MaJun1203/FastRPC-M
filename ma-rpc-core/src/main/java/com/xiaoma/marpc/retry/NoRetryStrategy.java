package com.xiaoma.marpc.retry;

import com.xiaoma.marpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * Class Name: NoRetryStrategy
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public class NoRetryStrategy implements RetryStrategy {

    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
