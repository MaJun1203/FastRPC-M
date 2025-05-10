package com.xiaoma.marpc.config;

import com.xiaoma.marpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * Class Name: RpcConfig
 * Description: RPC 框架配置
 * Created on: 2025/5/8
 * Author: 112033918
 */

@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name = "ma-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private int serverPort = 8080;

    /**
     * 模拟调用
     */
    private boolean mock = false;
    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;
    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
}
