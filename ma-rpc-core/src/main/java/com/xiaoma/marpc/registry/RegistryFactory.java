package com.xiaoma.marpc.registry;

import com.xiaoma.marpc.utils.SpiLoader;


/**
 * Class Name: RegistryFactory
 * Description:
 * Created on: 2025/5/10
 * Author: xiaoma
 */

public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    public static final Registry DEFAULT_REGISTRY = new EtcdRegistry();


    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
