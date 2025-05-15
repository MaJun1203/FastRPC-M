package com.xiaoma.marpc.bootstrap;

import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.config.RegistryConfig;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.model.ServiceMetaInfo;
import com.xiaoma.marpc.model.ServiceRegisterInfo;
import com.xiaoma.marpc.registry.LocalRegistry;
import com.xiaoma.marpc.registry.Registry;
import com.xiaoma.marpc.registry.RegistryFactory;
import com.xiaoma.marpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * Class Name: ProviderBootstrap
 * Description:
 * Created on: 2025/5/14
 * Author: xiaoma
 */

public class ProviderBootstrap {

    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // RPC 框架初始化
        RpcApplication.init();
        //全局配置
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        //注册服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {

            String serviceName = serviceRegisterInfo.getServiceName();
            Class<?> implClass = serviceRegisterInfo.getImplClass();
            //本地注册
            LocalRegistry.register(serviceName, implClass);

            // 注册服务到注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }
        }
        // 启动 tcp 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
