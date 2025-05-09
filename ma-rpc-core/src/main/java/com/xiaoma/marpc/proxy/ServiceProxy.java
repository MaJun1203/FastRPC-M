package com.xiaoma.marpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.xiaoma.marpc.RpcApplication;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.model.RpcRequest;
import com.xiaoma.marpc.model.RpcResponse;
import com.xiaoma.marpc.serializer.JdkSerializer;
import com.xiaoma.marpc.serializer.Serializer;
import com.xiaoma.marpc.serializer.SerializerFactory;
import com.xiaoma.marpc.utils.ConfigUtils;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        // 创建请求对象
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .parameters(args)
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            try (HttpResponse httpResponse = HttpRequest.post("http://" + config.getServerHost() + ":" + config.getServerPort())
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
