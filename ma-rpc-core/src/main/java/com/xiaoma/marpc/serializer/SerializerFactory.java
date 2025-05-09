package com.xiaoma.marpc.serializer;

import com.xiaoma.marpc.utils.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Name: SerializerFactory
 * Description:
 * Created on: 2025/5/9
 * Author: xiaoma
 */

public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }
    /**
     * 序列化映射（用于实现单例）
     */
    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();;

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }

}
