package com.xiaoma.marpc.server;

import com.xiaoma.marpc.model.RpcRequest;
import com.xiaoma.marpc.model.RpcResponse;
import com.xiaoma.marpc.registry.LocalRegistry;
import com.xiaoma.marpc.serializer.JdkSerializer;
import com.xiaoma.marpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Class Name: HttpServerHandler
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class HttpServerHandler implements Handler<HttpServerRequest> {

    /**
     * 处理请求
     *
     * @param request 请求
     * @return 响应
     */
    public void handle(HttpServerRequest request) {
        // 处理请求的逻辑
        final Serializer serializer = new JdkSerializer();
        System.out.println("Received request: " + request.method() + " " + request.uri());
        request.bodyHandler(body -> {
            // 处理请求体
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                // 反序列化请求体
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                // 处理异常
                e.printStackTrace();
            }
            RpcResponse rpcResponse = new RpcResponse();
            if(rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request,rpcResponse,serializer);
                return;
            }
            try{
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getParameters());
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            doResponse(request, rpcResponse, serializer);
        });
    }

    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
