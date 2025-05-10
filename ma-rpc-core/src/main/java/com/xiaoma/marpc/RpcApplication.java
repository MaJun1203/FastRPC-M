package com.xiaoma.marpc;

import com.xiaoma.marpc.config.RegistryConfig;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.constant.RpcConstant;
import com.xiaoma.marpc.registry.Registry;
import com.xiaoma.marpc.registry.RegistryFactory;
import com.xiaoma.marpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;


/**
 * Class Name: RpcApplication
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */
@Slf4j
public class RpcApplication {
    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     *
     * @param newRpcConfig
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("rpc registry init, config = {}", registryConfig);
    }

    /**
     * 初始化
     */
    public static void init(){
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     *
     * @return
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
