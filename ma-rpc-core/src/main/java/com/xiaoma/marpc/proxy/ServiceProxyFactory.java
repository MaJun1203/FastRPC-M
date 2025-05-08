package com.xiaoma.marpc.proxy;

import java.lang.reflect.Proxy;

/**
 * Class Name: ServiceProxyFactory
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class ServiceProxyFactory {
    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }
}
