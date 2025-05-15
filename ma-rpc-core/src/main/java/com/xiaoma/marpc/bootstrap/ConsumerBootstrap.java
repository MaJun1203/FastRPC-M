package com.xiaoma.marpc.bootstrap;

import com.xiaoma.marpc.RpcApplication;

/**
 * Class Name: ConsumerBootstrap
 * Description:服务消费者启动类
 * Created on: 2025/5/15
 * Author: xiaoma
 */

public class ConsumerBootstrap {
    /**
     * 初始化
     */
    public static void init() {
        // RPC框架初始化（配置和注册中心）
        RpcApplication.init();
    }
}
