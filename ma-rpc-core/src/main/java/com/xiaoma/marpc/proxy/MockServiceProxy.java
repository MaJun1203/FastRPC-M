package com.xiaoma.marpc.proxy;

import com.github.javafaker.Faker;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.model.RpcRequest;
import com.xiaoma.marpc.model.RpcResponse;
import com.xiaoma.marpc.server.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Class Name: MockServiceProxy
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

@Slf4j
public class MockServiceProxy implements InvocationHandler {
    public static final Faker faker = new Faker(new Locale("zh-CN"));

    /**
     *调用代理
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invock {}",method.getName());
        return getDefaultObject(methodReturnType);
    }
    private Object getDefaultObject(Class<?> type) {
        // 基本类型
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == int.class) {
                return 0;
            } else if (type == long.class) {
                return 0L;
            }
        }
        //RpcRequest/Response - Model
        if (type == RpcRequest.class || type == RpcResponse.class) {
            return getDefaultRpcReqResp(type);
        }

        //Rpc - Config
        if (type == RpcConfig.class) {
            return getDefaultRpcConfig();
        }

        //Http Server
        if (type.isAssignableFrom(HttpServer.class)) {
            return getDefaultHttpServer();
        }

        log.error("Receive unsupported return type:{}", type);
        //其他类型
        throw new RuntimeException("Unsupported return type:" + type);
    }

    private HttpServer getDefaultHttpServer() {
        return port -> System.out.println(
                faker.bothify("Default-HttpServer-##; Start on Port:" + port)
        );
    }

    private RpcConfig getDefaultRpcConfig() {
        RpcConfig rpcConfig = new RpcConfig();

        //faker属性值
        rpcConfig.setMock(true);
        rpcConfig.setName(faker.bothify("Default-RpcConfig-##"));
        rpcConfig.setServerHost(faker.internet().ipV4Address());
        rpcConfig.setServerPort(faker.number().numberBetween(1000, 9999));
        rpcConfig.setVersion(faker.bothify("v0.#"));

        return rpcConfig;
    }

    //返回 Request Response Model 对象
    private Object getDefaultRpcReqResp(Class<?> type) {
        if (type == RpcRequest.class) {
            RpcRequest request = new RpcRequest();

            //Faker 属性值
            request.setServiceName(faker.bothify("DefaultService-##"));
            request.setMethodName(faker.bothify("Default-Method-##"));
            request.setParameters(new Object[]{"Default-Params-##"});
            request.setParameterTypes(new Class<?>[]{faker.getClass()});
            return request;
        } else if (type == RpcResponse.class) {
            RpcResponse rpcResponse = new RpcResponse();

            //faker 属性值
            rpcResponse.setMessage(faker.bothify("Default-Message-??"));
            rpcResponse.setException(new RuntimeException());
            rpcResponse.setData(faker.getClass());
            rpcResponse.setDataType(faker.getClass());

            return rpcResponse;
        }
        return null;
    }
}
