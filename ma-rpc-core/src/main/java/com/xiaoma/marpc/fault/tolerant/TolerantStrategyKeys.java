package com.xiaoma.marpc.fault.tolerant;

/**
 * Class Name: TolerantStrategyKeys
 * Description:容错策略键名常量
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public interface TolerantStrategyKeys {
    /**
     * 故障恢复
     */
    String FAIL_BACK = "failBack";

    /**
     * 快速失败
     */
    String FAIL_FAST = "failFast";

    /**
     * 故障转移
     */
    String FAIL_OVER = "failOver";

    /**
     * 静默处理
     */
    String FAIL_SAFE = "failSafe";
}
