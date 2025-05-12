package com.xiaoma.example.consumer;

import com.xiaoma.example.common.model.User;
import com.xiaoma.example.common.service.UserService;
import com.xiaoma.marpc.config.RpcConfig;
import com.xiaoma.marpc.constant.RpcConstant;
import com.xiaoma.marpc.proxy.ServiceProxy;
import com.xiaoma.marpc.proxy.ServiceProxyFactory;
import com.xiaoma.marpc.utils.ConfigUtils;

/**
 * Class Name: EasyConsumerExample
 * Description:
 * Created on: 2025/5/8
 * Author: 112033918
 */

public class EasyConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
        // 启动服务消费者
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("xiaoma");

        // 调用
        User newUser = userService.getUser(user);
        User newUser1 = userService.getUser(user);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User newUser2 = userService.getUser(user);
        if(newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        /*short number = userService.getNumber();
        System.out.println(number);*/
    }
}
