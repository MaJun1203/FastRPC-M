package com.xiaoma.example.consumer;

import com.xiaoma.example.common.model.User;
import com.xiaoma.example.common.service.UserService;
import com.xiaoma.marpc.bootstrap.ConsumerBootstrap;
import com.xiaoma.marpc.proxy.ServiceProxyFactory;

/**
 * Class Name: ConsumerExample
 * Description:
 * Created on: 2025/5/15
 * Author: xiaoma
 */

public class ConsumerExample {
    public static void main(String[] args) {
        // 初始化消费者
        ConsumerBootstrap.init();

        // 获取服务
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("xiaoma");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        User user1 = new User();
        user1.setName("xiaoma1");
        User user2 = userService.getUser(user1);
        System.out.println(user2.getName());
    }
}
