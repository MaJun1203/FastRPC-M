package com.xiaoma.example.provider;

import com.xiaoma.example.common.service.UserService;
import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.config.RegistryConfig;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.model.ServiceMetaInfo;
import com.xiaoma.marpc.registry.LocalRegistry;
import com.xiaoma.marpc.registry.Registry;
import com.xiaoma.marpc.registry.RegistryFactory;
import com.xiaoma.marpc.server.HttpServer;
import com.xiaoma.marpc.server.VertxHttpServer;

/**
 * Class Name: ProviderExample
 * Description:
 * Created on: 2025/5/10
 * Author: xiaoma
 */

public class ProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
