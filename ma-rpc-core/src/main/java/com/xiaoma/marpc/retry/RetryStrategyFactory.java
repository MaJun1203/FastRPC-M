package com.xiaoma.marpc.retry;

import com.sun.net.httpserver.Authenticator;
import com.xiaoma.marpc.utils.SpiLoader;

/**
 * Class Name: RetryStrategyFactory
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public class RetryStrategyFactory {
    static{
        SpiLoader.load(RetryStrategy.class);
    }

    /**
     * 默认重试器
     */
    public static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    public static RetryStrategy getInstance(String key) {
        if (key == null || key.isEmpty()) {
            return DEFAULT_RETRY_STRATEGY;
        }
        RetryStrategy retryStrategy = SpiLoader.getInstance(RetryStrategy.class, key);
        if (retryStrategy == null) {
            return DEFAULT_RETRY_STRATEGY;
        }
        return retryStrategy;

    }

}
