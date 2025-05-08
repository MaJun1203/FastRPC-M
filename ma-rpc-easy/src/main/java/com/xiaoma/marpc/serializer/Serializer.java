package com.xiaoma.marpc.serializer;

import java.io.IOException;

/**
 * Class Name: Serializer
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public interface Serializer {
    /**
     * 序列化
     *
     * @param obj 对象
     * @return byte[]
     */
    <T> byte[] serialize(T obj) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes 字节数组
     * @param clazz 类
     * @return Object
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException;
}
