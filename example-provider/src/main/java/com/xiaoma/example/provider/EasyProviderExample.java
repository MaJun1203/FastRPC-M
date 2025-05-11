package com.xiaoma.example.provider;

import com.xiaoma.example.common.service.UserService;
import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.registry.EtcdRegistry;
import com.xiaoma.marpc.registry.LocalRegistry;
import com.xiaoma.marpc.registry.Registry;
import com.xiaoma.marpc.server.HttpServer;
import com.xiaoma.marpc.server.VertxHttpServer;

/**
 * Class Name: EasyProviderExample
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class EasyProviderExample {

    public static void main(String[] args) {
        RpcApplication.init();
        // 启动服务提供者
        HttpServer httpServer = new VertxHttpServer();
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
