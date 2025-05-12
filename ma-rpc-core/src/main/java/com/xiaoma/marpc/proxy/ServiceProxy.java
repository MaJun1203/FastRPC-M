package com.xiaoma.marpc.proxy;

import cn.hutool.core.util.IdUtil;
import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.constant.RpcConstant;
import com.xiaoma.marpc.model.RpcRequest;
import com.xiaoma.marpc.model.RpcResponse;
import com.xiaoma.marpc.model.ServiceMetaInfo;
import com.xiaoma.marpc.protocol.*;
import com.xiaoma.marpc.registry.Registry;
import com.xiaoma.marpc.registry.RegistryFactory;
import com.xiaoma.marpc.serializer.ProtocolMessageSerializerEnum;
import com.xiaoma.marpc.serializer.Serializer;
import com.xiaoma.marpc.serializer.SerializerFactory;
import com.xiaoma.marpc.server.tcp.VertxTcpClient;
import com.xiaoma.marpc.utils.ConfigUtils;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Class Name: ServiceProxy
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcConfig config = ConfigUtils.loadConfig(RpcConfig.class, "rpc");

        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        String serviceName = method.getDeclaringClass().getName();
        // 创建请求对象
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);

            //从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (serviceMetaInfoList == null || serviceMetaInfoList.isEmpty()) {
                throw new RuntimeException("暂无服务地址");
            }
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);


            // 发送 TCP 请求
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
            return rpcResponse.getData();
        } catch (IOException e) {
            throw new RuntimeException("调用失败");
        }
    }
}
