package com.xiaoma.marpc.retry;

/**
 * Class Name: RetryStrategyKeys
 * Description:
 * Created on: 2025/5/13
 * Author: xiaoma
 */

public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
