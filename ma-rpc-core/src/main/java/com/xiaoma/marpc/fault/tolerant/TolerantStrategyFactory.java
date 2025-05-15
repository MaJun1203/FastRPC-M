package com.xiaoma.marpc.fault.tolerant;

import com.xiaoma.marpc.utils.SpiLoader;

/**
 * Class Name: TolerantStrategyFactory
 * Description:容错策略工厂
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public class TolerantStrategyFactory {
    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
