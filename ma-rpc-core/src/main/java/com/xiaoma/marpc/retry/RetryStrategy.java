package com.xiaoma.marpc.retry;

import com.github.rholder.retry.RetryException;
import com.xiaoma.marpc.model.RpcResponse;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * Class Name: RetryStrategy
 * Description:重试策略
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public interface RetryStrategy {

    /**
     * 重试
     *
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
