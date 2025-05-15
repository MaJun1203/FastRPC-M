package com.xiaoma.marpc.fault.retry;

import com.github.rholder.retry.*;
import com.xiaoma.marpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Class Name: FixedIntervalRetryStrategy
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

@Slf4j
public class FixedIntervalRetryStrategy implements RetryStrategy {


    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws ExecutionException, RetryException {
        log.debug("Retry strategy:{}", this.getClass().getSimpleName());
        Retryer<RpcResponse> retryer = RetryerBuilder.<RpcResponse>newBuilder()
                .retryIfException()
                .withWaitStrategy(WaitStrategies.fixedWait(3L, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(3))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        log.info("重试次数：{}", attempt.getAttemptNumber());
                    }
                })
                .build();
        return retryer.call(callable);
    }
}
